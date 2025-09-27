package com.cody.haievents.android.screens.myTickets

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.MyTicketListUseCase
import com.cody.haievents.Show.model.MyTicketListResponse
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class MyTicketsViewModel(
    private val useCase: MyTicketListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MyTicketsViewModel"
    }

    var uiState by mutableStateOf(MyTicketsUiState())
        private set

    /** Fetch user's ticket list */
    fun fetchMyTickets() {
        val start = System.currentTimeMillis()
        Log.d(TAG, "fetchMyTickets: start")

        viewModelScope.launch {
            // State -> Loading
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            Log.i(TAG, "fetchMyTickets: state -> isLoading=true (cleared previous error)")

            val result = useCase()

            uiState = when (result) {
                is Result.Error -> {
                    Log.e(TAG, "fetchMyTickets: FAILED -> ${result.message}")
                    val next = uiState.copy(isLoading = false, errorMessage = result.message, succeed = false)
                    LogState("fetchMyTickets: state -> error", next)
                    next
                }

                is Result.Success -> {
                    Log.d(TAG, "fetchMyTickets: SUCCESS (data received)")
                    val next = uiState.copy(
                        isLoading = false,
                        errorMessage = null,
                        succeed = true,
                        myTicketList = result.data
                    )
                    LogState("fetchMyTickets: state -> success", next)
                    next
                }
            }

            val elapsed = System.currentTimeMillis() - start
            Log.d(TAG, "fetchMyTickets: end (elapsed=${elapsed}ms)")
        }
    }

    /** Consume one-time success flag after UI handles it (e.g., navigation). */
    fun onNavigationHandled() {
        Log.d(TAG, "onNavigationHandled: succeed=true -> false")
        uiState = uiState.copy(succeed = false)
        LogState("onNavigationHandled: state", uiState)
    }

    /** Consume error after it has been shown (prevents repeat on config change). */
    fun onErrorMessageShown() {
        Log.d(TAG, "onErrorMessageShown: clearing errorMessage")
        uiState = uiState.copy(errorMessage = null)
        LogState("onErrorMessageShown: state", uiState)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

    private fun LogState(prefix: String, state: MyTicketsUiState) {
        Log.i(
            TAG,
            "$prefix -> isLoading=${state.isLoading}, succeed=${state.succeed}, " +
                    "error=${state.errorMessage?.take(120)}, hasData=${state.myTicketList != null}"
        )
    }
}

data class MyTicketsUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var myTicketList: MyTicketListResponse? = null
)
