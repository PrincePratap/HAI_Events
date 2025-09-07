package com.cody.haievents.android.screens.addEvent.eventImageAndTickets

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.UploadImageUseCase
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.android.screens.addEvent.eventDetails.EventDetailsUiState
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class EventImageAndTicketsViewModel(
//    private val  uploadImageUseCase: UploadImageUseCase,
    ) : ViewModel() {
    companion object {
        private const val TAG = "EventDetailsViewModel"
    }

    var uiState by mutableStateOf(EventImageAndTicketsUiState())
        private set


//
//    fun uploadEventImage(
//        imageBytes: ByteArray,
//        fileName: String
//    ) {
//        Log.d("EventDetailsViewModel", "Image upload started → fileName=$fileName, size=${imageBytes.size} bytes")
//
//        viewModelScope.launch {
//            uiState = uiState.copy(isLoading = true)
//
//            val uploadResult = uploadImageUseCase(
//                imageBytes = imageBytes,
//                fileName = fileName
//            )
//
//            uiState = when (uploadResult) {
//                is Result.Error -> {
//                    Log.e(
//                        "EventDetailsViewModel",
//                        "Image upload failed → fileName=$fileName, error=${uploadResult.message}"
//                    )
//                    uiState.copy(
//                        isLoading = false,
//                        errorMessage = uploadResult.message
//                    )
//                }
//
//                is Result.Success -> {
//                    Log.d(
//                        "EventDetailsViewModel",
//                        "Image upload successful → fileName=$fileName, url=${uploadResult.data?.imageUrl}"
//                    )
//                    uiState.copy(
//                        isLoading = false,
//                        succeed = true,
//                        eventImage = uploadResult.data?.imageUrl ?: ""
//                    )
//                }
//            }
//        }
//    }


}

data class EventImageAndTicketsUiState(
    var eventImage: String = "",
    var builtRequest: CreateUserEventRequest? = null,
    var ticketTypesList: List<TicketTypeRequest> = emptyList(),
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)

