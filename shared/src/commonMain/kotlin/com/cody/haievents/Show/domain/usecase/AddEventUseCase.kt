package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.data.model.AddEventRequest
import com.cody.haievents.Show.data.model.AddEventResponse
import com.cody.haievents.Show.data.model.OrderResponse
import com.cody.haievents.Show.data.model.addTicketType
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject




class AddEventUseCase : KoinComponent {

    private val repository: ShowRepository by inject()

    suspend operator fun invoke(
        apiToken: String,
        title: String,
        description: String,
        categoryId: Int,
        organizerName: String,
        email: String,
        location: String,
        date: String,              // "YYYY-MM-DD"
        time: String,              // "HH:mm" 24-hour
        accountHolder: String,
        bankName: String,
        ifscCode: String,
        accountNumber: String,
        bankPhoneNumber: String,
        ticketTypes: List<addTicketType>
    ): Result<AddEventResponse> {

        // --- Light validation (kept minimal; extend as needed) ---
        if (title.isBlank()) return Result.Error(message = "Title is required.")
        if (description.isBlank()) return Result.Error(message = "Description is required.")
        if (email.isBlank() || !email.contains("@")) return Result.Error(message = "Valid email is required.")
        if (ticketTypes.isEmpty()) return Result.Error(message = "At least one ticket type is required.")
        if (ticketTypes.any { it.price < 0 }) return Result.Error(message = "Ticket price cannot be negative.")
        if (ticketTypes.any { it.quantity <= 0 }) return Result.Error(message = "Ticket quantity must be > 0.")

        val req = AddEventRequest(
            apiToken = apiToken.trim(),
            title = title.trim(),
            description = description.trim(),
            categoryId = categoryId,
            organizerName = organizerName.trim(),
            email = email.trim(),
            location = location.trim(),
            date = date.trim(),
            time = time.trim(),
            accountHolder = accountHolder.trim(),
            bankName = bankName.trim(),
            ifscCode = ifscCode.trim(),
            accountNumber = accountNumber.trim(),
            bankPhoneNumber = bankPhoneNumber.trim(),
            ticketTypes = ticketTypes
        )

        // Delegate to repository
        return repository.addEvent(req)
    }

    /**
     * Overload: if you already built the request upstream.
     */
    suspend operator fun invoke(request: AddEventRequest): Result<AddEventResponse> {
        // You can still reuse validation if you want:
        if (request.title.isBlank()) return Result.Error(message = "Title is required.")
        if (request.description.isBlank()) return Result.Error(message = "Description is required.")
        if (request.email.isBlank() || !request.email.contains("@")) return Result.Error(message = "Valid email is required.")
        if (request.ticketTypes.isEmpty()) return Result.Error(message = "At least one ticket type is required.")
        if (request.ticketTypes.any { it.price < 0 }) return Result.Error(message = "Ticket price cannot be negative.")
        if (request.ticketTypes.any { it.quantity <= 0 }) return Result.Error(message = "Ticket quantity must be > 0.")

        return repository.addEvent(request)
    }
}
