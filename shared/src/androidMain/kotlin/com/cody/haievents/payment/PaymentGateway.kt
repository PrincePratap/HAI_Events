package com.cody.haievents.payment

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import com.razorpay.Checkout
import org.json.JSONObject

// The actual class now matches the expect signature
actual class PaymentGateway(private val context: Context) {

    private fun findActivity(): Activity? {
        var ctx = context
        while (ctx is ContextWrapper) {
            if (ctx is Activity) {
                return ctx
            }
            ctx = ctx.baseContext
        }
        return null
    }

    actual fun startPayment(amount: Double) {
        val activity = findActivity()
        if (activity == null) {
            Toast.makeText(context, "Cannot find Activity to start payment", Toast.LENGTH_SHORT).show()
            return
        }

        val checkout = Checkout()
        // Use the secure key from BuildConfig
        checkout.setKeyID("rzp_live_UeuZ7eVS4dSjOP")

        try {
            val options = JSONObject()
            options.put("name", "Hai Events")
            options.put("description", "Test Payment")
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            // Amount must be in the smallest currency unit (e.g., paisa for INR)
            options.put("amount", (amount * 100).toInt())

            val prefill = JSONObject()
            prefill.put("email", "test.customer@example.com")
            prefill.put("contact", "9876543210")
            options.put("prefill", prefill)

            checkout.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error starting payment: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}