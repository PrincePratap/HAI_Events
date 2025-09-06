package com.cody.haievents.payment

// shared/src/iosMain/kotlin/payments/PaymentGatewayIos.kt


import kotlinx.coroutines.suspendCancellableCoroutine

import kotlin.coroutines.resume

class PaymentGatewayIos : PaymentGateway {
    override suspend fun startPayment(base64Payload: String): PaymentResult =
        suspendCancellableCoroutine { cont ->
            // Pseudocode – PhonePe iOS SDK opens PhonePe app / web sheet.
            // PhonePePayment.shared.startPayment(withBase64Payload: ...)
            // In the completion handler, resume with Success/Failure/Pending.
            cont.resume(PaymentResult.Pending("Launched PhonePe on iOS"))
        }

    override suspend fun checkStatus(merchantTransactionId: String): PaymentResult {
        // Same pattern: call your server to query PhonePe status
        return PaymentResult.Pending("Check via server")
    }
}
