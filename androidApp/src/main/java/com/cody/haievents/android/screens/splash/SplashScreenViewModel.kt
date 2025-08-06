package com.cody.haievents.android.screens.splash

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.common.data.local.UserSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SplashScreenViewModel(
    dataStore: DataStore<UserSettings>  // Injected DataStore for accessing saved user settings
) :  ViewModel() {
    val uiState: StateFlow<SplashUiState> = dataStore.data
        .map { settings ->  // Transform raw data into a UI state
            SplashUiState.Success(settings)
        }
        .stateIn(
            scope = viewModelScope,  // Launch coroutine in ViewModel scope
            initialValue = SplashUiState.Loading,  // Show loading until data is available
            started = SharingStarted.WhileSubscribed(1000)  // Keep collecting for 5 seconds after last subscriber
        )
}
sealed interface SplashUiState {
    data object Loading : SplashUiState  // Shown while waiting for data from DataStore
    data class Success(val currentUser: UserSettings) : SplashUiState  // When user data is available
}
