package com.cody.haievents.Show.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowEventRequest(
    val showId: Int,
    val userToken: String
)

@Serializable
data class ShowEventResponse(
    val status: Boolean,
    val event: Event
)

@Serializable
data class Event(
    val id: Int,
    val title: String,
    val slug: String,
    val language: String,
    @SerialName("image_path")
    val imagePath: String,
    val rating: Int,
    val votes: Int,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("category_id")
    val categoryId: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val category: Category,
    @SerialName("ticket_types")
    val ticketTypes: List<TicketType>,
    val detail: Detail
)

@Serializable
data class Category(
    val id: Int,
    val name: String,
    @SerialName("image_path")
    val imagePath: String,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)

// The object model for items in the 'ticket_types' array
@Serializable
data class TicketType(
    val id: Int,
    @SerialName("event_id")
    val eventId: Int,
    @SerialName("event_source")
    val eventSource: String,
    @SerialName("role_type")
    val roleType: String,
    val name: String,
    val price: String, // Kept as String to match "2.00" format
    val quantity: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

// The nested 'detail' object
@Serializable
data class Detail(
    val id: Int,
    @SerialName("movie_id")
    val movieId: Int,
    val summary: String,
    @SerialName("time_range")
    val timeRange: String
)
