package com.cody.haievents.phonepe.domain.repository


import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.model.BuyTicketRequest
import com.cody.haievents.phonepe.model.BuyTicketResponse
import com.cody.haievents.phonepe.model.PaymentRequestGaneshTheatre
import com.cody.haievents.phonepe.model.PaymentResponseGaneshTheatre
import com.cody.haievents.phonepe.model.PhonePeTicketRequest
import com.cody.haievents.phonepe.model.PhonePeTicketRequestResponse


internal interface PhonePeRepository {
    suspend fun createGaneshTheatreOrder(request: PaymentRequestGaneshTheatre): Result<PaymentResponseGaneshTheatre>
    suspend fun phonePeTicketPurchase(request: PhonePeTicketRequest): Result<PhonePeTicketRequestResponse>
    suspend fun buyTicket(request: BuyTicketRequest): Result<BuyTicketResponse>
}