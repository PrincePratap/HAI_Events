package com.cody.haievents.android.screens.ticket

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel


private const val TAG = "TicketRoute"

@Destination
@Composable
fun Ticket(showId: Int) {
    val viewModel: TicketViewModel = koinViewModel()
    // Collect the state from the ViewModel's StateFlow.
    // Using collectAsStateWithLifecycle is the recommended approach for lifecycle-awareness.
    val uiState = viewModel.uiState

    // Effect to fetch data. It runs when `showId` changes.
    LaunchedEffect(showId) {
        Log.d(TAG, "Requesting ticket list for showId: $showId")
        viewModel.getTicketList(showId)
    }

    // Effect to log significant state changes from the ViewModel.
    // It runs whenever the uiState object itself changes.
    LaunchedEffect(uiState) {
        when {
            uiState.isLoading -> {
                Log.d(TAG, "UI State: Loading.")
            }

            uiState.errorMessage != null -> {
                Log.e(TAG, "UI State: Error received - ${uiState.errorMessage}")
            }

            uiState.ticketList?.isNotEmpty() == true -> {
                Log.i(TAG, "UI State: Success - Loaded ${uiState.ticketList!!.size} ticket types.")
            }
        }
    }

    TicketScreen(
        uiState = uiState,
        onQuantityChange = { _, _ -> /* Preview does nothing */ },
        onGetTicketClick = { /* Preview does nothing */ }
    )
}