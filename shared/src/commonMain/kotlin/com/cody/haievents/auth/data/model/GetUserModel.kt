package com.cody.haievents.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class GetUserResponse(
    val status: Boolean,
    val data: AuthData? = null
)

@Serializable
data class AuthData(
    val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name")  val lastName: String,
    val email: String,
    val detail: AuthDetail? = null,
    val authorization: String
)

@Serializable
data class AuthDetail(
    val dob: String? = null,        // "1999-08-15"
    val telephone: String? = null,
    val image: String? = null,
    val address: String? = null,
    val zip: String? = null
)
