package com.cody.haievents.android.screens.myTicketsDetails

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "MyTicketsDetailsRoute"

@Destination
@Composable
fun MyTicketsDetails(
    navigator: DestinationsNavigator,
    ticketID: Int,
    viewModel: MyTicketsDetailsViewModel = koinViewModel()
) {
    Log.d(TAG, "Entered MyTicketsDetails (ticketID=$ticketID)")

    val uiState = viewModel.uiState

    // Kick off loading when ticketID changes
    LaunchedEffect(ticketID) {
        Log.d(TAG, "Fetching ticket details for id=$ticketID")
        viewModel.fetchTicketDetails(ticketID)
    }

    // Log state transitions
    LaunchedEffect(uiState.isLoading, uiState.succeed, uiState.myTicketDetails) {
        when {
            uiState.isLoading -> Log.d(TAG, "State: Loading…")
            uiState.succeed && uiState.myTicketDetails != null -> {
                Log.i(
                    TAG,
                    "State: Success. Title='${uiState.myTicketDetails!!.booking.data.title}'"
                )
            }
            !uiState.isLoading && uiState.myTicketDetails == null && uiState.errorMessage == null -> {
                Log.w(TAG, "State: Idle (no data, no error).")
            }
        }
    }

    // Log and consume errors
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { err ->
            Log.e(TAG, "State: Error -> $err")
            viewModel.onErrorMessageShown()
        }
    }

    MyTicketsDetailsScreen(
        uiState = uiState,
        navigationBack = {
            Log.d(TAG, "Back pressed -> navigateUp()")
            navigator.navigateUp()
        }
    )
}
