package com.cody.haievents.android.screens.ShowDetailed

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.data.ShowEventResponse
import com.cody.haievents.Show.domain.usecase.ShowDetailUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class ShowDetailedViewModel(
    private val useCase: ShowDetailUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "ShowDetailedViewModel"
    }

    var uiState by mutableStateOf(ShowDetailedUiState())
        private set

    init {
        Log.d(TAG, "ViewModel initialized")
    }

    fun getShowDetail(showId: Int) {
        Log.d(TAG, "getShowDetail called for showId: $showId")

        viewModelScope.launch {
            // Set loading state
            uiState = uiState.copy(isLoading = true)
            Log.d(TAG, "UI State updated to isLoading = true")

            // Execute the use case
            val showResultData = useCase(showId)

            // Process the result
            uiState = when (showResultData) {
                is Result.Error -> {
                    // Log the error with the specific message
                    Log.e(TAG, "Failed to fetch show details: ${showResultData.message}")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = showResultData.message
                    )
                }

                is Result.Success -> {
                    // Corrected the log tag and made the message more descriptive
                    Log.d(TAG, "Successfully fetched show details. Data: ${showResultData.data}")
                    uiState.copy(
                        isLoading = false,
                        succeed = true,
                        showDetail = showResultData.data
                    )
                }
            }
        }
    }

    // Log when the ViewModel is about to be destroyed
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared and resources released.")
    }
}


data class ShowDetailedUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var showDetail: ShowEventResponse? = null
)