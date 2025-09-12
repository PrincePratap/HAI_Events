package com.cody.haievents.android.screens.auth.ForgetPasswordOTP

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.auth.domain.usecase.OtpVerificationUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch




class ForgetPasswordOTPViewModel(
    private val useCase: OtpVerificationUseCase
) : ViewModel() {

    // 1. Consistent TAG for all logs in this class
    private val TAG = "OTPViewModel"

    var uiState by mutableStateOf(OtpUiState())
        private set

    // 2. Lifecycle Logging: Know when the ViewModel is created
    init {
        Log.d(TAG, "ViewModel initialized")
    }

    /**
     * Handles the event when the user inputs a new value in the OTP field.
     */
    fun onOtpValueChanged(newValue: String) {
        // We only want to accept digits and limit the length to 6
        if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
            Log.d(TAG, "OTP value updated to: $newValue")
            uiState = uiState.copy(otp = newValue)
        } else {
            Log.w(TAG, "Invalid OTP input ignored: $newValue")
        }
    }

    fun onErrorMessageShown() {
        Log.d(TAG, "Consuming error message event.")
        uiState = uiState.copy(errorMessage = null)
    }

    /**
     * Call this from the UI after successful navigation to prevent
     * re-triggering navigation.
     */
    fun onNavigationHandled() {
        Log.d(TAG, "Consuming success/navigation event.")
        uiState = uiState.copy(succeed = false)
    }

    /**
     * Submits the current OTP value for verification.
     */
    fun submitOTP(token: String) {
        // 3. Descriptive Action Logging: Clear log for what is about to happen
        Log.d(TAG, "submitOTP called. Verifying OTP: '${uiState.otp}' with token.")

        // Don't proceed if OTP is not fully entered
        if (uiState.otp.length != 6) {
            Log.w(TAG, "OTP submission cancelled: OTP is not 6 digits long.")
            return
        }

        viewModelScope.launch {
            // 4. State Change Logging: Announce state transitions
            Log.i(TAG, "State transition: isLoading -> true")
            uiState = uiState.copy(isLoading = true, errorMessage = null) // Clear previous errors

            val otpVerificationResult = useCase(otp = uiState.otp, token = token)

            uiState = when (otpVerificationResult) {
                is Result.Error -> {
                    Log.e(TAG, "OTP verification failed. Reason: ${otpVerificationResult.message}")
                    Log.i(TAG, "State transition: isLoading -> false, updating error message.")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = otpVerificationResult.message
                    )
                }

                is Result.Success -> {
                    Log.d(TAG, "OTP verification successful!")
                    Log.i(TAG, "State transition: isLoading -> false, succeed -> true.")
                    uiState.copy(
                        isLoading = false,
                        succeed = true
                    )
                }
            }
            Log.d(TAG, "submitOTP finished. Final UI State: $uiState")
        }
    }


}

data class OtpUiState(
    var otp: String = "",
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)