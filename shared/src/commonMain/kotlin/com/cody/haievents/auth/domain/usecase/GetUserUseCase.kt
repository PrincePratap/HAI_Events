package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.model.GetUserResponse
import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class GetUserUseCase : KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
    ): Result<GetUserResponse> {

        return repository.getUser()
    }
}