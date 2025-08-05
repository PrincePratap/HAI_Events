package com.cody.haievents.auth.data

import com.cody.haievents.common.data.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class AuthService: KtorApi() {

    suspend fun register(request: RegisterRequest): RegisterResponse = client.post {
        endPoint(path = "/api/register/send-otp")
        setBody(request)
    }.body()

    suspend fun otpVerification(request: OtpVerificationRequest): OTPSuccessResponse = client.post {
        endPoint(path = "/api/register/verify-otp")
        setBody(request)
    }.body()

    suspend fun login(request: LoginRequest): LoginResponse = client.post {
        endPoint(path = "/api/login")
        setBody(request)
    }.body()


    suspend fun changePassword(request: ChangePasswordRequest): ChangePasswordResponse = client.post {
        endPoint(path = "/api/change-password")
        setBody(request)
    }.body()

    suspend fun homePage(): HomepageResponse = client.get {
        endPoint(path = "/api/homepage")
    }.body()



}