package com.cody.haievents.android.screens.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.cody.haievents.android.screens.destinations.EditProfileDestination
import com.cody.haievents.android.screens.destinations.EventDetailsDestination
import com.cody.haievents.android.screens.destinations.WebPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import com.cody.haievents.android.screens.NavGraphs
import com.cody.haievents.android.screens.destinations.HelpSupportDestination
import com.cody.haievents.android.screens.destinations.LoginDestination
import com.ramcosta.composedestinations.utils.startDestination
private const val TAG = "ProfileRoute"


@Destination
@Composable
fun Profile(navigator: DestinationsNavigator) {


    val viewModel: ProfileViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        Log.d(TAG, "Entered Profile screen with ViewModel=${viewModel::class.simpleName}")
    }

    val uiState by viewModel.uiState.collectAsState()

    // Log every time state changes
    LaunchedEffect(uiState) {
        Log.d(TAG, "uiState updated -> ${uiState::class.simpleName}: $uiState")
    }

    val userName = (uiState as? ProfileUiState.Success)?.currentUser?.name ?: "Cody Rajput"
    val userEmail = (uiState as? ProfileUiState.Success)?.currentUser?.email ?: "Corporate Lawyer"
    val token = (uiState as? ProfileUiState.Success)?.currentUser?.token ?: ""
    Log.d(TAG, "token ${token}")




    ProfileScreen(
        userName = userName,
        userEmail = userEmail,
        onYourListedEventsClick = {
            navigator.navigate(EventDetailsDestination.route)
        },
        onEditProfileClick = {
            navigator.navigate(EditProfileDestination.route)
        },
        onTermsClick = {
            navigator.navigate(WebPageDestination(type = 1))
        },
        onPrivacyClick = {
            navigator.navigate(WebPageDestination(type = 0))
        },
        onHelpSupportClick = {
            navigator.navigate(HelpSupportDestination.route)
        },
        onLogoutClick = {
            viewModel.clearUserData()
            navigator.navigate(LoginDestination.route) {
                popUpTo(NavGraphs.root.startDestination.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }

    )
}