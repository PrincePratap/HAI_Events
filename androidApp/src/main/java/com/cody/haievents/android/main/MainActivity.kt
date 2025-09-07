package com.cody.haievents.android.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cody.haievents.android.common.theming.MyApplicationTheme
import com.phonepe.intent.sdk.api.PhonePeKt
import com.phonepe.intent.sdk.api.models.PhonePeEnvironment


private const val TAG = "PhonePeIntegration"


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializePhonePeSdk()

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


    private fun initializePhonePeSdk() {
        val ok = PhonePeKt.init(
            context = this,
            merchantId = "M234FIQGR25BJ",
            flowId = "FLOW_" + System.currentTimeMillis(),
            phonePeEnvironment = PhonePeEnvironment.RELEASE,  // ← prod
            enableLogging = true,
            appId = packageName
        )

        if (ok) {
            Log.i(TAG, "PhonePe SDK initialized.")
        } else {
            Log.e(TAG, "PhonePe SDK init failed.")
        }
    }


    fun startPayment(token: String, orderId: String) {
        try {
            Log.i(TAG, "startPayment() orderId=$orderId, token.len=${token}")
            PhonePeKt.startCheckoutPage(
                context = this,
                token = token,
                orderId = orderId,
                activityResultLauncher = phonePeLauncher
            )
        } catch (e: Throwable) {
            Log.e(TAG, "startCheckoutPage threw", e)
            Toast.makeText(this, "PhonePe launch failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private val phonePeLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Log.w(TAG, "SDK resultCode=${result.resultCode}")
            val intent = result.data
            if (intent == null) {
                Log.w(TAG, "No intent data returned")
            } else {
                Log.i(TAG, "data=$intent")
                intent.extras?.keySet()?.forEach { k ->
                    Log.i(TAG, "extra[$k]=${intent.extras?.get(k)}")
                }
            }

            val msg = when (result.resultCode) {
                Activity.RESULT_OK -> "Payment flow finished (verify on server)"
                Activity.RESULT_CANCELED -> "Payment canceled by user (verify on server)"
                else -> "Payment failed/unknown (verify on server)"
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


        }


}
