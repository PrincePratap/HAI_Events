package com.cody.haievents.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("data") val data: GetUserData
)

@Serializable
data class GetUserData(
    @SerialName("id") val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("email") val email: String,
    @SerialName("detail") val detail: GetUserDetail
)

@Serializable
data class GetUserDetail(
    @SerialName("dob") val dob: String? = null,
    @SerialName("telephone") val telephone: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("zip") val zip: String? = null
)
