package com.cody.haievents.auth.data

import com.cody.haievents.auth.data.model.EditUserRequest
import com.cody.haievents.auth.data.model.EditUserResponse
import com.cody.haievents.auth.data.model.GetUserResponse
import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.data.model.ProfileImageUploadResponse
import com.cody.haievents.auth.model.EditUserProfileRequest
import com.cody.haievents.auth.model.ForgetPasswordOTPTokenResponse
import com.cody.haievents.auth.model.ForgetPasswordOtpTokenRequest
import com.cody.haievents.auth.model.ForgetPasswordRequest
import com.cody.haievents.auth.model.ForgetPasswordResponse
import com.cody.haievents.auth.model.ProfileUpdateResponse
import com.cody.haievents.auth.model.SetNewPasswordRequest
import com.cody.haievents.auth.model.SetNewPasswordResponse
import com.cody.haievents.auth.model.TermsConditionsResponse
import com.cody.haievents.common.data.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders.ContentType
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.utils.io.core.* // ByteReadPacket

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

    suspend fun homePage(): HomePageResponse = client.get {
        endPoint(path = "/api/homepage")
    }.body()

    suspend fun homePage(token : String , request: EditUserRequest): EditUserResponse = client.post {
        endPoint(path = "/api/profile/update")
        setBody(request)
        setToken(token)

    }.body()


    suspend fun getUser(token : String): GetUserResponse = client.get {
        endPoint(path = "/api/profile")
        setToken(token)
    }.body()


    suspend fun updateUser(token : String, request: EditUserProfileRequest): ProfileUpdateResponse = client.post {
        endPoint(path = "/api/profile/update")
        setBody(request)
        setToken(token)
    }.body()

    suspend fun getTermsConditions(type :Int): TermsConditionsResponse = client.get {
        endPoint(path = "/api/page")
        parameter("type", type)

    }.body()

    suspend fun forgetPassword(request: ForgetPasswordRequest): HttpResponse {
        return client.post {
            setBody(request)
            endPoint(path = "/api/forgot-password")
        }
    }

    suspend fun forgetPasswordOtp(request: ForgetPasswordOtpTokenRequest)
    : HttpResponse = client.post {
        setBody(request)
        endPoint(path = "/api/verify-otp")
    }.body()

    suspend fun resetPassword(request: SetNewPasswordRequest): SetNewPasswordResponse = client.post {
        setBody(request)
        endPoint(path = "/api/reset-password")
    }.body()






}