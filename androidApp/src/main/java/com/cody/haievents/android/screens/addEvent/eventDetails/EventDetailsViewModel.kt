package com.cody.haievents.android.screens.addEvent.eventDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class EventDetailsViewModel(
) : ViewModel() {

    companion object {
        private const val TAG = "EventDetailsViewModel"
    }

    var uiState by mutableStateOf(EventDetailsUiState())
        private set

//    fun checkEventDetails() {
//        viewModelScope.launch {
//            // start loading and clear previous error/success
//            uiState = uiState.copy(isLoading = true, errorMessage = null, succeed = false)
//
//            val result = useCase(
//                eventTitle = uiState.eventTitle,
//                organiserName = uiState.organiserName,
//                contactEmail = uiState.contactEmail,
//                eventLocation = uiState.eventLocation,
//                eventDate = uiState.eventDate,           // required, no format check
//                eventTime = uiState.eventTime,           // required, no format check
//                eventDescription = uiState.eventDescription,
//                ticketType = uiState.ticketType,
//                ticketQuantity = uiState.ticketQuantity, // numeric
//                ticketPrice = uiState.ticketPrice        // decimal
//            )
//
//            uiState = when (result) {
//                is Result.Error -> {
//                    Log.e(TAG, "Validation failed: ${result.message}")
//                    uiState.copy(isLoading = false, errorMessage = result.message, succeed = false)
//                }
//                is Result.Success -> {
//                    Log.d(TAG, "Validation succeeded")
//                    uiState.copy(isLoading = false, errorMessage = null, succeed = true)
//                }
//            }
//        }
//    }

    // --- Field updaters (call from TextFields) ---
    fun updateEventTitle(value: String) { uiState = uiState.copy(eventTitle = value) }
    fun updateOrganiserName(value: String) { uiState = uiState.copy(organiserName = value) }
    fun updateContactEmail(value: String) { uiState = uiState.copy(contactEmail = value) }
    fun updateEventLocation(value: String) { uiState = uiState.copy(eventLocation = value) }
    fun updateEventDate(value: String) { uiState = uiState.copy(eventDate = value) }
    fun updateEventTime(value: String) { uiState = uiState.copy(eventTime = value) }
    fun updateEventDescription(value: String) { uiState = uiState.copy(eventDescription = value) }
    fun updateTicketType(value: String) { uiState = uiState.copy(ticketType = value) }
    fun updateTicketQuantity(value: String) { uiState = uiState.copy(ticketQuantity = value) }
    fun updateTicketPrice(value: String) { uiState = uiState.copy(ticketPrice = value) }

    // Optional: clear error after showing a Snackbar/dialog
    fun clearError() { uiState = uiState.copy(errorMessage = null) }

    // Optional: reset entire form
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
    var ticketType: String = "",
    var ticketQuantity: String = "",
    var ticketPrice: String = "",
    var password: String = "",
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)
