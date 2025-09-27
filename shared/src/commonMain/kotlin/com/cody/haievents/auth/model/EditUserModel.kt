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
    val status: Boolean? = null,
    val message: String? = null,
    val data: UserData? = null
)

@Serializable
data class UserData(
    val id: Int? = null,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("last_name")  val lastName: String? = null,
    val email: String? = null,
    val detail: UserDetail? = null,
    val authorization: String? = null
)

@Serializable
data class UserDetail(
    val dob: String? = null,        // "1999-08-15"
    val telephone: String? = null,
    val image: String? = null,
    val address: String? = null,
    val zip: String? = null
)
