package com.cody.haievents.android.screens.auth.forgetpassword

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.auth.domain.usecase.ForgetPasswordUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch


class ForgetPasswordViewModel(
    private val useCase: ForgetPasswordUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ForgetPasswordUiState())
        private set

    fun submitForgetPasswordRequest() {
        Log.d(TAG, "Forget password started with email=${uiState.email}")

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, succeed = false)

            val result = useCase(uiState.email)

            uiState = when (result) {
                is Result.Error -> {
                    Log.e(TAG, "Forget password failed: ${result.message}")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is Result.Success -> {
                    Log.d(TAG, "Forget password successful, response=${result.data}")
                    uiState.copy(
                        isLoading = false,
                        succeed = true,
                        token = result.data?.token ?: "",
                        email = result.data?.email ?: ""
                    )
                }
            }
        }
    }

    fun updateEmail(input: String){
        Log.d(TAG, "Email input updated: $input")
        uiState = uiState.copy(email = input)
    }

    companion object {
        private const val TAG = "ForgetPasswordViewModel"
    }
}


data class ForgetPasswordUiState(
    var email: String = "",
    var token: String = "",
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false
)