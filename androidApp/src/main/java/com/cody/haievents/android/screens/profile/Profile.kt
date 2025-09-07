package com.cody.haievents.android.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.cody.haievents.android.screens.destinations.EditProfileDestination
import com.cody.haievents.android.screens.destinations.EventDetailsDestination
import com.cody.haievents.android.screens.destinations.WebPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import com.cody.haievents.android.screens.NavGraphs
import com.cody.haievents.android.screens.destinations.LoginDestination
import com.ramcosta.composedestinations.utils.startDestination


@Destination
@Composable
fun  Profile(navigator: DestinationsNavigator) {


    val viewModel: ProfileViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val userName = (uiState as? ProfileUiState.Success)?.currentUser?.name ?: "Cody Rajput"
    val userEmail = (uiState as? ProfileUiState.Success)?.currentUser?.email ?: "Corporate Lawyer"
    val token = (uiState as? ProfileUiState.Success)?.currentUser?.token ?: ""



    ProfileScreen(
        userName = userName,
        userEmail = userEmail,
        onYourListedEventsClick = {
            navigator.navigate(EventDetailsDestination.route)
        },
        onEditProfileClick = {
            navigator.navigate(EditProfileDestination.route)
        },
        onTermsClick ={
            navigator.navigate(WebPageDestination(type = 1 ))
        },
        onPrivacyClick ={
            navigator.navigate(WebPageDestination(type = 0 ))
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