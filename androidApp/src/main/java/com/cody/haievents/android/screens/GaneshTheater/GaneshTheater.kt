package com.cody.haievents.android.screens.GaneshTheater

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cody.haievents.android.main.MainActivity
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

private const val TAG = "GaneshTheaterComposable"



@Destination
@Composable
fun GaneshTheater() {
    val viewModel: GaneshTheaterViewModel = koinViewModel()
    val context = LocalContext.current as MainActivity  // we’ll need MainActivity.startPayment()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGaneshTheater()
    }

    // Show error exactly once per change
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { msg ->
            Log.e("GaneshTheater", "Error: $msg")
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }

    // 👇 Observe paymentResponse trigger
    LaunchedEffect(uiState.paymentResponse) {
        uiState.paymentResponse?.let { resp ->
            Log.d("GaneshTheater", "orderID ${resp.merchantOrderId}  token ${resp.token}")

            val orderId = resp.merchantOrderId
            val token = resp.token
            context.startPayment(token, orderId)   // call MainActivity method
            viewModel.clearPaymentTrigger()        // reset so it doesn’t repeat
        }
    }

    GaneshTheaterCushionChairsScreen(
        uiState = uiState,
        clickOnProceed = {
            if (!uiState.isLoading) {
                viewModel.makePayment(it)
            }
        }
    )

    if (uiState.succeed) {
        Log.d("GaneshTheater", "GaneshTheater success — data loaded or action completed")
    }
}
