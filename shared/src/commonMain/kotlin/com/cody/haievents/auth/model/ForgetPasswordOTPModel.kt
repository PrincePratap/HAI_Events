package com.cody.haievents.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordOtpTokenRequest(
    @SerialName("otp") val otp: String,
    @SerialName("token") val token: String
)




@Serializable
data class ForgetPasswordOTPTokenResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("code") val code: Int,
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
    @SerialName("telephone") val telephone: String
)
