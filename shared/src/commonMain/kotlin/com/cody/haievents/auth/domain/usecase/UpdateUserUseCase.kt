package com.cody.haievents.auth.domain.usecase

import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.EditUserProfileRequest
import com.cody.haievents.auth.model.ProfileUpdateResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateUserUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        dob: String,
        address: String,
        zip: String,
        image: String
    ): Result<ProfileUpdateResponse> {

        // --- Validate names ---
        if (firstName.isBlank() || lastName.isBlank()) {
            return Result.Error(message ="First name and last name cannot be empty.")
        }
        if (firstName.length < 2 || lastName.length < 2) {
            return Result.Error(message ="First and last name must have at least 2 characters.")
        }

        // --- Validate DOB (YYYY-MM-DD) ---
        val dobRegex = Regex("""^\d{4}-\d{2}-\d{2}$""")
        if (!dobRegex.matches(dob.trim())) {
            return Result.Error(message="Invalid date of birth. Use format YYYY-MM-DD.")
        }

        // --- Validate address ---
        if (address.isBlank()) {
            return Result.Error(message="Address cannot be empty.")
        }

        // --- Validate ZIP code (6 digits for India, adapt if needed) ---
        val zipRegex = Regex("""^\d{6}$""")
        if (!zipRegex.matches(zip.trim())) {
            return Result.Error(message="Invalid ZIP code. It should be 6 digits.")
        }

        // --- Validate image URL/path ---
//        if (image.isBlank()) {
//            return Result.Error(message="Profile image cannot be empty.")
//        }
//        val urlRegex = Regex("""^(https?://.*)|(uploads/.*)$""")
//        if (!urlRegex.matches(image.trim())) {
//            return Result.Error(message="Invalid image format. Must be a valid URL or upload path.")
//        }

        // --- Build request ---
        val request = EditUserProfileRequest(
            firstName = firstName.trim(),
            lastName = lastName.trim(),
            dob = dob.trim(),
            address = address.trim(),
            zip = zip.trim(),
            image = ""
        )

        // --- Call repository ---
        return repository.updateUser(request)
    }
}
