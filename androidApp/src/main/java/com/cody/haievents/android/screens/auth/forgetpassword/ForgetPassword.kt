package com.cody.haievents.android.screens.auth.forgetpassword

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.destinations.ForgetPasswordDestination
import com.cody.haievents.android.screens.destinations.ForgetPasswordOTPDestination
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.cody.haievents.android.screens.destinations.RegisterDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun ForgetPassword(
    navigator: DestinationsNavigator,
    ) {

    val viewModel: ForgetPasswordViewModel = koinViewModel()
    Log.d("LoginComposable", "Login Composable Loaded")

    val uiState = viewModel.uiState
    val context = LocalContext.current

    ForgetPasswordScreen(
        uiState = uiState,
        onIdentifierChange = viewModel::updateEmail,
        onSendOTPClick = {
            viewModel.submitForgetPasswordRequest()
        },
        onRegisterClick = {
            navigator.navigate(RegisterDestination.route)
        }

    )

    uiState.errorMessage?.let { error ->
        Log.e("LoginComposable", "Error: $error")
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    if (uiState.succeed) {
        Log.d("LoginComposable", "Login success — navigating to next screen")
        navigator.navigate(ForgetPasswordOTPDestination(token = uiState.token))
    }
}