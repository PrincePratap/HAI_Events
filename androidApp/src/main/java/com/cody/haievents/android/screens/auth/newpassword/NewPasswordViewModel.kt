package com.cody.haievents.android.screens.auth.newpassword

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.auth.domain.usecase.ResetPasswordUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class NewPasswordViewModel(
    private val useCase: ResetPasswordUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "NewPasswordViewModel"
    }

    var uiState by mutableStateOf(NewPasswordUiState())
        private set

    /**
     * Tries to set the new password using a reset token obtained from previous steps (OTP flow).
     */
    fun resetPassword(token: String) {
        // ---- Local validations before making network call ----
        when {
            token.isBlank() -> {
                setError("Reset token is missing or invalid.")
                return
            }
            uiState.password.isBlank() || uiState.confirmPassword.isBlank() -> {
                setError("Password and confirm password cannot be empty.")
                return
            }
            uiState.password != uiState.confirmPassword -> {
                setError("Passwords do not match.")
                return
            }
            uiState.password.length < 8 -> {
                setError("Password must be at least 8 characters long.")
                return
            }
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            val result = useCase(
                token = token,
                password = uiState.password,
                confirmPassword = uiState.confirmPassword
            )

            uiState = when (result) {
                is Result.Error -> {
                    Log.e(TAG, "Reset password failed: ${result.message}")
                    uiState.copy(isLoading = false, errorMessage = result.message)
                }
                is Result.Success -> {
                    Log.d(TAG, "Reset password successful")
                    uiState.copy(isLoading = false, succeed = true)
                }
            }
        }
    }

    fun updatePassword(input: String) {
        Log.d(TAG, "Password updated")
        uiState = uiState.copy(password = input, errorMessage = null)
    }

    fun updateConfirmPassword(input: String) {
        Log.d(TAG, "Confirm password updated")
        uiState = uiState.copy(confirmPassword = input, errorMessage = null)
    }

    fun clearError() {
        uiState = uiState.copy(errorMessage = null)
    }

    private fun setError(msg: String) {
        Log.w(TAG, msg)
        uiState = uiState.copy(isLoading = false, errorMessage = msg, succeed = false)
    }
}

data class NewPasswordUiState(
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val succeed: Boolean = false
)
