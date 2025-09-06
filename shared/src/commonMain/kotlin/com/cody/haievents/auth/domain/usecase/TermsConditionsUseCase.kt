package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.OtpVerificationRequest
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.TermsConditionsResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class TermsConditionsUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        type : Int,
    ): Result<TermsConditionsResponse> {




        return repository.getTermsConditions(type)
    }
}
