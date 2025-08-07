package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.data.ShowEventResponse
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.auth.data.HomepageResponse
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class ShowDetailUseCase : KoinComponent {
    private val repository: ShowRepository by inject()

    suspend operator fun invoke(
        showId: Int,

        ): Result<ShowEventResponse> {


        return repository.showDetails(showId)
    }
}