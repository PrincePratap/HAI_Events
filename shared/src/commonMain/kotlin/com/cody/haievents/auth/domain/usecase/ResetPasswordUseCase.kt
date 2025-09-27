package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.SetNewPasswordRequest
import com.cody.haievents.auth.model.SetNewPasswordResponse
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ResetPasswordUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    /**
     * Completes the forgot-password flow by setting a new password using a server-issued token.
     * - Validates token and password inputs locally.
     * - Calls repository to perform the reset.
     */
    suspend operator fun invoke(
        token: String,
        password: String,
        confirmPassword: String
    ): Result<SetNewPasswordResponse> {

        // ---- Client-side validations ----
        if (token.isBlank()) {
            return Result.Error(message = "Reset token is missing or invalid.")
        }

        if (password.isBlank() || confirmPassword.isBlank()) {
            return Result.Error(message = "Password and confirm password cannot be empty.")
        }

        if (password != confirmPassword) {
            return Result.Error(message = "Passwords do not match.")
        }

        if (password.length < 8) {
            return Result.Error(message = "Password must be at least 8 characters long.")
        }



        return try {
            withContext(Dispatchers.IO) {
                repository.resetPassword(
                    SetNewPasswordRequest(
                        token = token,
                        password = password
                    )
                )
            }
        } catch (t: Throwable) {
            Result.Error(message = t.message ?: "Something went wrong. Please try again.")
        }
    }
}
