package com.cody.haievents.android.screens.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.auth.forgetpassword.ForgetPassword
import com.cody.haievents.android.screens.destinations.ForgetPasswordDestination
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.cody.haievents.android.screens.destinations.RegisterDestination
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
    val context = LocalContext.current


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
        },
        onRegisterClick = {
            navigator.navigate(RegisterDestination.route)
        },
        onForgotPasswordClick = {
            navigator.navigate(ForgetPasswordDestination.route)
        }
    )

    uiState.errorMessage?.let { error ->
        Log.e("LoginComposable", "Error: $error")
        // Show a Toast, Dialog or Snackbar here if needed
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()

    }

    if (uiState.succeed) {
        Log.d("LoginComposable", "Login success — navigating to next screen")
        navigator.navigate(HomePageDestination.route)
    }
}
