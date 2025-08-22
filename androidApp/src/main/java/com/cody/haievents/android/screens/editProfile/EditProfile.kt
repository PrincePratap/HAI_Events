package com.cody.haievents.android.screens.editProfile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

private const val TAG = "EditProfileRoute"

@Destination
@Composable
fun EditProfile(
    navigator: DestinationsNavigator,
) {
    val viewModel: EditProfileViewModel = koinViewModel()
    Log.i(TAG, "Composable entered")

    val uiState = viewModel.uiState

    // One-time initial load
    LaunchedEffect(Unit) {
        Log.i(TAG, "Initial load -> viewModel.getUser()")
        viewModel.getUser()
    }

    // Log effects for error/success changes (optional but helpful)
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { Log.w(TAG, "uiState.errorMessage: $it") }
    }
    LaunchedEffect(uiState.succeed) {
        if (uiState.succeed) Log.i(TAG, "uiState.succeed changed -> true")
    }

    EditProfileScreen(
        uiState = uiState,

        onFirstNameChange = {
            Log.v(TAG, "onFirstNameChange('$it')")
            viewModel.updateFirstName(it)
        },
        onLastNameChange = {
            Log.v(TAG, "onLastNameChange('$it')")
            viewModel.updateLastName(it)
        },
        onDobChange = {
            Log.v(TAG, "onDobChange('$it')")
            viewModel.updateDob(it)
        },
        onTelephoneChange = {
            Log.v(TAG, "onTelephoneChange('$it')")
            viewModel.updateTelephone(it)
        },
        onAddressChange = {
            Log.v(TAG, "onAddressChange(...)")
            viewModel.updateAddress(it)
        },
        onZipChange = {
            Log.v(TAG, "onZipChange('$it')")
            viewModel.updateZipCode(it)
        },

        onPickImage = { uri ->
            Log.d(TAG, "onPickImage(uri=$uri)")
            // viewModel.setImageUri(uri) // if you add this to the VM
        },
        onSave = {
            Log.i(TAG, "onSave() clicked")
            // viewModel.saveProfile() // if implemented
        },
        onBack = {
            Log.i(TAG, "onBack() -> navigator.popBackStack()")
            navigator.popBackStack()
        },
        onDismissError = {
            Log.d(TAG, "onDismissError()")
            viewModel.clearError()
        }
    )
}
