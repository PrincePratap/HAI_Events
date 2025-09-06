package com.cody.haievents.auth.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TermsConditionsResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("type")
    val type: String,
    @SerialName("content")
    val content: String
)
