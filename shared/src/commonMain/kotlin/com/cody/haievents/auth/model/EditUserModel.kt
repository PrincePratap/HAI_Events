package com.cody.haievents.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class EditUserProfileRequest(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("dob") val dob: String,
    @SerialName("address") val address: String,
    @SerialName("zip") val zip: String,
    @SerialName("image") val image: String
)




@Serializable
data class ProfileUpdateResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: UserData
)

@Serializable
data class UserData(
    @SerialName("id") val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("email") val email: String,
    @SerialName("detail") val detail: UserDetail
)

@Serializable
data class UserDetail(
    @SerialName("dob") val dob: String,
    @SerialName("telephone") val telephone: String,
    @SerialName("image") val image: String,
    @SerialName("address") val address: String,
    @SerialName("zip") val zip: String
)
