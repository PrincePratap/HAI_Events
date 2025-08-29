package com.cody.haievents.android.screens.GaneshTheater

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.GaneshTheaterUseCase
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class GaneshTheaterViewModel(
    private val useCase: GaneshTheaterUseCase
) : ViewModel() {

    var uiState by mutableStateOf(GaneshTheaterUiState())
        private set

    fun getGaneshTheater() {
        Log.d(TAG, "Fetching Ganesh Theater seat configuration…")

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val result = useCase()

            uiState = when (result) {
                is Result.Error -> {
                    Log.e(TAG, "Failed to fetch Ganesh Theater config: ${result.message}")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is Result.Success -> {
                    Log.d(TAG, "Successfully fetched Ganesh Theater config")
                    uiState.copy(
                        isLoading = false,
                        succeed = true,
                        seatConfig = result.data
                    )
                }
            }
        }
    }

    companion object {
        private const val TAG = "GaneshTheaterViewModel"
    }
}

data class GaneshTheaterUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var seatConfig: GaneshTheaterGetSeatResponse? = null
)
