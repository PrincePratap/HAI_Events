package com.cody.haievents.auth.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SetNewPasswordRequest(
    @SerialName("token") val token: String,
    @SerialName("password") val password: String
)

@Serializable
data class SetNewPasswordResponse(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String
)