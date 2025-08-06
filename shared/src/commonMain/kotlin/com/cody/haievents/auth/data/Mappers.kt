package com.cody.haievents.auth.data

import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.domain.model.HomePageData

internal fun LoginResponse.toAuthResultData(): AuthResultData {
    return AuthResultData(id =user.id.toString(),name= "${user.firstName} ${user.lastName}" ,email = user.email, phone=  user.telephone, token = token)
}

internal fun OTPSuccessResponse.toAuthResultData(): AuthResultData {
    return AuthResultData(id =user.id.toString(),name= "${user.firstName} ${user.lastName}" ,email = user.email, phone= user.telephone, token = token)
}
