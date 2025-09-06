// androidApp/src/main/java/com/cody/haievents/android/main/MainActivity.kt
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
import java.util.UUID


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
            enableLogging = false,
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
            PhonePeKt.startCheckoutPage(
                context = this,
                token = token,
                orderId = orderId,
                activityResultLauncher = phonePeLauncher
            )
        } catch (e: Exception) {
            Log.e(TAG, "startCheckoutPage failed", e)
            Toast.makeText(this, "Unable to start PhonePe", Toast.LENGTH_SHORT).show()
        }
    }

    private val phonePeLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Log.w(TAG, "SDK returned resultCode=${result.resultCode}")
            val data = result.data
            data?.extras?.keySet()?.forEach { k ->
                Log.d(TAG, "result.extra[$k]=${data.extras?.get(k)}")
            }

            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    Toast.makeText(
                        this,
                        "Returned from PhonePe (verify on server)",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    Toast.makeText(
                        this,
                        "Payment cancelled/failed (verify on server)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


}
