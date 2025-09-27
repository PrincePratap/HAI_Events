package com.cody.haievents.phonepe.domain.usecase

import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.domain.repository.PhonePeRepository
import com.cody.haievents.phonepe.model.BuyTicketRequest
import com.cody.haievents.phonepe.model.BuyTicketResponse
import com.cody.haievents.phonepe.model.TicketLine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BuyTicketUseCase : KoinComponent {
    private val repository: PhonePeRepository by inject()

    suspend operator fun invoke(
        itemId: Int,
        itemType: String = "movie",
        tickets: List<TicketLine>
    ): Result<BuyTicketResponse> {







        // ✅ Build request
        val request = BuyTicketRequest(
            itemId = itemId,
            itemType = itemType,
            tickets = tickets
        )

        return repository.  buyTicket(request)


    }
}