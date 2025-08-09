package com.cody.haievents.android.screens.homePage

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.destinations.ShowDetailedDestination
import com.cody.haievents.payment.PaymentGateway
import com.cody.haievents.payment.PaymentResult
import com.cody.haievents.payment.PaymentResultHandler
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "HomePageRoute"

@Destination()
@Composable
fun HomePage(
    navigator: DestinationsNavigator,
) {
    Log.d(TAG, "HomePage Composable entered composition.")

    val viewModel: HomePageViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    // Set up the listener to handle the result when it comes back from the Activity
    DisposableEffect(Unit) {
        PaymentResultHandler.onPaymentResult = { result ->
            when (result) {
                is PaymentResult.Success -> {
                    Log.d(TAG, "Payment Success: ID = ${result.paymentId}")
                    Toast.makeText(context, "Payment Success!", Toast.LENGTH_SHORT).show()
                    // Here you would typically navigate to a success screen or update the ViewModel
                }
                is PaymentResult.Error -> {
                    Log.e(TAG, "Payment Error: Code=${result.errorCode}, Desc=${result.description}")
                    Toast.makeText(context, "Payment Failed: ${result.description}", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Cleanup the listener when the Composable leaves the screen
        onDispose {
            PaymentResultHandler.onPaymentResult = null
        }
    }

    LaunchedEffect(Unit) {
        Log.d(TAG, "Starting fetchHomePage()")
        viewModel.fetchHomePageData()
    }

    HomePageScreen(
        uiState = uiState,
        onRetry = {
            Log.d(TAG, "UI Event: onRetry clicked.")
            viewModel.fetchHomePageData()
        },
        navigateToShowDetails = {
            navigator.navigate(ShowDetailedDestination(it))
        },
        paymentWithRazorPay = {
            Log.d(TAG, "UI Event: paymentWithRazorPay clicked.")
            // Pass the context to the constructor so it can find the Activity
            PaymentGateway(context).startPayment(amount = 1.0)
        }
    )

    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Log.e(TAG, "Side Effect: Error state observed. Showing Toast for message: '$error'")
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.onErrorMessageShown()
        }
    }
}