package com.cody.haievents.android.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.cody.haievents.android.screens.destinations.LoginDestination

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

import androidx.compose.runtime.getValue
import com.cody.haievents.android.screens.destinations.WelcomeDestination

@Destination(start = true)
@Composable
fun Splash(navigator: DestinationsNavigator) {

    val viewModel: SplashScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    // Show UI

    LaunchedEffect(uiState) {
        when (uiState) {
            is SplashUiState.Loading -> {

            }

            is SplashUiState.Success -> {
                val token = (uiState as SplashUiState.Success).currentUser.token
                if (token.isEmpty()) {
                    navigator.navigate(WelcomeDestination.route) {
                        popUpTo(0)
                    }
                } else {
                    navigator.navigate(HomePageDestination.route) {
                        popUpTo(0)
                    }
                }
            }
        }
    }

    SplashScreen() // No-op here since navigation is now automatic
}