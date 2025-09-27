package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.Show.model.MyTicketListResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MyTicketListUseCase : KoinComponent {
    private val repository: ShowRepository by inject()

    suspend operator fun invoke(

    ): Result<MyTicketListResponse> {


        return repository.myTicketList()
    }
}