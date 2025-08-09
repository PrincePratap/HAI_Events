package com.cody.haievents.payment

object PaymentResultHandler {
    var onPaymentResult: ((PaymentResult) -> Unit)? = null
}