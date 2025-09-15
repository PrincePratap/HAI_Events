package com.cody.haievents.android.screens.auth.newpassword

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.destinations.LoginDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun NewPassword(token: String, navigator: DestinationsNavigator) {

    val viewModel: NewPasswordViewModel = koinViewModel()
    Log.d("NewPasswordScreen", "Composable loaded with token: $token")

    val uiState = viewModel.uiState
    val context = LocalContext.current

    NewPasswordScreen(
        uiState = uiState,
        onPasswordChange = {
            Log.d("NewPasswordScreen", "Password updated")
            viewModel.updatePassword(it)
        },
        onConfirmPasswordChange = {
            Log.d("NewPasswordScreen", "Confirm password updated")
            viewModel.updateConfirmPassword(it)
        },
        onSetNewPasswordClick = {
            Log.d("NewPasswordScreen", "Reset password clicked")
            viewModel.resetPassword(token)
        }
    )

    uiState.errorMessage?.let { error ->
        Log.e("NewPasswordScreen", "Error: $error")
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    if (uiState.succeed) {
        Log.i("NewPasswordScreen", "Password reset successful, navigating to Login")
        navigator.navigate(LoginDestination.route)
    }
}
