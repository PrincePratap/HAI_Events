package com.cody.haievents.auth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable






// Login Request
@Serializable
data class LoginRequest(
    @SerialName("login")
    val login: String,

    @SerialName("password")
    val password: String
)
@Serializable
data class LoginResponse(
    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String,

    @SerialName("user")
    val user: LoggedInUser
)

@Serializable
data class LoggedInUser(
    @SerialName("id")
    val id: Int,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    @SerialName("email")
    val email: String,

    @SerialName("telephone")
    val telephone: String
)


// register request
@Serializable
data class RegisterRequest(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("email") val email: String,
    @SerialName("telephone") val telephone: String,
    @SerialName("password") val password: String,
    @SerialName("password_confirmation") val passwordConfirmation: String
)

@Serializable
data class RegisterResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String
)

// OTP  request

@Serializable
data class OtpVerificationRequest(
    @SerialName("otp")
    val otp: String,

    @SerialName("token")
    val token: String
)

// Otp Response
@Serializable
data class OTPSuccessResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String,

    @SerialName("user")
    val user: RegisteredUser
)

@Serializable
data class RegisteredUser(
    @SerialName("id")
    val id: Int,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    @SerialName("email")
    val email: String,

    @SerialName("telephone")
    val telephone: String
)
// change Password
@Serializable
data class ChangePasswordRequest(
    @SerialName("current_password")
    val currentPassword: String,

    @SerialName("new_password")
    val newPassword: String
)


@Serializable
data class ChangePasswordResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("message")
    val message: String
)








