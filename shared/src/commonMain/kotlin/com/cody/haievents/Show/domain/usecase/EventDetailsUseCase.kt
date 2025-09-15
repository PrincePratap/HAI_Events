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
    ): Result<CreateUserEventRequest> {

        // -------- validations --------
        if (eventTitle.isBlank()) return Result.Error(message ="Event title cannot be empty")
        if (eventTitle.trim().length < 3) return Result.Error(message ="Event title should be at least 3 characters")

        if (organiserName.isBlank()) return Result.Error(message ="Organiser name cannot be empty")

//        if (!isValidEmail(contactEmail)) return Result.Error(message ="Please enter a valid email address")

        if (eventLocation.isBlank()) return Result.Error(message ="Event location cannot be empty")

        if (eventDescription.isBlank()) return Result.Error(message ="Event description cannot be empty")
        if (eventDescription.trim().length < 10) return Result.Error(message ="Event description should be at least 10 characters")







        // -------- build sanitized payload (step 1 only) --------
        val payload = CreateUserEventRequest(
            title = eventTitle.trim(),
            description = eventDescription.trim(),
             organizerName = organiserName.trim(),
            email = contactEmail.trim(),
            location = eventLocation.trim(),
            date = eventDate,             // normalized to YYYY-MM-DD
            time = eventTime,             // "HH:mm" or "HH:mm - HH:mm"
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
