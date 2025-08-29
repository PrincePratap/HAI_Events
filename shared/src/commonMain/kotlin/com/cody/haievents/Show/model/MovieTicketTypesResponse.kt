package com.cody.haievents.Show.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieTicketTypesResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("movie_id") val movieId: Int,
    @SerialName("ticket_types") val ticketTypes: List<TicketTypes>
)

@Serializable
data class TicketTypes(
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