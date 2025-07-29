package com.cody.haievents.android.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cody.haievents.android.screens.destinations.LoginDestination
import com.cody.haievents.android.screens.destinations.SplashDestination
import com.cody.haievents.android.screens.destinations.WelcomeDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun  Splash(navigator: DestinationsNavigator) {

    // Show UI
    SplashScreen()

    // Navigate after 2 seconds delay
    LaunchedEffect(Unit) {
        delay(2000)
        navigator.navigate(WelcomeDestination.route) {
            popUpTo(SplashDestination.route) { inclusive = true }
        }
    }
}