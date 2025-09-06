package com.cody.haievents.auth.domain.repository

import com.cody.haievents.auth.data.ChangePasswordRequest
import com.cody.haievents.auth.data.ChangePasswordResponse
import com.cody.haievents.auth.data.LoginRequest
import com.cody.haievents.auth.data.LoginResponse
import com.cody.haievents.auth.data.OTPSuccessResponse
import com.cody.haievents.auth.data.OtpVerificationRequest
import com.cody.haievents.auth.data.RegisterRequest
import com.cody.haievents.auth.data.RegisterResponse
import com.cody.haievents.auth.data.model.EditUserRequest
import com.cody.haievents.auth.data.model.EditUserResponse
import com.cody.haievents.auth.data.model.GetUserResponse
import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.model.EditUserProfileRequest
import com.cody.haievents.auth.model.ProfileUpdateResponse
import com.cody.haievents.auth.model.TermsConditionsResponse
import com.cody.haievents.common.util.Result


internal interface AuthRepository {
    suspend fun registerUser(registerRequest: RegisterRequest): Result<RegisterResponse>
    suspend fun otpVerification(otpRequest: OtpVerificationRequest): Result<AuthResultData>
    suspend fun loginUser(loginRequest: LoginRequest): Result<AuthResultData>
    suspend fun changePassword(request: ChangePasswordRequest): Result<ChangePasswordResponse>
    suspend fun homePage(): Result<HomePageResponse>
    suspend fun getUser(): Result<GetUserResponse>
    suspend fun updateUser(request: EditUserProfileRequest): Result<ProfileUpdateResponse>
    suspend fun getTermsConditions(type : Int): Result<TermsConditionsResponse>








}