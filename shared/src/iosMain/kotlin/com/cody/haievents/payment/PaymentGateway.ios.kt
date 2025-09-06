package com.cody.haievents.payment


// shared/src/iosMain/kotlin/com/cody/haievents/payment/PaymentGateway.ios.kt

actual class PaymentGateway {

    actual suspend fun startPayment(base64Payload: String): PaymentResult {
        return PaymentResult.Pending("Launched PhonePe on iOS")
    }

    actual suspend fun checkStatus(merchantTransactionId: String): PaymentResult {
        return PaymentResult.Pending("Check via server")
    }
}
