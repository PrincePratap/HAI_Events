package com.cody.haievents.android.screens.addEvent.bankTransferDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cody.haievents.Show.domain.usecase.EventCreateUseCase
import com.cody.haievents.Show.domain.usecase.EventDetailsUseCase
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.android.screens.addEvent.eventDetails.EventDetailsUiState

class BankTransferViewModel(
    private val  eventDetailsUseCase: EventDetailsUseCase
): ViewModel() {

    companion object {
        private const val TAG = "EventDetailsViewModel"
    }

    var uiState by mutableStateOf(EventDetailsUiState())
        private set

}

data class BankTransferUiState(
    var imageTicketsPayload: CreateUserEventRequest? = null,        // validated payload
    var ticketTypesList: List<TicketTypeRequest> = emptyList(),
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)