package com.cody.haievents.phonepe.domain.usecase

import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.domain.repository.PhonePeRepository
import com.cody.haievents.phonepe.model.PhonePeTicketRequest
import com.cody.haievents.phonepe.model.PhonePeTicketRequestResponse
import com.cody.haievents.phonepe.model.Ticket
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TicketPurchaseUseCase : KoinComponent {
    private val repository: PhonePeRepository by inject()

    suspend operator fun invoke(
        amount: Int,
        tickets: List<Ticket>,
    ): Result<PhonePeTicketRequestResponse> {

        // ✅ Validate amount
        if (amount <= 0) {
            return Result.Error(message = "Amount must be greater than 0")
        }

        // ✅ Validate tickets
        if (tickets.isEmpty()) {
            return Result.Error(message = "At least one ticket must be provided")
        }

        tickets.forEachIndexed { index, ticket ->
            if (ticket.ticketTypeId <= 0) {
                return Result.Error(message = "Ticket at index $index has invalid ticketTypeId")
            }
            if (ticket.quantity <= 0) {
                return Result.Error(message = "Ticket at index $index has invalid quantity")
            }

        }


        // ✅ Build request
        val request = PhonePeTicketRequest(
            amount = amount,
            tickets = tickets
        )

        return repository.  phonePeTicketPurchase(request)


    }
}
