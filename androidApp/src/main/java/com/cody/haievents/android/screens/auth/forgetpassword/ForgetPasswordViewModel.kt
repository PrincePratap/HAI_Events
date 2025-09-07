package com.cody.haievents.android.screens.auth.forgetpassword

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.android.screens.auth.login.LoginUiState
import com.cody.haievents.auth.domain.usecase.LogInUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class ForgetPasswordViewModel(
    private val useCase: LogInUseCase
): ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun login() {
        Log.d("LoginViewModel", "Login started with email=${uiState.identifiers}, password=${uiState.password}")

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val authResultData = useCase(uiState.identifiers, uiState.password)

            uiState = when (authResultData) {
                is Result.Error -> {
                    Log.e("LoginViewModel", "Login failed: ${authResultData.message}")
                    uiState.copy(
                        isLoading = false,
                        errorMessage = authResultData.message
                    )
                }

                is Result.Success -> {
                    Log.d("LoginViewModel", "Login successful!")
                    uiState.copy(
                        isLoading = false,
                        succeed = true
                    )
                }
            }
        }
    }

    fun updateIdentifiers(input: String) {
        Log.d("LoginViewModel", "Email updated: $input")
        uiState = uiState.copy(identifiers = input)
    }


}