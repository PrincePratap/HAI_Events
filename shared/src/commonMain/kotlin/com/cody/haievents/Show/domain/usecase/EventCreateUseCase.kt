package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.common.util.Result

class EventCreateUseCase {

    // Basic validators
    private val ifscRegex = Regex("^[A-Z]{4}0[A-Z0-9]{6}\$") // e.g., HDFC0001234
    private val nameRegex = Regex("^[A-Za-z][A-Za-z .'-]{2,50}\$") // letters & spaces
    private val phoneRegexIndia = Regex("^[6-9]\\d{9}\$") // 10-digit Indian mobile

    operator fun invoke(
        payload: CreateUserEventRequest,
        accountHolderName: String,
        bankName: String,
        ifscCode: String,
        phoneNumber: String
    ): Result<CreateUserEventRequest> {

        val holder = accountHolderName.trim()
        val bank   = bankName.trim()
        val ifsc   = ifscCode.trim().uppercase()
        val phone  = phoneNumber.trim().replace(" ", "").replace("-", "")

        // ---- Validations ----
        if (holder.isBlank()) {
            return Result.Error(message = "Account holder name is required")
        }
        if (!nameRegex.matches(holder)) {
            return Result.Error(message = "Enter a valid account holder name")
        }

        if (bank.isBlank()) {
            return Result.Error(message = "Bank name is required")
        }

        if (ifsc.length != 11 || !ifscRegex.matches(ifsc)) {
            return Result.Error(message = "Enter a valid IFSC (11 chars, e.g. HDFC0001234)")
        }

        if (phone.isBlank()) {
            return Result.Error(message = "Bank phone number is required")
        }
        if (!phoneRegexIndia.matches(phone)) {
            return Result.Error(message = "Enter a valid 10-digit phone number")
        }

        // ---- Build sanitized payload ----
        val updatedPayload = payload.copy(
            accountHolder = holder,
            bankName = bank,
            ifscCode = ifsc,
            bankPhoneNumber = phone
        )

        return Result.Success(updatedPayload)
    }
}
