package com.cody.haievents.android.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.data.model.Movie
import com.cody.haievents.Show.data.model.SearchShowResponse
import com.cody.haievents.Show.domain.usecase.SearchTextUseCase
import com.cody.haievents.android.screens.homePage.HomepageUiState
import com.cody.haievents.auth.data.HomepageResponse
import com.cody.haievents.auth.domain.usecase.HomePageUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class SearchViewModel(
    private val useCase: SearchTextUseCase

) : ViewModel() {
    // 1. Consistent TAG for all logs in this class
    private val TAG = "HomePageViewModel"

    var uiState by mutableStateOf(SearchUiState())
        private set



    /**
     * Fetches all necessary data for the homepage from the use case.
     */
    fun searchShow(text: String) {
        // 3. Descriptive Action Logging
        Log.d(TAG, "fetchHomePageData: Starting to fetch homepage data...")

        viewModelScope.launch {
            // 4. State Change Logging
            Log.i(TAG, "fetchHomePageData: State transition -> isLoading=true")
            uiState = uiState.copy(isLoading = true, errorMessage = null) // Clear previous errors

            val homepageResult = useCase(text)

            uiState = when (homepageResult) {
                is Result.Error -> {
                    // 5. Specific Error Logging
                    Log.e(TAG, "fetchHomePageData: Fetch failed. Reason: ${homepageResult.message}")
                    Log.i(TAG, "fetchHomePageData: State transition -> isLoading=false, updating error message.")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = homepageResult.message
                    )
                }

                is Result.Success -> {
                    // 6. Specific Success Logging
                    Log.d(TAG, "fetchHomePageData: Fetch successful. Data received.")
                    Log.i(TAG, "fetchHomePageData: State transition -> isLoading=false, succeed=true, data updated.")
                    uiState.copy(
                        isLoading = false,
                        succeed = true, // Use 'succeed' to trigger one-time events like navigation
                        showList = homepageResult.data?.movies ?: emptyList()
                    )
                }
            }
            Log.d(TAG, "fetchHomePageData: Request finished. Final uiState: $uiState")
        }
    }


    fun updateQuery(input: String) {
        Log.d("LoginViewModel", "Password updated")
        uiState = uiState.copy(query = input)
    }

    /**
     * Call this from the UI after a one-time success event (like navigation) has been handled.
     */
    fun onNavigationHandled() {
        // 7. Event Consumption Logging
        Log.d(TAG, "onNavigationHandled: Consuming success event. Setting succeed -> false.")
        uiState = uiState.copy(succeed = false)
    }

    /**
     * Call this from the UI after the error message has been shown
     * to prevent it from being shown again on configuration change.
     */
    fun onErrorMessageShown() {
        Log.d(TAG, "onErrorMessageShown: Consuming error message. Setting errorMessage -> null.")
        uiState = uiState.copy(errorMessage = null)
    }

    // 2. Lifecycle Logging
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared.")
    }

}

data class SearchUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var query: String = "",
    var showList:  List<Movie> = emptyList()
)