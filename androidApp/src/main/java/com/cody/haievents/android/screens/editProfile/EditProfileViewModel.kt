package com.cody.haievents.android.screens.editProfile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.auth.domain.usecase.GetUserUseCase
import com.cody.haievents.auth.domain.usecase.UpdateUserUseCase
import com.cody.haievents.common.util.Result
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "EditProfileViewModel"
    }

    var uiState by mutableStateOf(EditProfileState())
        private set

    fun getUser() {
        Log.i(TAG, "getUser(): started")
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, succeed = false)

            when (val result = getUserUseCase()) {
                is Result.Error -> {
                    Log.w(TAG, "getUser(): failed -> ${result.message}")
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message ?: "Something went wrong"
                    )
                }

                is Result.Success -> {

                    val user = result.data?.data
                    Log.i(TAG, "getUser(): success, mapping response to UI state")
                    // ---- Try to extract data in a tolerant way (covers common shapes) ----


//                    Log.d(
//                        TAG,
//                        "getUser(): mapped -> firstName='$firstName', lastName='$lastName', dob='$dob', phone='$telephone'"
//                    )

                    if (user != null) {
                        uiState = uiState.copy(
                            firstName = user.firstName,
                            lastName = user.lastName,
                            dob = user.detail?.dob ?: "",
                            telephone = user.detail?.telephone ?: "",
                            address = user.detail?.address ?: "",
                            zipCode = user.detail?.zip ?: "",
                            isLoading = false,
                            errorMessage = null,
                            succeed = true
                        )
                    }
                }
            }
        }
    }

    fun updateUser() {
        Log.i(TAG, "getUser(): started")
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, succeed = false)

            when (val result = updateUserUseCase(
                firstName = uiState.firstName,
                lastName = uiState.lastName,
                dob = uiState.dob,
                address = uiState.address,
                zip = uiState.zipCode,
                image = ""
            )) {
                is Result.Error -> {
                    Log.w(TAG, "getUser(): failed -> ${result.message}")
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message ?: "Something went wrong"
                    )
                }

                is Result.Success -> {

                    val user = result.data?.data
                    Log.i(TAG, "getUser(): success, mapping response to UI state")


                    if (user != null) {
                        uiState = uiState.copy(
                            firstName = user.firstName ?: "",
                            lastName = user.lastName?: "",
                            dob = user.detail?.dob ?: "",
                            telephone = user.detail?.telephone ?: "",
                            address = user.detail?.address ?: "",
                            zipCode = user.detail?.zip ?: "",
                            isLoading = false,
                            errorMessage = null,
                            succeed = true
                        )
                    }
                }
            }
        }
    }

    // ---------- Field updaters with concise logs ----------
    fun updateFirstName(input: String) {
        if (uiState.firstName != input) {
            Log.v(TAG, "updateFirstName(): '$input'")
            uiState = uiState.copy(firstName = input)
        }
    }

    fun updateLastName(input: String) {
        if (uiState.lastName != input) {
            Log.v(TAG, "updateLastName(): '$input'")
            uiState = uiState.copy(lastName = input)
        }
    }

    fun updateDob(input: String) {
        if (uiState.dob != input) {
            Log.v(TAG, "updateDob(): '$input'")
            uiState = uiState.copy(dob = input)
        }
    }

    fun updateTelephone(input: String) {
        if (uiState.telephone != input) {
            Log.v(TAG, "updateTelephone(): '$input'")
            uiState = uiState.copy(telephone = input)
        }
    }

    fun updateAddress(input: String) {
        if (uiState.address != input) {
            Log.v(TAG, "updateAddress(): '$input'")
            uiState = uiState.copy(address = input)
        }
    }

    fun updateZipCode(input: String) {
        if (uiState.zipCode != input) {
            Log.v(TAG, "updateZipCode(): '$input'")
            uiState = uiState.copy(zipCode = input)
        }
    }

    fun clearError() {
        if (uiState.errorMessage != null) {
            Log.d(TAG, "clearError()")
            uiState = uiState.copy(errorMessage = null)
        }
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared()")
        super.onCleared()
    }
}

/**
 * Small reflection helper to safely fetch a property by name from an unknown data class / map-like object.
 * This keeps the ViewModel resilient to slight response-shape differences without forcing new models here.
 */
@Suppress("UNCHECKED_CAST")
private fun <T> Any?.safeGet(field: String): T? {
    if (this == null) return null

    // If it's a Map-like structure
    if (this is Map<*, *>) {
        return this[field] as? T
    }

    // Try Kotlin reflection on data classes / POJOs
    return try {
        val member = this::class.members.firstOrNull { it.name == field }
        @Suppress("UNCHECKED_CAST")
        (member?.call(this) as? T)
    } catch (e: Throwable) {
        Log.d("EditProfileViewModel", "safeGet('$field') failed on ${this::class.simpleName}: ${e.message}")
        null
    }
}

data class EditProfileState(
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = "",
    val telephone: String = "",
    val address: String = "",
    val zipCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val succeed: Boolean = false
)
