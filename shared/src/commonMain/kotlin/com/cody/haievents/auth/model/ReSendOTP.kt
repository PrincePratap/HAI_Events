package com.cody.haievents.auth.model


import kotlinx.serialization.Serializable

@Serializable
data class ReSendOTPResponse(
    val status: Boolean,
    val code: Int,
    val message: String,
    val token: String
)


@Serializable
data class ForgetPasswordReSendOTPResponse(
    val status: Boolean,
    val message: String,
    val token: String,
    val email: String
)


@Serializable
data class ReSendOTPRequest(
    val token: String
)