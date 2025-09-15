package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.common.util.Result

class EventImageAndTicketsUseCase {

    operator fun invoke(
        payload: CreateUserEventRequest,
        imagePath: String,
        ticketTypes: List<TicketTypeRequest>,
    ): Result<CreateUserEventRequest> {

        // 1) Image required
        if (imagePath.isBlank()) {
            return Result.Error(message = "Event image is required")
        }

        // 2) At least one ticket
        if (ticketTypes.isEmpty()) {
            return Result.Error(message = "At least one ticket type is required")
        }

        // 3) Validate each ticket safely (no !!)
        ticketTypes.forEachIndexed { index, ticket ->
            val name = ticket.name?.trim().orEmpty()
            val price = ticket.price ?: -1
            val qty = ticket.quantity ?: -1

            if (name.isEmpty()) {
                return Result.Error(message = "Ticket #${index + 1} name is missing")
            }
            if (price <= 0) {
                return Result.Error(message = "Ticket #${index + 1} price must be greater than 0")
            }
            if (qty <= 0) {
                return Result.Error(message = "Ticket #${index + 1} quantity must be greater than 0")
            }
        }

        // 4) Build sanitized payload
        val updatedPayload = payload.copy(
            imagePath = imagePath,
            ticketTypes = ticketTypes
        )

        return Result.Success(updatedPayload)
    }
}

