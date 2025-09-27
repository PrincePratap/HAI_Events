package com.cody.haievents.Show.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyTicketDetails(
    val status: Boolean,
    val booking: MyTicketItem
)

@Serializable
data class MyTicketItem(
    val type: String,       // e.g., "movie"
    val data: MyTicketData
)

@Serializable
data class MyTicketData(
    val id: Int,
    val title: String,
    val slug: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("category_id") val categoryId: Int,
    val date: String,
    val time: String,
    val summary: String,
    val venue: String,
    @SerialName("ticket_name") val ticketName: String,
    @SerialName("ticket_price") val ticketPrice: String, // "1.00"
    @SerialName("purchased_qty") val purchasedQty: Int,
    @SerialName("total_price") val totalPrice: Int
)
