package com.cody.haievents.Show.data

import com.cody.haievents.auth.data.ChangePasswordRequest
import com.cody.haievents.auth.data.ChangePasswordResponse
import com.cody.haievents.auth.data.HomepageResponse
import com.cody.haievents.auth.data.LoginRequest
import com.cody.haievents.auth.data.LoginResponse
import com.cody.haievents.auth.data.OTPSuccessResponse
import com.cody.haievents.auth.data.OtpVerificationRequest
import com.cody.haievents.auth.data.RegisterRequest
import com.cody.haievents.auth.data.RegisterResponse
import com.cody.haievents.common.data.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody


internal class ShowService: KtorApi() {

    suspend fun getShowDetail(showId: Int, userToken: String): ShowEventResponse = client.get {
        endPoint(path = "/api/event-detail")
        parameter("id", showId)
        parameter("api_token", userToken)
    }.body()

    suspend fun getTicketPrice(showId: Int,): getShowTicketResponse = client.get {
        endPoint(path = "/api/movie")
        parameter("id", showId)
    }.body()


}