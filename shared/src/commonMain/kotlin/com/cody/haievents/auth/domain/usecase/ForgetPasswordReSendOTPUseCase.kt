package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.ForgetPasswordReSendOTPResponse
import com.cody.haievents.auth.model.ReSendOTPRequest
import com.cody.haievents.auth.model.ReSendOTPResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ForgetPasswordReSendOTPUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        token: String
    ): Result<ForgetPasswordReSendOTPResponse> {




        val request = ReSendOTPRequest(
            token = token
        )

        return repository.forgetPasswordReSendPassword(request)
    }
}