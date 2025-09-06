package com.cody.haievents.android.screens.allCategories

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.AllCategoriesUseCase
import com.cody.haievents.Show.model.AllCategoryResponse
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class AllCategoriesViewModel(
    private val useCase: AllCategoriesUseCase
) : ViewModel() {

    private val TAG = "AllCategoriesViewModel"

    var uiState by mutableStateOf(AllCategoriesUiState())
        private set

    fun fetchAllCategories() {
        Log.d(TAG, "fetchAllCategories: Starting to fetch categories...")

        viewModelScope.launch {
            Log.i(TAG, "fetchAllCategories: State transition -> isLoading=true")
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            val result = useCase()

            uiState = when (result) {
                is Result.Error -> {
                    Log.e(TAG, "fetchAllCategories: Fetch failed. Reason: ${result.message}")
                    Log.i(TAG, "fetchAllCategories: State transition -> isLoading=false, errorMessage updated.")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is Result.Success -> {
                    Log.d(TAG, "fetchAllCategories: Fetch successful. Data received.")
                    Log.i(TAG, "fetchAllCategories: State transition -> isLoading=false, succeed=true, data updated.")
                    uiState.copy(
                        isLoading = false,
                        succeed = true,
                        homePageData = result.data
                    )
                }
            }
            Log.d(TAG, "fetchAllCategories: Request finished. Final uiState: $uiState")
        }
    }

    fun onNavigationHandled() {
        Log.d(TAG, "onNavigationHandled: Consuming success event. succeed -> false.")
        uiState = uiState.copy(succeed = false)
    }

    fun onErrorMessageShown() {
        Log.d(TAG, "onErrorMessageShown: Consuming error message. errorMessage -> null.")
        uiState = uiState.copy(errorMessage = null)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared.")
    }


}
data class AllCategoriesUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var homePageData: AllCategoryResponse? = null
)
