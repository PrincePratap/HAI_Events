package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.domain.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.cody.haievents.common.util.Result


class HomePageUseCase : KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
    ): Result<HomePageResponse> {


        return repository.homePage()
    }
}