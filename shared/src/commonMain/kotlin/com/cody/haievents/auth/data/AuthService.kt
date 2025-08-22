package com.cody.haievents.auth.data

import com.cody.haievents.auth.data.model.EditUserRequest
import com.cody.haievents.auth.data.model.EditUserResponse
import com.cody.haievents.auth.data.model.GetUserResponse
import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.data.model.ProfileImageUploadResponse
import com.cody.haievents.common.data.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders.ContentType
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
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

//    suspend fun uploadImage(
//        token: String,
//        imageBytes: ByteArray,
//        fileName: String,
//        fieldName: String = "image",            // change if your backend expects a different key
//    ): ProfileImageUploadResponse {
//        val contentType = when (fileName.substringAfterLast('.', "").lowercase()) {
//            "png" -> ContentType.Image.PNG
//            "jpg", "jpeg" -> ContentType.Image.JPEG
//            "webp" -> ContentType.parse("image/webp")
//            else -> ContentType.Application.OctetStream
//        }
//
//        return client.post {
//            endPoint(path = "/api/profile/upload-image")       // your helper
//            setToken(token)          // your helper (e.g., adds Authorization header)
//
//            setBody(
//                MultiPartFormDataContent(
//                    formData {
//                        append(
//                            key = fieldName,
//                            value = ByteReadPacket(imageBytes),
//                            headers = Headers.build {
//                                append(
//                                    HttpHeaders.ContentDisposition,
//                                    "form-data; name=\"$fieldName\"; filename=\"$fileName\""
//                                )
//                                append(HttpHeaders.ContentType, contentType.toString())
//                            }
//                        )
//                    }
//                )
//            )
//        }.body()
//    }






}