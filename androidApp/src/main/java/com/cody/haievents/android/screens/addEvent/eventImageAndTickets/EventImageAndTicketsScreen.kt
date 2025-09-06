package com.cody.haievents.android.screens.addEvent.eventImageAndTickets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.Show.model.Role
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.CommonButton
import com.cody.haievents.android.common.componets.EventPosterUploader
import com.cody.haievents.android.common.componets.TicketDetailsSection
import com.cody.haievents.android.common.componets.TicketRowUi
import com.cody.haievents.android.common.theming.goldColor
import com.cody.haievents.android.common.theming.lightGoldBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventImageAndTicketsScreen(
    tickets: List<TicketRowUi> = emptyList(),
    onTicketChange: (index: Int, updated: TicketRowUi) -> Unit,
    onAddTicket: () -> Unit,
    onRemoveTicket: (index: Int) -> Unit,
    clickOnChooseFile: () -> Unit
    , onNextClick: () -> Unit = {}
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = goldColor,
        unfocusedBorderColor = lightGoldBorder,
        cursorColor = goldColor,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White
    )

    Scaffold(
        topBar = {
          CommonTopBar()
        }, bottomBar = {
            CommonButton(text = "Next", onClick = onNextClick)

        }

    ) { contentPadding: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            EventPosterUploader(clickOnChooseFile = clickOnChooseFile)

            // Spacer is optional; TicketDetailsSection has its own spacing
            // Spacer(Modifier.height(16.dp))

            TicketDetailsSection(
                tickets = tickets,
                onTicketChange = onTicketChange,
                onAddTicket = onAddTicket,
                onRemoveTicket = onRemoveTicket,
                colors = textFieldColors
            )
        }
    }
}

@Preview(showBackground = true, name = "Event Image & Tickets – Preview")
@Composable
private fun EventImageAndTicketsPreview() {
    MaterialTheme {
        Surface {
            // Local preview state with listSaver so edits survive recomposition
            var tickets by rememberSaveable(
                stateSaver = listSaver(
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
                        TicketRowUi(Role.ATTENDEE, "Audience", "50", "99")
                    )
                )
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
                clickOnChooseFile = { /* no-op for preview */ }
            )
        }
    }
}
