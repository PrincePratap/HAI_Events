package com.cody.haievents.android.screens.myTickets

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.destinations.MyTicketsDetailsDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "MyTicketsRoute"

@Destination
@Composable
fun MyTickets(
    navigator: DestinationsNavigator,
    ) {
    Log.d(TAG, "Entered MyTickets composable.")

    val viewModel: MyTicketsViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    // Log dispose (leaving screen)
    DisposableEffect(Unit) {
        Log.d(TAG, "MyTickets: Composable in composition.")
        onDispose {
            Log.d(TAG, "MyTickets: Composable leaving composition.")
        }
    }

    // Kick off loading once
    LaunchedEffect(Unit) {
        Log.d(TAG, "MyTickets: Triggering fetchMyTickets()")
        viewModel.fetchMyTickets()
    }

    // Log state transitions (loading / success / data presence)
    LaunchedEffect(uiState.isLoading, uiState.succeed, uiState.myTicketList) {
        when {
            uiState.isLoading -> Log.d(TAG, "State: Loading…")
            uiState.succeed && uiState.myTicketList != null -> {
                Log.i(TAG, "State: Success. Data received = ${uiState.myTicketList}")
            }

            !uiState.isLoading && uiState.myTicketList == null && uiState.errorMessage == null -> {
                Log.w(TAG, "State: Idle (no data, no error).")
            }
        }
    }

    // Show and consume error
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Log.e(TAG, "SideEffect: Error observed -> $error")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.onErrorMessageShown()
        }
    }

    // Your screen UI
    MyTicketsScreen(
        uiState = uiState,
        onTicketClick = {
            navigator.navigate(MyTicketsDetailsDestination(ticketID = it))
        })
}
