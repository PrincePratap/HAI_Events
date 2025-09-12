package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.LoginRequest
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.ForgetPasswordRequest
import com.cody.haievents.auth.model.ForgetPasswordResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class ForgetPasswordUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(email: String): Result<ForgetPasswordResponse> {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isEmpty()) {
            return Result.Error(message = "Please enter your email.")
        }

        if (!isValidEmail(trimmedEmail)) {
            return Result.Error(message = "Invalid email format.")
        }

        return try {
            repository.forgetPassword(
                ForgetPasswordRequest(email = trimmedEmail)
            )
        } catch (t: Throwable) {
            Result.Error(message = t.message ?: "Something went wrong. Please try again.")
        }


    }
    private fun isValidEmail(s: String): Boolean {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return regex.matches(s)
    }
}