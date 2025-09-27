package com.cody.haievents.android.screens.myTicketsDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.MyTicketDetailsUseCase
import com.cody.haievents.Show.model.MyTicketDetails
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class MyTicketsDetailsViewModel(
    private val useCase: MyTicketDetailsUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MyTicketDetailsVM"
    }

    var uiState by mutableStateOf(MyTicketsDetailsUiState())
        private set

    /** New: fetch details for a single ticketId (preferred API) */
    fun fetchTicketDetails(ticketId: Int) {
        val start = System.currentTimeMillis()
        Log.d(TAG, "fetchTicketDetails: start, ticketId=$ticketId")

        // Basic guard
        if (ticketId <= 0) {
            val msg = "Invalid ticketId ($ticketId)"
            Log.e(TAG, "fetchTicketDetails: $msg")
            uiState = uiState.copy(isLoading = false, errorMessage = msg, succeed = false)
            logState("fetchTicketDetails: state -> invalid_id", uiState)
            return
        }

        viewModelScope.launch {
            // -> Loading
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            Log.i(TAG, "fetchTicketDetails: state -> isLoading=true, error=null")

            when (val result = useCase(ticketId)) {
                is Result.Error -> {
                    Log.e(TAG, "fetchTicketDetails: FAILED for ticketId=$ticketId, reason=${result.message}")
                    uiState = uiState.copy(isLoading = false, errorMessage = result.message, succeed = false)
                    logState("fetchTicketDetails: state -> error", uiState)
                }
                is Result.Success -> {
                    Log.d(TAG, "fetchTicketDetails: SUCCESS for ticketId=$ticketId")
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = null,
                        succeed = true,
                        myTicketDetails = result.data
                    )
                    logState("fetchTicketDetails: state -> success", uiState)
                }
            }

            val elapsed = System.currentTimeMillis() - start
            Log.d(TAG, "fetchTicketDetails: end (elapsed=${elapsed}ms)")
        }
    }

    /** Back-compat alias (keeps old call sites working) */
    fun fetchMyTickets(ticketId: Int) {
        Log.w(TAG, "fetchMyTickets(ticketId=$ticketId) is deprecated, use fetchTicketDetails()")
        fetchTicketDetails(ticketId)
    }

    /** Consume one-time success flag after UI handles it (e.g., navigation). */
    fun onNavigationHandled() {
        Log.d(TAG, "onNavigationHandled: succeed=true -> false")
        uiState = uiState.copy(succeed = false)
        logState("onNavigationHandled: state", uiState)
    }

    /** Consume error after it has been shown (prevents repeat on config change). */
    fun onErrorMessageShown() {
        Log.d(TAG, "onErrorMessageShown: clearing errorMessage")
        uiState = uiState.copy(errorMessage = null)
        logState("onErrorMessageShown: state", uiState)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

    private fun logState(prefix: String, state: MyTicketsDetailsUiState) {
        Log.i(
            TAG,
            "$prefix -> isLoading=${state.isLoading}, succeed=${state.succeed}, " +
                    "error=${state.errorMessage?.take(120)}, hasData=${state.myTicketDetails != null}"
        )
    }
}

data class MyTicketsDetailsUiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var myTicketDetails: MyTicketDetails? = null
)
