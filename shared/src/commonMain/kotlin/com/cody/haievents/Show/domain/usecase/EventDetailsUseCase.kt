package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.common.util.Result
import kotlinx.datetime.LocalDate

class EventDetailsUseCase {

    operator fun invoke(
        eventTitle: String,
        organiserName: String,
        contactEmail: String,
        eventLocation: String,
        eventDate: String,
        eventTime: String,
        eventDescription: String,
        ticketTypesList: List<TicketTypeRequest>
    ): Result<CreateUserEventRequest> {

        // -------- validations --------
        if (eventTitle.isBlank()) return Result.Error(message ="Event title cannot be empty")
        if (eventTitle.trim().length < 3) return Result.Error(message ="Event title should be at least 3 characters")

        if (organiserName.isBlank()) return Result.Error(message ="Organiser name cannot be empty")

//        if (!isValidEmail(contactEmail)) return Result.Error(message ="Please enter a valid email address")

        if (eventLocation.isBlank()) return Result.Error(message ="Event location cannot be empty")

        if (eventDescription.isBlank()) return Result.Error(message ="Event description cannot be empty")
        if (eventDescription.trim().length < 10) return Result.Error(message ="Event description should be at least 10 characters")



        if (ticketTypesList.isEmpty()) return Result.Error(message ="Add at least one ticket type")

        // Validate & sanitize tickets (no !!; require role; trim name)
        val seen = mutableSetOf<String>()
        val cleanedTickets = mutableListOf<TicketTypeRequest>()

        ticketTypesList.forEachIndexed { idx, t ->
            val row = idx + 1
            val name = t.name?.trim().orEmpty()
            val qty  = t.quantity ?: 0
            val price = t.price ?: 0
            val role = t.role ?: return Result.Error(message ="Ticket #$row: role is required")

            if (name.isEmpty()) return Result.Error(message ="Ticket #$row: name cannot be empty")
            if (qty <= 0) return Result.Error(message ="Ticket #$row: quantity must be greater than 0")
            if (price < 0) return Result.Error(message ="Ticket #$row: price cannot be negative")

            val key = "${role.name}:${name.lowercase()}"
//            if (!seen.add(key)) {
//                return Result.Error(message ="Duplicate ticket for role '${pretty(role.name)}' with name '$name'")
//            }

            cleanedTickets += TicketTypeRequest(
                name = name,
                price = price,
                role = role,
                quantity = qty
            )
        }

        // -------- build sanitized payload (step 1 only) --------
        val payload = CreateUserEventRequest(
            title = eventTitle.trim(),
            description = eventDescription.trim(),
             organizerName = organiserName.trim(),
            email = contactEmail.trim(),
            location = eventLocation.trim(),
            date = "",             // normalized to YYYY-MM-DD
            time = "",             // "HH:mm" or "HH:mm - HH:mm"
            accountHolder = null,              // filled in Bank step
            bankName = null,
            ifscCode = null,
            accountNumber = null,
            bankPhoneNumber = null,
            ticketTypes = emptyList()
        )

        return Result.Success(payload)
    }


}
