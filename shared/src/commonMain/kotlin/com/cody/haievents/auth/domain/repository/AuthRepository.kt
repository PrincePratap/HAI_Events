package com.cody.haievents.auth.domain.repository

import com.cody.haievents.auth.data.ChangePasswordRequest
import com.cody.haievents.auth.data.ChangePasswordResponse
import com.cody.haievents.auth.data.HomepageResponse
import com.cody.haievents.auth.data.LoginRequest
import com.cody.haievents.auth.data.LoginResponse
import com.cody.haievents.auth.data.OTPSuccessResponse
import com.cody.haievents.auth.data.OtpVerificationRequest
import com.cody.haievents.auth.data.RegisterRequest
import com.cody.haievents.auth.data.RegisterResponse
import com.cody.haievents.common.util.Result


internal interface AuthRepository {

    suspend fun registerUser(registerRequest: RegisterRequest): Result<RegisterResponse>
    suspend fun otpVerification(otpRequest: OtpVerificationRequest): Result<OTPSuccessResponse>
    suspend fun loginUser(loginRequest: LoginRequest): Result<LoginResponse>
    suspend fun changePassword(request: ChangePasswordRequest): Result<ChangePasswordResponse>
    suspend fun homePage(): Result<HomepageResponse>








}