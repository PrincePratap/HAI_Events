package com.cody.haievents.auth.data

import com.cody.haievents.auth.domain.model.AuthResultData

internal fun LoginResponse.toAuthResultData(): AuthResultData {
    return AuthResultData(id =user.id.toString(),name= "${user.firstName} ${user.lastName}" ,email = user.email, phone=  "", token = token)
}
//internal fun VerifyOtpResponse.toAuthResultData(): AuthResultData {
//    return AuthResultData(user.id,user.name, user.email, token)
//}
//internal fun LoginWithPhoneResponse .toAuthResultData(): AuthResultData {
//    return AuthResultData(user.id,user.name, user.email, token)
//}