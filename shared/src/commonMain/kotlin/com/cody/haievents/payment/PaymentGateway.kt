package com.cody.haievents.payment



// Add the method signature to the expect class
expect class PaymentGateway {
    // We remove parameters that are platform-specific, like the Activity.
    // The amount can be passed in.
    fun startPayment(amount: Double)
    fun startPayment(amount: Double, razorpayKey: String)
}

// The result class is fine.
sealed class PaymentResult {
    data class Success(val paymentId: String) : PaymentResult()
    data class Error(val errorCode: Int, val description: String) : PaymentResult()
}