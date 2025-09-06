package com.cody.haievents.android.screens.ticket

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "TicketRoute"

@Destination
@Composable
fun Ticket(
    navigator: DestinationsNavigator,
    showId: Int ) {
    val viewModel: TicketViewModel = koinViewModel()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current


    LaunchedEffect(showId) {
        Log.d(TAG, "Requesting ticket list for showId: $showId")
        viewModel.getTicketList(showId)
    }


//    DisposableEffect(Unit) {
//        PaymentResultHandler.onPaymentResult = { result ->
//            when (result) {
//                is PaymentResult.Success -> {
//                    Log.d(TAG,"Payment Success: ID = ${result.paymentId}")
//                    Toast.makeText(context, "Payment Success!", Toast.LENGTH_SHORT).show()
//                    // Here you would typically navigate to a success screen or update the ViewModel
//                }
//                is PaymentResult.Error -> {
//                    Log.e(TAG, "Payment Error: Code=${result.errorCode}, Desc=${result.description}")
//                    Toast.makeText(context, "Payment Failed: ${result.description}", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//
//        onDispose {
//            PaymentResultHandler.onPaymentResult = null
//        }
//    }

//    LaunchedEffect(uiState.isLoading, uiState.errorMessage, uiState.ticketList, uiState.totalTickets, uiState.totalPrice) {
//        when {
//            uiState.isLoading -> Log.d(TAG, "UI State: Loading.")
//            uiState.errorMessage != null -> Log.e(TAG, "UI State: Error - ${uiState.errorMessage}")
//            uiState.ticketList.isNotEmpty() -> Log.i(TAG, "UI State: Loaded ${uiState.ticketList.size} ticket types.")
//        }
//    }

//    LaunchedEffect(Unit) {
//        viewModel.events.collect { event ->
//            when (event) {
//                is UiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
//                is UiEvent.NavigateToSuccess -> {}
//                is UiEvent.OrderCreated -> {
//                    event.payload?.key?.let { PaymentGateway(context).startPayment(amount = uiState.totalPrice.toDouble(), razorpayKey = it) }
//                }
//            }
//        }
//    }

//    TicketScreen(
//        uiState = uiState,
//        onQuantityChange = { ticketId, newQty -> viewModel.updateQuantity(ticketId.toInt(), newQty) },
//        onGetTicketClick = {
//            Log.d(TAG, "UI Event: paymentWithRazorPay clicked.")
//            // Pass the context to the constructor so it can find the Activity
////
//            viewModel.createOrder()
//        },
//        navigationBack = { navigator.navigateUp() }
//    )
}
