package com.cody.haievents.phonepe.domain.repository


import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.model.PaymentRequestGaneshTheatre
import com.cody.haievents.phonepe.model.PaymentResponseGaneshTheatre


internal interface PhonePeRepository {
    suspend fun createGaneshTheatreOrder(request: PaymentRequestGaneshTheatre): Result<PaymentResponseGaneshTheatre>
}