package com.cody.haievents.phonepe.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhonePeTicketRequest(
    val amount: Int,
    val tickets: List<Ticket>
)

@Serializable
    data class Ticket(
    @SerialName("ticket_type_id")
    val ticketTypeId: Int,
        val quantity: Int
)