package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.data.model.ShowDetailPageResponse
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class ShowDetailUseCase : KoinComponent {
    private val repository: ShowRepository by inject()

    suspend operator fun invoke(
        showId: Int,

        ): Result<ShowDetailPageResponse> {


        return repository.showDetails(showId)
    }
}