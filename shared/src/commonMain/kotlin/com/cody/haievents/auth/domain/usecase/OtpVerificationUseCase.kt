package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.OTPSuccessResponse


import com.cody.haievents.auth.data.OtpVerificationRequest
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OtpVerificationUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        otp: String,
        token: String
    ): Result<AuthResultData> {

        if (otp.length != 6 || !otp.all { it.isDigit() }) {
            return Result.Error(message = "Invalid OTP format")
        }

        if (token.isBlank()) {
            return Result.Error(message = "Missing verification token")
        }

        val request = OtpVerificationRequest(
            otp = otp,
            token = token
        )

        return repository.otpVerification(request)
    }
}
