package com.cody.haievents.auth.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordRequest(
    @SerialName("email")
    val email: String
)


@Serializable
data class ForgetPasswordResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String,

    @SerialName("email")
    val email: String
)
