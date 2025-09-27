package com.cody.haievents.auth.data

import com.cody.haievents.auth.data.model.GetUserResponse
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.model.ProfileUpdateResponse

internal fun LoginResponse.toAuthResultData(): AuthResultData {
    return AuthResultData(
        id = user.id.toString(),
        name = "${user.firstName} ${user.lastName}",
        email = user.email,
        phone = user.telephone,
        token = token
    )
}

internal fun OTPSuccessResponse.toAuthResultData(): AuthResultData {
    return AuthResultData(
        id = user.id.toString(),
        name = "${user.firstName} ${user.lastName}",
        email = user.email,
        phone = user.telephone,
        token = token
    )
}

internal fun ProfileUpdateResponse.toAuthResultData(): AuthResultData {
    return AuthResultData(
        id = data?.id.toString(),
        name = "${data?.firstName} ${data?.lastName}",
        email = data?.email ?: "",
        phone = data?.detail?.telephone ?: "",
        token = data?.authorization ?: ""
    )
}
internal fun GetUserResponse.toAuthResultData(): AuthResultData {
    return AuthResultData(
        id = data?.id.toString(),
        name = "${data?.firstName} ${data?.lastName}",
        email = data?.email ?: "",
        phone = data?.detail?.telephone ?: "",
        token = data?.authorization ?: ""
    )
}
