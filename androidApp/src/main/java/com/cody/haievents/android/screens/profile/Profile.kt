package com.cody.haievents.android.screens.profile

import androidx.compose.runtime.Composable
import com.cody.haievents.android.screens.destinations.EditProfileDestination
import com.cody.haievents.android.screens.destinations.EventDetailsDestination
import com.cody.haievents.android.screens.destinations.WebPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun  Profile(navigator: DestinationsNavigator) {
    ProfileScreen(
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
        }

    )
}