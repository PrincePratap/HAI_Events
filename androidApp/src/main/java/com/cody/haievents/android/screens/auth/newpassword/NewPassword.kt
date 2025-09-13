package com.cody.haievents.android.screens.auth.newpassword

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.auth.login.LoginViewModel
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun NewPassword(token: String, navigator: DestinationsNavigator) {

    val viewModel: NewPasswordViewModel = koinViewModel()
    Log.d("LoginComposable", "Login Composable Loaded")

    val uiState = viewModel.uiState
    val context = LocalContext.current

    NewPasswordScreen(
        uiState = uiState,
        onPasswordChange = { viewModel.updatePassword(it) },
        onConfirmPasswordChange = { viewModel.updateConfirmPassword(it) },
        onSetNewPasswordClick = { viewModel.resetPassword(token) })

    uiState.errorMessage?.let { error ->
        Log.e("LoginComposable", "Error: $error")
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()

    }

    if (uiState.succeed){
        navigator.navigate(HomePageDestination.route)
    }
}