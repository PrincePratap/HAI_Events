package com.cody.haievents.android.screens.addEvent.eventDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.Show.domain.usecase.EventDetailsUseCase
import com.cody.haievents.Show.domain.usecase.UploadImageUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch


class EventDetailsViewModel(
    private val  eventDetailsUseCase: EventDetailsUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "EventDetailsViewModel"
    }

    var uiState by mutableStateOf(EventDetailsUiState())
        private set



    fun checkEventDetails() {
        viewModelScope.launch {

            uiState = uiState.copy(isLoading = true, errorMessage = null, succeed = false)

            val result = eventDetailsUseCase(
                eventTitle = uiState.eventTitle,
                organiserName = uiState.organiserName,
                contactEmail = uiState.contactEmail,
                eventLocation = uiState.eventLocation,
                eventDate = uiState.eventDate,
                eventTime = uiState.eventTime,
                eventDescription = uiState.eventDescription,
            )

            uiState = when (result) {
                is Result.Error -> {
                    Log.e(TAG, "Event details validation FAILED: ${result.message}")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = result.message,
                        succeed = false,
                        builtRequest = null
                    )
                }
                is Result.Success -> {
                    val req = result.data
                    Log.d(
                        TAG,
                        "Event details validation OK. " +
                                "normalizedDate='${req?.date}', normalizedTime='${req?.time}', " +
                                "tickets=${req?.ticketTypes?.size ?: 0}"
                    )
                    uiState.copy(
                        isLoading = false,
                        errorMessage = null,
                        succeed = true,
                        builtRequest = req
                    )
                }
            }
        }
    }




    // --- Field updaters ---
    fun updateEventTitle(value: String) { uiState = uiState.copy(eventTitle = value) }
    fun updateOrganiserName(value: String) { uiState = uiState.copy(organiserName = value) }
    fun updateContactEmail(value: String) { uiState = uiState.copy(contactEmail = value) }
    fun updateEventLocation(value: String) { uiState = uiState.copy(eventLocation = value) }
    fun updateEventDate(value: String) { uiState = uiState.copy(eventDate = value) }
    fun updateEventTime(value: String) { uiState = uiState.copy(eventTime = value) }
    fun updateEventDescription(value: String) { uiState = uiState.copy(eventDescription = value) }

    // If you're building tickets elsewhere, expose a setter:
//    fun setTicketTypes(list: List<TicketTypeRequest>) { uiState = uiState.copy(ticketTypesList = list) }

    fun clearError() { uiState = uiState.copy(errorMessage = null) }
    fun reset() { uiState = EventDetailsUiState() }
}

data class EventDetailsUiState(
    var eventTitle: String = "",
    var organiserName: String = "",
    var contactEmail: String = "",
    var eventLocation: String = "",
    var eventDate: String = "",
    var eventTime: String = "",
    var eventDescription: String = "",
    var eventImage: String = "",
    var builtRequest: CreateUserEventRequest? = null,
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)

