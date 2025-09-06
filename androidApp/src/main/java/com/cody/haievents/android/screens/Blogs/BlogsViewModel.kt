package com.cody.haievents.android.screens.Blogs

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.BlogsListUseCase
import com.cody.haievents.Show.model.BlogsListResponse
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class BlogsViewModel(
    private val useCase: BlogsListUseCase
) : ViewModel() {

    private val TAG = "BlogsViewModel"

    var uiState by mutableStateOf(BlogsUiState())
        private set

    fun fetchBlogs() {
        Log.d(TAG, "fetchBlogs → started")

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            Log.d(TAG, "fetchBlogs → loading=true")

            when (val result = useCase()) {
                is Result.Error -> {
                    Log.e(TAG, "fetchBlogs → failed: ${result.message}")
                    uiState = uiState.copy(isLoading = false, errorMessage = result.message)
                }
                is Result.Success -> {
                    Log.d(TAG, "fetchBlogs → success, blogs=${result.data?.blogs?.size ?: 0}")
                    uiState = uiState.copy(
                        isLoading = false,
                        succeed = true,
                        blogsPageData = result.data
                    )
                }
            }

            Log.d(TAG, "fetchBlogs → finished, uiState=$uiState")
        }
    }

    fun onNavigationHandled() {
        Log.d(TAG, "onNavigationHandled → succeed=false")
        uiState = uiState.copy(succeed = false)
    }

    fun onErrorMessageShown() {
        Log.d(TAG, "onErrorMessageShown → errorMessage cleared")
        uiState = uiState.copy(errorMessage = null)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared → ViewModel destroyed")
    }
}

data class BlogsUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var blogsPageData: BlogsListResponse? = null
)
