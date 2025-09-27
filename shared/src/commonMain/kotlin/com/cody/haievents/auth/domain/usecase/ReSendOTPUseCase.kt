package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.OtpVerificationRequest
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.ReSendOTPRequest
import com.cody.haievents.auth.model.ReSendOTPResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class ReSendOTPUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        token: String
    ): Result<ReSendOTPResponse> {




        val request = ReSendOTPRequest(

            token = token
        )

        return repository.reSendPassword(request)
    }
}