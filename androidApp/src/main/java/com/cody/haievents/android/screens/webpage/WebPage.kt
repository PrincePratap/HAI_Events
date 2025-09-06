package com.cody.haievents.android.screens.webpage

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "WebPageComposable"

@Destination
@Composable
fun WebPage(
    type: Int,
    navigator: DestinationsNavigator
) {
    Log.d(TAG, "Entered composition. type=$type")

    val viewModel: WebPageViewModel = koinViewModel()
    val uiState = viewModel.uiState

    // Fetch when the screen appears or when `type` changes
    LaunchedEffect(type) {
        Log.d(TAG, "Fetching terms/privacy for type=$type")
        viewModel.fetchTermsConditions(type)
    }

    // Log success state changes
    LaunchedEffect(uiState.succeed) {
        if (uiState.succeed) {
            Log.i(TAG, "Fetch succeed=true (data ready).")
            // If you trigger one-time actions on success, consume it:
            viewModel.onNavigationHandled()
        }
    }

    // Log error state changes
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { msg ->
            Log.w(TAG, "Error received: $msg")
            // If you show a snackbar/dialog, consume afterwards:
            viewModel.onErrorMessageShown()
        }
    }

    WebPageScreen(
        uiState = uiState,
        clickBack = {
            Log.d(TAG, "Back clicked. Navigating up.")
            navigator.navigateUp()
        }
    )

    Log.v(TAG, "Compose pass complete. uiState=$uiState")
}
