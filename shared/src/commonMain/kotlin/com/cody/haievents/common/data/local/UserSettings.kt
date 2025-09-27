package com.cody.haievents.common.data.local

import com.cody.haievents.auth.domain.model.AuthResultData
import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val id: String ="",
    val name: String = "",
    val email: String = "",
    val token: String = "",
)

fun UserSettings.toAuthResultData(): AuthResultData {
    return AuthResultData(id, name, email,  ",",token)
}

fun AuthResultData.toUserSettings(): UserSettings {
    return UserSettings(id, name, email, token)
}