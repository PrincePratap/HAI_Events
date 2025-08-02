package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.data.RegisterRequest
import com.cody.haievents.auth.data.RegisterResponse
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        telephone: String,
        password: String,
        passwordConfirmation: String
    ): Result<RegisterResponse> {

        // --- Validate names ---
        if (firstName.isBlank() || firstName.length < 2) {
            return Result.Error(message = "First name is required and must be at least 2 characters.")
        }

        if (lastName.isBlank() || lastName.length < 2) {
            return Result.Error(message = "Last name is required and must be at least 2 characters.")
        }

        // --- Validate email ---
        if (email.isBlank() || !email.contains("@")) {
            return Result.Error(message = "Invalid email address.")
        }

        // --- Optional: Validate phone number ---
        if (telephone.isBlank() || telephone.length < 10) {
            return Result.Error(message = "Invalid phone number.")
        }


        // --- Validate password ---
        if (password.length < 6) {
            return Result.Error(message = "Password must be at least 6 characters.")
        }

        if (password != passwordConfirmation) {
            return Result.Error(message = "Passwords do not match.")
        }



        val request = RegisterRequest(
            firstName = firstName,
            lastName = lastName,
            email = email,
            telephone = telephone,
            password = password,
            passwordConfirmation = passwordConfirmation
        )

        return repository.registerUser(request)
    }
}
