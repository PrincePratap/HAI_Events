package com.cody.haievents.phonepe.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BuyTicketRequest(
    @SerialName("item_id") val itemId: Int,
    @SerialName("item_type") val itemType: String,
    val tickets: List<TicketLine>
)

@Serializable
data class TicketLine(
    @SerialName("ticket_id") val ticketId: Int,
    val price: Double,   // 1.00, 2.00 etc.
    val quantity: Int
)




@Serializable
data class BuyTicketResponse(
    val status: Boolean,
    val message: String
)
