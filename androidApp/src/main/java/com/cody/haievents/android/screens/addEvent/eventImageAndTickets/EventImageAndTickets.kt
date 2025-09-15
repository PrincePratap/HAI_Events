package com.cody.haievents.android.screens.addEvent.eventImageAndTickets

import android.database.Cursor
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.Role
import com.cody.haievents.android.common.componets.TicketRowUi
import com.cody.haievents.android.screens.destinations.BankTransferDetailsDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun EventImageAndTickets(
    navigator: DestinationsNavigator,
    payload: CreateUserEventRequest = CreateUserEventRequest()
) {
    val vm: EventImageAndTicketsViewModel = koinViewModel()
    val state = vm.uiState
    val ctx = LocalContext.current

    // tickets local state (as you had)
    var tickets by rememberSaveable(
        stateSaver = androidx.compose.runtime.saveable.listSaver(
            save = { list -> list.flatMap { listOf(it.role.name, it.name, it.quantity, it.price) } },
            restore = { flat ->
                flat.chunked(4).map { (role, name, qty, price) ->
                    TicketRowUi(
                        role = Role.valueOf(role),
                        name = name,
                        quantity = qty,
                        price = price
                    )
                }
            }
        )
    ) {
        mutableStateOf(
            listOf(
                TicketRowUi(Role.PERFORMER, "Performer", "10", "299"),
            )
        )
    }

    // Image picker -> read bytes -> upload
    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val name = runCatching {
                var result = "event_poster.jpg"
                val cursor: Cursor? = ctx.contentResolver.query(uri, null, null, null, null)
                cursor?.use {
                    val nameIdx = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (it.moveToFirst() && nameIdx >= 0) {
                        result = it.getString(nameIdx)
                    }
                }
                result
            }.getOrElse { "event_poster.jpg" }

            val bytes = runCatching {
                ctx.contentResolver.openInputStream(uri)?.use { it.readBytes() }
            }.getOrNull()

            if (bytes == null) {
                Toast.makeText(ctx, "Unable to read selected file", Toast.LENGTH_LONG).show()
            } else {
                vm.uploadEventImage(bytes, name)
            }
        }
    }

    // UI
    EventImageAndTicketsScreen(
        tickets = tickets,
        onTicketChange = { index, updated ->
            tickets = tickets.toMutableList().also { it[index] = updated }
        },
        onAddTicket = { tickets = tickets + TicketRowUi() },
        onRemoveTicket = { index ->
            if (tickets.size > 1) tickets = tickets.toMutableList().also { it.removeAt(index) }
        },
        clickOnChooseFile = { pickImage.launch("image/*") },
        onNextClick = {
            vm.submitEventImageAndTickets(payload, tickets)
        }
    )

    // Feedback + Navigation
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { Toast.makeText(ctx, it, Toast.LENGTH_LONG).show() }
    }
    LaunchedEffect(state.succeed) {
        if (state.succeed && state.imageTicketsPayload != null) {
            // Pass the validated payload forward if your destination expects it
            navigator.navigate(BankTransferDetailsDestination.route)
        }
    }
}
