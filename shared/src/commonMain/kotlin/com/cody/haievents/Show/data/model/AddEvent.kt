package com.cody.haievents.Show.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddEventRequest(
    @SerialName("api_token") val apiToken: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("organizer_name") val organizerName: String,
    @SerialName("email") val email: String,
    @SerialName("location") val location: String,
    @SerialName("date") val date: String,
    @SerialName("time") val time: String,
    @SerialName("account_holder") val accountHolder: String,
    @SerialName("bank_name") val bankName: String,
    @SerialName("ifsc_code") val ifscCode: String,
    @SerialName("account_number") val accountNumber: String,
    @SerialName("bank_phone_number") val bankPhoneNumber: String,
    @SerialName("ticket_types") val ticketTypes: List<addTicketType>
)

@Serializable
data class addTicketType(
    @SerialName("name") val name: String,
    @SerialName("price") val price: Int,
    @SerialName("role") val role: String,
    @SerialName("quantity") val quantity: Int
)




@Serializable
data class AddEventResponse(
    @SerialName("message") val message: String,
    @SerialName("event") val event: AddEventDetailResponse
)

@Serializable
data class AddEventDetailResponse(
    @SerialName("title") val title: String,
    @SerialName("slug") val slug: String,
    @SerialName("description") val description: String,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("organizer_name") val organizerName: String,
    @SerialName("email") val email: String,
    @SerialName("location") val location: String,
    @SerialName("date") val date: String,
    @SerialName("time") val time: String,
    @SerialName("is_approved") val isApproved: Int,
    @SerialName("account_holder") val accountHolder: String,
    @SerialName("bank_name") val bankName: String,
    @SerialName("ifsc_code") val ifscCode: String,
    @SerialName("account_number") val accountNumber: String,
    @SerialName("bank_phone_number") val bankPhoneNumber: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("id") val id: Int,
    @SerialName("ticket_types") val ticketTypes: List<TicketTypeResponse>
)

@Serializable
data class TicketTypeResponse(
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
