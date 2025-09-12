package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.ForgetPasswordOTPTokenResponse
import com.cody.haievents.auth.model.ForgetPasswordOtpTokenRequest
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ForgetPasswordOtpUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    /**
     * Verifies the OTP for the forgot-password flow using the server-issued token.
     */
    suspend operator fun invoke(
        otp: String,
        token: String
    ): Result<ForgetPasswordOTPTokenResponse> {
        // Basic input validation (fail fast before hitting network)
        val trimmedOtp = otp.trim()
        val trimmedToken = token.trim()

        if (trimmedOtp.isEmpty() || !trimmedOtp.all { it.isDigit() } || trimmedOtp.length !in 4..8) {
            return Result.Error(message ="Please enter a valid OTP.")
        }
        if (trimmedToken.isEmpty()) {
            return Result.Error(message ="Invalid or missing token. Please request OTP again.")
        }

        return try {
            withContext(Dispatchers.IO) {
                // ❗ Use the repository method that actually verifies the OTP.
                // Rename this to match your repository contract if different.
                repository.verifyForgetPasswordOtp(
                    ForgetPasswordOtpTokenRequest(
                        otp = trimmedOtp,
                        token = trimmedToken
                    )
                )
            }
        } catch (t: Throwable) {
            Result.Error(message =t.message ?: "Something went wrong. Please try again.")
        }
    }
}
