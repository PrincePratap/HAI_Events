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
    @SerialName("status")
    val status: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("token")
    val token: String
)




