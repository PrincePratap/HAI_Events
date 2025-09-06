package com.cody.haievents.phonepe.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable





@Serializable
data class PaymentRequestGaneshTheatre(
    val amount: Int,
    val meta: Meta
)

@Serializable
data class Meta(
    val seats: List<Seat>
)

@Serializable
data class Seat(
    val row: String,
    @SerialName("seat_number")
    val seatNumber: Int
)



@Serializable
data class PaymentResponseGaneshTheatre(
    val success: Boolean,
    val merchantOrderId: String,
    val order: Order
)

@Serializable
data class Order(
    val orderId: String,
    val state: String,
    val expireAt: Long,
    val token: String
)
