package com.cody.haievents.Show.data.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowDetailPageResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("event") val event: EventDetails,
    @SerialName("venue_is_nsui") val venueIsNsui: Boolean
)

@Serializable
data class EventDetails(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("slug") val slug: String,
    @SerialName("language") val language: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("rating") val rating: Int,
    @SerialName("votes") val votes: Int,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("category") val category: Category,
    @SerialName("ticket_types") val ticketTypes: List<TicketType>,
    @SerialName("detail") val detail: EventDetail
)

@Serializable
data class Category(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class TicketType(
    @SerialName("id") val id: Int,
    @SerialName("event_id") val eventId: Int,
    @SerialName("event_source") val eventSource: String,
    @SerialName("role_type") val roleType: String,
    @SerialName("name") val name: String,
    @SerialName("price") val price: String,
    @SerialName("quantity") val quantity: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class EventDetail(
    @SerialName("id") val id: Int,
    @SerialName("movie_id") val movieId: Int,
    @SerialName("summary") val summary: String,
    @SerialName("time_range") val timeRange: String,
    @SerialName("venue") val venue: String
)
