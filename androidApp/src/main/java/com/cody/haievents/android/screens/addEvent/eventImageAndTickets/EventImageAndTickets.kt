package com.cody.haievents.android.screens.addEvent.eventImageAndTickets

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.cody.haievents.Show.model.Role
import com.cody.haievents.android.common.componets.TicketRowUi
import com.cody.haievents.android.screens.destinations.BankTransferDetailsDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun EventImageAndTickets(
    navigator: DestinationsNavigator
) {
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
                TicketRowUi(Role.ATTENDEE,  "Audience",  "50", "99")
            )
        )
    }

    // Image/file picker (updates nothing yet, just hooks up the action)
    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->


    }

    EventImageAndTicketsScreen(
        tickets = tickets,
        onTicketChange = { index, updated ->
            tickets = tickets.toMutableList().also { it[index] = updated }
        },
        onAddTicket = { tickets = tickets + TicketRowUi() },
        onRemoveTicket = { index ->
            if (tickets.size > 1) {
                tickets = tickets.toMutableList().also { it.removeAt(index) }
            }
        },
        clickOnChooseFile = { pickImage.launch("image/*") },
        onNextClick = { navigator.navigate(BankTransferDetailsDestination.route) }
    )
}
