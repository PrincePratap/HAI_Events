package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.ChangePasswordRequest
import com.cody.haievents.auth.data.ChangePasswordResponse
import com.cody.haievents.auth.domain.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.cody.haievents.common.util.Result


class NewPasswordUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        currentPassword: String,
        newPassword: String
    ): Result<ChangePasswordResponse> {

        val trimmedCurrent = currentPassword.trim()
        val trimmedNew = newPassword.trim()

        // Validate current password
        if (trimmedCurrent.length < 6) {
            return Result.Error(message = "Current password must be at least 6 characters.")
        }

        // Validate new password
        if (trimmedNew.length < 6) {
            return Result.Error(message = "New password must be at least 6 characters.")
        }

        // Check if current and new passwords are the same
        if (trimmedCurrent == trimmedNew) {
            return Result.Error(message = "New password must be different from current password.")
        }

        val request = ChangePasswordRequest(
            currentPassword = trimmedCurrent,
            newPassword = trimmedNew
        )

        return repository.changePassword(request)
    }
}