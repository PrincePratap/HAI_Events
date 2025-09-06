package com.cody.haievents.android.screens.webpage

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.auth.domain.usecase.TermsConditionsUseCase
import com.cody.haievents.auth.model.TermsConditionsResponse
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class WebPageViewModel(
    private val useCase: TermsConditionsUseCase
) : ViewModel() {

    // Consistent log tag for this ViewModel
    private val TAG = "WebPageViewModel"

    var uiState by mutableStateOf(TermsConditionsUiState())
        private set

    /**
     * Fetches terms & conditions or privacy policy based on type.
     */
    fun fetchTermsConditions(type: Int) {
        Log.d(TAG, "fetchTermsConditionsUiState: Starting fetch request...")

        viewModelScope.launch {
            Log.i(TAG, "fetchTermsConditionsUiState: Loading -> true")
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            val result = useCase(type)

            uiState = when (result) {
                is Result.Error -> {
                    Log.e(TAG, "fetchTermsConditionsUiState: Failed. Reason=${result.message}")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is Result.Success -> {
                    Log.d(TAG, "fetchTermsConditionsUiState: Success. Data received.")
                    uiState.copy(
                        isLoading = false,
                        succeed = true,
                        homePageData = result.data
                    )
                }
            }

            Log.d(TAG, "fetchTermsConditionsUiState: Finished. Final uiState=$uiState")
        }
    }

    /**
     * Reset succeed flag after navigation or one-time success event.
     */
    fun onNavigationHandled() {
        Log.d(TAG, "onNavigationHandled: succeed -> false")
        uiState = uiState.copy(succeed = false)
    }

    /**
     * Reset error message after it has been displayed.
     */
    fun onErrorMessageShown() {
        Log.d(TAG, "onErrorMessageShown: errorMessage -> null")
        uiState = uiState.copy(errorMessage = null)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared")
    }
}

data class TermsConditionsUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var homePageData: TermsConditionsResponse? = null
)
