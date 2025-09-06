package com.cody.haievents.android.screens.blogsDetail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.BlogsItemUseCase
import com.cody.haievents.Show.model.BlogItemResponse
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class BlogsDetailViewModel(
    private val useCase: BlogsItemUseCase
) : ViewModel() {

    // Consistent TAG for this ViewModel
    private val TAG = "BlogsDetailViewModel"

    var uiState by mutableStateOf(BlogsDetailUiState())
        private set

    /**
     * Fetch a specific blog by its ID.
     */
    fun fetchBlogDetail(blogId: Int) {
        Log.d(TAG, "fetchBlogDetail($blogId): Started")

        viewModelScope.launch {
            Log.d(TAG, "fetchBlogDetail: Loading...")
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            when (val result = useCase(blogId)) {
                is Result.Error -> {
                    Log.e(TAG, "fetchBlogDetail: Failed -> ${result.message}")
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is Result.Success -> {
                    Log.d(TAG, "fetchBlogDetail: Success -> Blog loaded")
                    uiState = uiState.copy(
                        isLoading = false,
                        succeed = true,
                        homePageData = result.data
                    )
                }
            }

            Log.v(TAG, "fetchBlogDetail: Final uiState = $uiState")
        }
    }

    /**
     * Reset success flag after navigation.
     */
    fun onNavigationHandled() {
        Log.d(TAG, "onNavigationHandled: Resetting succeed flag")
        uiState = uiState.copy(succeed = false)
    }

    /**
     * Reset error message after it is shown.
     */
    fun onErrorMessageShown() {
        Log.d(TAG, "onErrorMessageShown: Clearing error message")
        uiState = uiState.copy(errorMessage = null)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ViewModel destroyed")
    }
}

data class BlogsDetailUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var homePageData: BlogItemResponse? = null
)
