package com.cody.haievents.android.screens.auth.register

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.cody.haievents.android.screens.destinations.LoginDestination
import com.cody.haievents.android.screens.destinations.OTPDestination
import com.cody.haievents.android.screens.destinations.RegisterDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "RegisterRoute"

@Destination
@Composable
fun Register(navController: DestinationsNavigator) {
    Log.d(TAG, "Register route Composable entered composition.")

    val viewModel: RegisterViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    RegisterScreen(
        uiState = uiState,
        onFirstNameChange = { viewModel.updateFirstName(it) },
        onLastNameChange = { viewModel.updateLastName(it) },
        onEmailChange = { viewModel.updateEmail(it) },
        onPhoneChange = { viewModel.updateTelephone(it) },
        onPasswordChange = { viewModel.updatePassword(it) },
        onConfirmPasswordChange = { viewModel.updateConfirmPassword(it) },
        onRegisterClick = {
            Log.d(TAG, "Register button clicked. Forwarding to ViewModel.")
            viewModel.register()
        },
        onLoginClick = {
            Log.d(TAG, "Login navigation clicked. Navigating to Login screen.")
            navController.navigate(LoginDestination) {
                popUpTo(RegisterDestination.route) {
                    inclusive = true
                }
            }
        },
        clickOnBackButton = {
            navController.navigateUp()
        })


    LaunchedEffect(key1 = uiState.succeed, key2 = uiState.errorMessage) {
        if (uiState.succeed) {
            Log.d(TAG, "Register success — navigating to home and clearing back stack.")
            navController.navigate(OTPDestination(token = uiState.verificationToken ))
            viewModel.registrationHandled()
        }

        uiState.errorMessage?.let { error ->
            Log.e(TAG, "Error: $error")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            // Inform the ViewModel that the error has been shown.
            viewModel.errorShown()
        }
    }
}