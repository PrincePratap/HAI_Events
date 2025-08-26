package com.cody.haievents.android.screens.addEvent.eventDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.data.model.CreateUserEventRequest
import com.cody.haievents.Show.data.model.TicketTypeRequest
import com.cody.haievents.Show.domain.usecase.EventDetailsUseCase
import com.cody.haievents.Show.domain.usecase.UploadImageUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch


class EventDetailsViewModel(
    private val  uploadImageUseCase: UploadImageUseCase,
    private val  eventDetailsUseCase: EventDetailsUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "EventDetailsViewModel"
    }

    var uiState by mutableStateOf(EventDetailsUiState())
        private set


    fun uploadEventImage(
        imageBytes: ByteArray,
        fileName: String
    ) {
        Log.d("EventDetailsViewModel", "Image upload started → fileName=$fileName, size=${imageBytes.size} bytes")

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val uploadResult = uploadImageUseCase(
                imageBytes = imageBytes,
                fileName = fileName
            )

            uiState = when (uploadResult) {
                is Result.Error -> {
                    Log.e(
                        "EventDetailsViewModel",
                        "Image upload failed → fileName=$fileName, error=${uploadResult.message}"
                    )
                    uiState.copy(
                        isLoading = false,
                        errorMessage = uploadResult.message
                    )
                }

                is Result.Success -> {
                    Log.d(
                        "EventDetailsViewModel",
                        "Image upload successful → fileName=$fileName, url=${uploadResult.data?.imageUrl}"
                    )
                    uiState.copy(
                        isLoading = false,
                        succeed = true
                    )
                }
            }
        }
    }
    fun checkEventDetails() {
        viewModelScope.launch {
            Log.d(
                TAG,
                "Validating event details… title='${uiState.eventTitle}', organiser='${uiState.organiserName}', " +
                        "date='${uiState.eventDate}', time='${uiState.eventTime}', tickets=${uiState.ticketTypesList.size}"
            )
            uiState = uiState.copy(isLoading = true, errorMessage = null, succeed = false)

            val result = eventDetailsUseCase(
                eventTitle = uiState.eventTitle,
                organiserName = uiState.organiserName,
                contactEmail = uiState.contactEmail,
                eventLocation = uiState.eventLocation,
                eventDate = uiState.eventDate,
                eventTime = uiState.eventTime,
                eventDescription = uiState.eventDescription,
                ticketTypesList = uiState.ticketTypesList
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
    fun setTicketTypes(list: List<TicketTypeRequest>) { uiState = uiState.copy(ticketTypesList = list) }

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
    var ticketTypesList: List<TicketTypeRequest> = emptyList(),
    var builtRequest: CreateUserEventRequest? = null,
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)

