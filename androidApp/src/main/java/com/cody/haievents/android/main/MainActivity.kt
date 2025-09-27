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

    var onPhonePeSuccess: (() -> Unit)? = null
    var onPhonePeCancelled: (() -> Unit)? = null
    var onPhonePeFailed: ((resultCode: Int) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    fun initializePhonePeSdk(
        merchantId: String = "M234FIQGR25BJ",
        token: String,
        orderId: String
    ) {
        val ok = PhonePeKt.init(
            context = this,
            merchantId = merchantId,
            flowId = "FLOW_" + System.currentTimeMillis(),
            phonePeEnvironment = PhonePeEnvironment.RELEASE,  // ← prod
            enableLogging = true,
            appId = packageName
        )

        if (ok) {
            startPayment(token, orderId)
        } else {
            Log.e(TAG, "PhonePe SDK init failed.")
            Toast.makeText(this, "PhonePe SDK init failed", Toast.LENGTH_SHORT).show()
        }
    }

    fun startPayment(token: String, orderId: String) {
        try {
            Log.i(TAG, "startPayment() orderId=$orderId, token=$token")
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
            intent?.extras?.keySet()?.forEach { k ->
                Log.i(TAG, "extra[$k]=${intent.extras?.get(k)}")
            }

            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    Toast.makeText(this, "Payment finished (verify on server)", Toast.LENGTH_SHORT).show()
                    // ✅ Notify UI to finalize booking (e.g., viewModel.buyTickets())
                    onPhonePeSuccess?.invoke()
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(this, "Payment canceled by user", Toast.LENGTH_SHORT).show()
                    onPhonePeCancelled?.invoke()
                }
                else -> {
                    Toast.makeText(this, "Payment failed/unknown (verify on server)", Toast.LENGTH_SHORT).show()
                    onPhonePeFailed?.invoke(result.resultCode)
                }
            }
        }
}
