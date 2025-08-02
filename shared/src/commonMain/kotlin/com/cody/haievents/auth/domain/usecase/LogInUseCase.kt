package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.LoginRequest
import com.cody.haievents.auth.data.LoginResponse
import com.cody.haievents.auth.data.RegisterRequest
import com.cody.haievents.auth.data.RegisterResponse
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogInUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        emailOrPhone: String,
        password: String,
    ): Result<LoginResponse> {

        val trimmedInput = emailOrPhone.trim()
        val trimmedPassword = password.trim()

        // --- Check if input is email ---
        val isEmail = trimmedInput.contains("@")

        // --- Validate Email ---
        if (isEmail) {
            val emailPattern = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
            if (!emailPattern.matches(trimmedInput)) {
                return Result.Error(message = "Invalid email format.")
            }
        } else {
            // --- Validate Phone Number ---
            val phonePattern = Regex("^[0-9]{10}$")
            if (!phonePattern.matches(trimmedInput)) {
                return Result.Error(message = "Invalid phone number. It should be 10 digits.")
            }
        }

        // --- Validate password ---
        if (trimmedPassword.length < 6) {
            return Result.Error(message = "Password must be at least 6 characters.")
        }

        val request = LoginRequest(
            login = trimmedInput,
            password = trimmedPassword
        )

        return repository.loginUser(request)
    }
}
