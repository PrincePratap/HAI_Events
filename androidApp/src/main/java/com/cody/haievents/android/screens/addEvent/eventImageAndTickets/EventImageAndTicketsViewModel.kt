package com.cody.haievents.android.screens.addEvent.eventImageAndTickets

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.EventImageAndTicketsUseCase
import com.cody.haievents.Show.domain.usecase.UploadImageUseCase
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.Role
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.android.screens.addEvent.eventDetails.EventDetailsUiState
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class EventImageAndTicketsViewModel(
    private val uploadImageUseCase: UploadImageUseCase,
    private val eventImageAndTicketsUseCase: EventImageAndTicketsUseCase
) : ViewModel() {

    companion object { private const val TAG = "EventImageTicketsVM" }

    var uiState by mutableStateOf(EventImageAndTicketsUiState())
        private set

    fun uploadEventImage(imageBytes: ByteArray, fileName: String) {
        Log.d(TAG, "Uploading image: $fileName (${imageBytes.size} bytes)")
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            when (val r = uploadImageUseCase(imageBytes = imageBytes, fileName = fileName)) {
                is Result.Error -> {
                    Log.e(TAG, "Upload failed: ${r.message}")
                    uiState = uiState.copy(isLoading = false, errorMessage = r.message)
                }
                is Result.Success -> {
                    val url = r.data?.imageUrl.orEmpty()
                    Log.d(TAG, "Upload success → $url")
                    uiState = uiState.copy(isLoading = false, eventImage = url)
                }
            }
        }
    }

    /**
     * Build & validate payload with image URL + ticket types.
     * Returns Success(updatedPayload) or Error(message)
     */
    fun submitEventImageAndTickets(
        basePayload: CreateUserEventRequest,
        ticketsUi: List<com.cody.haievents.android.common.componets.TicketRowUi>
    ) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, succeed = false)

            // Map UI rows -> request objects safely
            val ticketTypes: List<TicketTypeRequest> = ticketsUi.mapIndexed { idx, t ->
                TicketTypeRequest(
                    role = t.role,                                   // Role
                    name = t.name.trim().orEmpty(),                 // String
                    quantity = t.quantity.toIntOrNull() ?: 0,        // Int
                    price = t.price.toIntOrNull() ?: 0         // Double
                )
            }

            // Merge current image URL
            val payloadWithImage = basePayload.copy(
                imagePath = uiState.eventImage,    // <-- server URL from upload
                ticketTypes = ticketTypes
            )

            when (val res = eventImageAndTicketsUseCase(
                payload = payloadWithImage,
                imagePath = uiState.eventImage,
                ticketTypes = ticketTypes
            )) {
                is Result.Error -> {
                    Log.e(TAG, "Validation failed: ${res.message}")
                    uiState = uiState.copy(isLoading = false, errorMessage = res.message)
                }
                is Result.Success -> {
                    Log.d(TAG, "Validation success. Ready for next step.")
                    uiState = uiState.copy(
                        isLoading = false,
                        succeed = true,
                        imageTicketsPayload = res.data
                    )
                }
            }
        }
    }
}

data class EventImageAndTicketsUiState(
    var eventImage: String = "",                                    // uploaded image URL
    var imageTicketsPayload: CreateUserEventRequest? = null,        // validated payload
    var ticketTypesList: List<TicketTypeRequest> = emptyList(),
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)
