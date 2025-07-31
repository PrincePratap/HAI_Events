package com.cody.haievents.auth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


// Login Response

@Serializable
data class LoginResponse(
    @SerialName("message") val message: String,
    @SerialName("token") val token: String,
    @SerialName("user") val user: User
)

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("email") val email: String,
    @SerialName("email_verified_at") val emailVerifiedAt: String? = null,
    @SerialName("api_token") val apiToken: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("role") val role: String
)


// Login Request
@Serializable
data class LoginRequest(
    @SerialName("login") val login: String,
    @SerialName("password") val password: String
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