package com.cody.haievents.Show.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyTicketListResponse(
    val status: Boolean,
    val bookings: List<BookingItem>
)

@Serializable
data class BookingItem(
    val type: String,          // e.g., "movie"
    val data: BookingData
)

@Serializable
data class BookingData(
    val id: Int,
    val title: String,
    val slug: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("category_id") val categoryId: Int,
    val date: String,          // "14 Dec, Sunday"
    val time: String,          // "10:00 AM – 3:00 PM"
    val summary: String,
    val venue: String
)
