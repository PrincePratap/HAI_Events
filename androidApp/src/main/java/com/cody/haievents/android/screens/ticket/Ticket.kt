package com.cody.haievents.android.screens.ticket

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cody.haievents.android.main.MainActivity

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
    val mainContext = LocalContext.current as MainActivity  // we’ll need MainActivity.startPayment()
    val activity = context as? MainActivity




    LaunchedEffect(showId) {
        Log.d(TAG, "Requesting ticket list for showId: $showId")
        viewModel.getTicketList(showId)
    }

    DisposableEffect(Unit) {
        activity?.onPhonePeSuccess = {
            // 👉 Your VM method that performs the final booking
            viewModel.buyTickets()
        }
        onDispose {
            activity?.onPhonePeSuccess = null
        }
    }




    LaunchedEffect(uiState.isLoading, uiState.errorMessage, uiState.ticketList, uiState.totalTickets, uiState.totalPrice) {
        when {
            uiState.isLoading -> Log.d(TAG, "UI State: Loading.")
            uiState.errorMessage != null -> Log.e(TAG, "UI State: Error - ${uiState.errorMessage}")
            uiState.ticketList.isNotEmpty() -> Log.i(TAG, "UI State: Loaded ${uiState.ticketList.size} ticket types.")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                is UiEvent.NavigateToSuccess -> {}
                is UiEvent.OrderCreated -> {
                }
            }
        }
    }

    TicketScreen(
        uiState = uiState,
        onQuantityChange = { ticketId, newQty -> viewModel.updateQuantity(ticketId.toInt(), newQty) },
        onGetTicketClick = {
            Log.d(TAG, "UI Event: paymentWithRazorPay clicked.")
//
            viewModel.createOrder()
        },
        navigationBack = { navigator.navigateUp() }
    )

    LaunchedEffect(uiState.createPhoneOrderResponse) {
        uiState.createPhoneOrderResponse?.let { resp ->
            Log.d("GaneshTheater", "orderID ${resp.merchantOrderId}  token ${resp.order.token}")

            val orderId = resp.merchantOrderId
            val token = resp.order.token
            mainContext.initializePhonePeSdk(token = token,orderId =  orderId)   // call MainActivity method
//            viewModel.clearPaymentTrigger()        // reset so it doesn’t repeat
        }
    }
}
