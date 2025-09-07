package com.cody.haievents.android.screens.profile

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.common.data.local.UserSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val dataStore: DataStore<UserSettings>

) : ViewModel() {


    val uiState: StateFlow<ProfileUiState> = dataStore.data
        .map { settings -> ProfileUiState.Success(settings) }
        .stateIn(
            scope = viewModelScope,
            initialValue = ProfileUiState.Loading,
            started = SharingStarted.WhileSubscribed(1000)
        )

    fun clearUserData() {
        viewModelScope.launch {
            dataStore.updateData {
                UserSettings()
            }
        }
    }
}


sealed interface ProfileUiState {
    data object Loading : ProfileUiState  // Shown while waiting for data from DataStore
    data class Success(val currentUser: UserSettings) :
        ProfileUiState  // When user data is available
}