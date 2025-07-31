package com.cody.haievents.auth.domain.model

data class AuthResultData(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val token: String
)