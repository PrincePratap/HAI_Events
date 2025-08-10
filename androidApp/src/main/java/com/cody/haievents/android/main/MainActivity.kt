package com.cody.haievents.android.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

import com.cody.haievents.android.common.theming.MyApplicationTheme
import com.cody.haievents.payment.PaymentResult
import com.cody.haievents.payment.PaymentResultHandler
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener

class MainActivity : ComponentActivity() , PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Checkout.preload(applicationContext)


        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HaiEventsApp()
                }
            }
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {

        PaymentResultHandler.onPaymentResult?.invoke(
            PaymentResult.Success(razorpayPaymentId ?: "N/A")
        )
    }

    override fun onPaymentError(code: Int, description: String?) {
        // The payment failed.
        // Pass the result back to the UI/ViewModel.
        PaymentResultHandler.onPaymentResult?.invoke(
            PaymentResult.Error(code, description ?: "Unknown error")
        )
    }
}



