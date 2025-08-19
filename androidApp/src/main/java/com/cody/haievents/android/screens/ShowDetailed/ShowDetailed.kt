package com.cody.haievents.android.screens.ShowDetailed

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cody.haievents.android.screens.auth.login.LoginViewModel
import com.cody.haievents.android.screens.destinations.TicketDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


private const val TAG = "ShowDetailedRoute"


@Destination
@Composable
fun ShowDetailed(
    navigator: DestinationsNavigator,
    showId: Int
    ) {

    Log.d(TAG, "HomePage Composable entered composition.")


    val viewModel: ShowDetailedViewModel = koinViewModel()
    Log.d("LoginComposable", "Login Composable Loaded")


    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        Log.d(TAG, "Starting fetchHomePage()")
        viewModel.getShowDetail(showId)
    }

    ShowDetailedScreen(
        uiState = uiState,
        navigationBack = { navigator.navigateUp() },
        navigateToTicketList = {
            navigator.navigate(TicketDestination(showId))
        }
    )


}