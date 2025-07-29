package com.cody.haievents.android.screens.welcome

import androidx.compose.runtime.Composable
import com.cody.haievents.android.screens.destinations.LoginDestination
import com.cody.haievents.android.screens.destinations.RegisterDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun  Welcome(navigation: DestinationsNavigator) {
    WelcomeScreen(
         clickLogin = {
             navigation.navigate(LoginDestination.route)
    }, clickRegister = {
        navigation.navigate(RegisterDestination.route)
    })
}