package com.cody.haievents.auth.data

import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.data.local.UserPreferences
import com.cody.haievents.common.util.DispatcherProvider
import kotlinx.coroutines.withContext
import com.cody.haievents.common.util.Result

internal class AuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val authService: AuthService,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override suspend fun registerUser(registerRequest: RegisterRequest): Result<RegisterResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.register(registerRequest)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun otpVerification(otpRequest: OtpVerificationRequest): Result<OTPSuccessResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.otpVerification(otpRequest)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun loginUser(loginRequest: LoginRequest): Result<LoginResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.login(loginRequest)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun changePassword(request: ChangePasswordRequest): Result<ChangePasswordResponse>{
        return withContext(dispatcher.io) {
            try {
                val response = authService.changePassword(request)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun homePage(): Result<HomepageResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.homePage()
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }


}
