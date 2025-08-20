package com.cody.haievents.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EditUserRequest(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("dob") val dob: String,
    @SerialName("telephone") val telephone: String,
    @SerialName("address") val address: String,
    @SerialName("zip") val zip: String
)



@Serializable
data class EditUserResponse(
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
    @SerialName("email_verified_at") val emailVerifiedAt: String? = null,
    @SerialName("api_token") val apiToken: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("role") val role: String,
    @SerialName("detail") val detail: UserDetail
)

@Serializable
data class UserDetail(
    @SerialName("id") val id: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("dob") val dob: String,
    @SerialName("telephone") val telephone: String,
    @SerialName("image") val image: String,
    @SerialName("address") val address: String,
    @SerialName("zip") val zip: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)
