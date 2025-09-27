package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.Show.model.MyTicketDetails
import com.cody.haievents.Show.model.MyTicketListResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MyTicketDetailsUseCase : KoinComponent {
    private val repository: ShowRepository by inject()

    suspend operator fun invoke(
        ticketId: Int
    ): Result<MyTicketDetails> {


        return repository.myTicketDetails(ticketId)
    }
}