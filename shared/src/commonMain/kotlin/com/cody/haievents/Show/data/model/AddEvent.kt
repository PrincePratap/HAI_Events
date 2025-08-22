package com.cody.haievents.Show.data.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserEventRequest(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("organizer_name") val organizerName: String,
    @SerialName("email") val email: String,
    @SerialName("location") val location: String,
    @SerialName("date") val date: String,   // e.g. "2025-08-10"
    @SerialName("time") val time: String,   // e.g. "18:00" or "6:30 PM"
    @SerialName("account_holder") val accountHolder: String,
    @SerialName("bank_name") val bankName: String,
    @SerialName("ifsc_code") val ifscCode: String,
    @SerialName("account_number") val accountNumber: String,
    @SerialName("bank_phone_number") val bankPhoneNumber: String,
    @SerialName("ticket_types") val ticketTypes: List<TicketTypeRequest>
)

@Serializable
data class TicketTypeRequest(
    @SerialName("name") val name: String,
    @SerialName("price") val price: Int,
    @SerialName("role") val role: Role,
    @SerialName("quantity") val quantity: Int
)

@Serializable
enum class Role {
    @SerialName("performer") PERFORMER,
    @SerialName("attendee") ATTENDEE
}



@Serializable
data class CreateUserEventResponse(
    @SerialName("message") val message: String,
    @SerialName("event") val event: CreatedEvent
)

@Serializable
data class CreatedEvent(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("slug") val slug: String,
    @SerialName("description") val description: String,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("organizer_name") val organizerName: String,
    @SerialName("email") val email: String,
    @SerialName("location") val location: String,
    @SerialName("date") val date: String,              // e.g. "2025-08-10"
    @SerialName("time") val time: String,              // e.g. "18:00"
    @SerialName("is_approved") val isApprovedInt: Int, // 0/1
    @SerialName("account_holder") val accountHolder: String,
    @SerialName("bank_name") val bankName: String,
    @SerialName("ifsc_code") val ifscCode: String,
    @SerialName("account_number") val accountNumber: String,
    @SerialName("bank_phone_number") val bankPhoneNumber: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("ticket_types") val ticketTypes: List<CreatedTicketType>
)

@Serializable
data class CreatedTicketType(
    @SerialName("id") val id: Int,
    @SerialName("event_id") val eventId: Int,
    @SerialName("event_source") val eventSource: String,   // e.g. "event"
    @SerialName("role_type") val roleType: String,         // e.g. "attendee"
    @SerialName("name") val name: String,
    @SerialName("price") val price: String,                // API returns "200.00"
    @SerialName("quantity") val quantity: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)
