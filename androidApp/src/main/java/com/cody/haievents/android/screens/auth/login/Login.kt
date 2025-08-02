package com.cody.haievents.android.screens.auth.login

import android.util.Log
import androidx.compose.runtime.Composable
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Login(
    navigator: DestinationsNavigator,

) {
    val viewModel: LoginViewModel = koinViewModel()
    Log.d("LoginComposable", "Login Composable Loaded")

    val uiState = viewModel.uiState

    LoginScreen(
        uiState = uiState,
        onIdentifierChange = {
            Log.d("LoginComposable", "Identifier Changed: $it")
            viewModel.updateIdentifiers(it)
        },
        onPasswordChange = {
            Log.d("LoginComposable", "Password Changed")
            viewModel.updatePassword(it)
        },
        onLoginClick = {
            Log.d("LoginComposable", "Login button clicked")
            viewModel.login()
        }
    )

    uiState.errorMessage?.let { error ->
        Log.e("LoginComposable", "Error: $error")
        // Show a Toast, Dialog or Snackbar here if needed
    }

    if (uiState.succeed) {
        Log.d("LoginComposable", "Login success — navigating to next screen")
        navigator.navigate(HomePageDestination.route)
    }
}
