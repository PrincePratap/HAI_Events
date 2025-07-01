package com.cody.haievents.android.screens.verification

import androidx.compose.runtime.Composable
import com.cody.haievents.android.screens.destinations.NavigationDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun  Verification(navigator: DestinationsNavigator) {
    VerificationScreen(clickOnVerify = {
        navigator.navigate(NavigationDestination.route)
    })
}