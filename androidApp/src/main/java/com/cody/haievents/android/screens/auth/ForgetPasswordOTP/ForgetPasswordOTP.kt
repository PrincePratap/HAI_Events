package com.cody.haievents.android.screens.auth.ForgetPasswordOTP

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.auth.otp.OTPScreen
import com.cody.haievents.android.screens.auth.otp.OTPViewModel
import com.cody.haievents.android.screens.destinations.HomePageDestination
import com.cody.haievents.android.screens.destinations.NewPasswordDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel



// 1. Use a specific TAG for this route
private const val TAG = "ForgetPasswordOTP"

@Destination
@Composable
fun ForgetPasswordOTP(
    navController: DestinationsNavigator,
    token: String = "",
) {
    // 2. Log entry and parameters for debugging navigation
    Log.d(TAG, "Composable entered composition. Received token: ${!token.isNullOrEmpty()}")

    val viewModel: ForgetPasswordOTPViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    // 3. Add lifecycle logging to know when the screen is left
    DisposableEffect(Unit) {
        onDispose {
            Log.d(TAG, "Composable left composition.")
        }
    }

    ForgetPasswordOTPScreen(
        uiState = uiState,
        onOTpValueChanged = {
            // 4. Log UI event delegation to the ViewModel
            Log.d(TAG, "UI Event: onOTpValueChanged triggered. Delegating to ViewModel.")
            viewModel.onOtpValueChanged(it)
        },
        onContinueClicked = {
            Log.d(TAG, "UI Event: onContinueClicked triggered. Delegating to ViewModel.")
            viewModel.submitOTP(token)
        },
        onResendClicked = {
            Log.d(TAG, "UI Event: onResendClicked triggered. Delegating to ViewModel.")
            // viewModel.resendOtp() // Assuming you will add this function
        }
    )

    // 5. Handle one-time side effects safely with enhanced logging
    LaunchedEffect(key1 = uiState.succeed, key2 = uiState.errorMessage) {
        if (uiState.succeed) {
            Log.i(TAG, "Side Effect: Success state observed. Navigating to home screen and clearing back stack.")
            navController.navigate(NewPasswordDestination(uiState.response?.token?: "empty_token"))
            // Inform the ViewModel that the navigation has been handled to prevent re-triggering
            viewModel.onNavigationHandled()
            Log.d(TAG, "Side Effect: Navigation event consumed by calling onNavigationHandled().")
        }

        uiState.errorMessage?.let { error ->
            Log.e(TAG, "Side Effect: Error state observed. Showing Toast for message: '$error'")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            // Inform the ViewModel that the error has been shown
            viewModel.onErrorMessageShown()
            Log.d(TAG, "Side Effect: Error message event consumed by calling onErrorMessageShown().")
        }
    }
}