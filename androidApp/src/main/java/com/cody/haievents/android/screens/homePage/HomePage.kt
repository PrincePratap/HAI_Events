package com.cody.haievents.android.screens.homePage

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

// Use a specific and consistent TAG for this route
private const val TAG = "HomePageRoute"

@Destination() // Assuming this is the starting screen of your app
@Composable
fun HomePage(
    navigator: DestinationsNavigator,
) {
    // Corrected log message
    Log.d(TAG, "HomePage Composable entered composition.")

    val viewModel: HomePageViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        Log.d(TAG, "Starting fetchHomePage()")
        viewModel.fetchHomePageData()
    }


    // Pass the state and event handlers down to the stateless screen composable.
    HomePageScreen(
        uiState = uiState,
        onRetry = {
            Log.d(TAG, "UI Event: onRetry clicked. Delegating to ViewModel.")
            viewModel.fetchHomePageData()
        }
    )

    // Handle one-time side effects like showing an error message
    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Log.e(TAG, "Side Effect: Error state observed. Showing Toast for message: '$error'")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            // Inform the ViewModel that the error has been shown to prevent re-showing
            viewModel.onErrorMessageShown()
        }
    }
}