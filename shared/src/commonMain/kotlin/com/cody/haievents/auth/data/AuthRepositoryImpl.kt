package com.cody.haievents.auth.data

import com.cody.haievents.auth.data.model.EditUserRequest
import com.cody.haievents.auth.data.model.EditUserResponse
import com.cody.haievents.auth.data.model.GetUserResponse
import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.model.EditUserProfileRequest
import com.cody.haievents.auth.model.ForgetPasswordOTPTokenResponse
import com.cody.haievents.auth.model.ForgetPasswordOtpTokenRequest
import com.cody.haievents.auth.model.ForgetPasswordRequest
import com.cody.haievents.auth.model.ForgetPasswordResponse
import com.cody.haievents.auth.model.ProfileUpdateResponse
import com.cody.haievents.auth.model.SetNewPasswordRequest
import com.cody.haievents.auth.model.SetNewPasswordResponse
import com.cody.haievents.auth.model.TermsConditionsResponse
import com.cody.haievents.common.data.local.UserPreferences
import com.cody.haievents.common.data.local.toUserSettings
import com.cody.haievents.common.util.DispatcherProvider
import kotlinx.coroutines.withContext
import com.cody.haievents.common.util.Result
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText

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

    override suspend fun getUser(): Result<GetUserResponse> {
        return withContext(dispatcher.io) {
            try {
                val token = userPreferences.getUserData().token
                val response = authService.getUser(token)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to get homepage: ${e.message}")
            }
        }
    }

    override suspend fun updateUser(request: EditUserProfileRequest): Result<ProfileUpdateResponse> {
        return withContext(dispatcher.io) {
            try {
                val token = userPreferences.getUserData().token
                val response = authService.updateUser(token, request)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to update user : ${e.message}")
            }
        }
    }

    override suspend fun getTermsConditions(type : Int): Result<TermsConditionsResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.getTermsConditions(type)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to get terms conditions and privacy policy : ${e.message}")
            }
        }
    }

    override suspend fun forgetPassword(request: ForgetPasswordRequest): Result<ForgetPasswordResponse>{
        return withContext(dispatcher.io){
            try {
                val response = authService.forgetPassword(request)
                val statusCode = response.status.value

                if (statusCode == 200) {
                    Result.Success(response.body())
                }else if (statusCode == 404) {
                    Result.Error(message = "your email is not registered")
                }
                else {
                    Result.Error(message = "Error $statusCode: ${response.bodyAsText()}")
                }
            } catch (e: Exception) {
                Result.Error(message = "Network error: ${e.message}")
            }
        }
    }


    override suspend fun verifyForgetPasswordOtp(request: ForgetPasswordOtpTokenRequest): Result<ForgetPasswordOTPTokenResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.forgetPasswordOtp(request)
                val statusCode = response.status.value
                if (statusCode == 200) {
                    Result.Success(response.body())
                }else if (statusCode == 401) {
                    Result.Error(message = "Invalid OTP")
                }
                else {
                    Result.Error(message = "Error $statusCode: ${response.bodyAsText()}")
                }
            } catch (e: Exception) {
                Result.Error(message = "Network error: ${e.message}")
            }
        }
    }

    override suspend fun resetPassword(request: SetNewPasswordRequest): Result<SetNewPasswordResponse>{
        return withContext(dispatcher.io){
            try {
                val response = authService.resetPassword(request)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to reset password: ${e.message}")
            }
        }
    }


}
