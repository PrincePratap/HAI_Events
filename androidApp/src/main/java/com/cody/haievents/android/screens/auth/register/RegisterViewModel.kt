package com.cody.haievents.android.screens.auth.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.android.screens.auth.login.LoginUiState
import com.cody.haievents.auth.domain.usecase.LogInUseCase
import com.cody.haievents.auth.domain.usecase.RegisterUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val useCase: RegisterUseCase
) : ViewModel(){

    var uiState by mutableStateOf(RegisterUiState())
        private set

    fun register() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val authResultData = useCase(firstName = uiState.firstName, lastName = uiState.lastName, email = uiState.email, telephone = uiState.telephone, password = uiState.password , passwordConfirmation = uiState.confirmPassword)

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
                        succeed = true,
                        verificationToken = authResultData.data?.token ?: ""
                    )
                }
            }
        }
    }


    fun updateFirstName(input: String) {
        Log.d("LoginViewModel", "Email updated: $input")
        uiState = uiState.copy(firstName = input)
    }
    fun updateLastName(input: String) {
        Log.d("LoginViewModel", "Email updated: $input")
        uiState = uiState.copy(lastName = input)
    }
    fun updateEmail(input: String) {
        Log.d("LoginViewModel", "Email updated: $input")
        uiState = uiState.copy(email = input)
    }
    fun updateTelephone(input: String) {
        Log.d("LoginViewModel", "Email updated: $input")
        uiState = uiState.copy(telephone = input)
    }
    fun updatePassword(input: String) {
        Log.d("LoginViewModel", "Email updated: $input")
        uiState = uiState.copy(password = input)
    }
    fun updateConfirmPassword(input: String) {
        Log.d("LoginViewModel", "Email updated: $input")
        uiState = uiState.copy(confirmPassword = input)
    }

    fun registrationHandled() {
        uiState = uiState.copy(succeed = false)
    }

    // This function resets the error message so the Toast doesn't re-appear.
    fun errorShown() {
        uiState = uiState.copy(errorMessage = null)
    }
    fun onNavigationHandled() {
        uiState = uiState.copy(succeed = false)
    }



}

data class RegisterUiState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var telephone: String = "",
    val password : String = "",
    val confirmPassword : String = "",
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var succeed: Boolean = false,
    var verificationToken  : String = ""
)
