package com.cody.haievents.auth.data

import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.data.local.UserPreferences
import com.cody.haievents.common.data.local.toUserSettings
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

    override suspend fun otpVerification(otpRequest: OtpVerificationRequest): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.otpVerification(otpRequest)

                val authData = response.toAuthResultData()
                userPreferences.setUserData(authData.toUserSettings())

                Result.Success(data = authData)
            } catch (e: Exception) {
                Result.Error(message = "OTP verification failed: ${e.message}")
            }
        }
    }

    override suspend fun loginUser(loginRequest: LoginRequest): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val authResponse = authService.login(loginRequest)
                if (authResponse == null) {
                    Result.Error(
                        message = "Unknown error occurred"
                    )
                } else {
                    userPreferences.setUserData(
                        authResponse.toAuthResultData().toUserSettings()
                    )
                    Result.Success(
                        data = authResponse.toAuthResultData()
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = "Oops, we could not send your request, try later! ${e.message}"
                )
            }
        }
    }

    override suspend fun changePassword(request: ChangePasswordRequest): Result<ChangePasswordResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.changePassword(request)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun homePage(): Result<HomePageResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.homePage()
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to get homepage: ${e.message}")
            }
        }
    }


}
