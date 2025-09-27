package com.cody.haievents.phonepe.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhonePeTicketRequestResponse(
    val success: Boolean,
    val merchantOrderId: String,
    val amount: Int,
    val tickets: List<TicketItem>,
    val order: Order
)

@Serializable
data class TicketItem(
    @SerialName("ticket_type_id")
    val ticketTypeId: Int,
    val name: String,
    val price: String,
    val quantity: Int,
    val subtotal: Int
)

@Serializable
data class Order(
    val orderId: String,
    val state: String,
    val expireAt: Long,
    val token: String
)
