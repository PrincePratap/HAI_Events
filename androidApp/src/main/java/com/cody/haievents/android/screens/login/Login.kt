package com.cody.haievents.android.screens.login

import androidx.compose.runtime.Composable
import com.cody.haievents.android.screens.destinations.VerificationDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Login(navigator: DestinationsNavigator) {
    LoginScreen(clickOnLogin = {
        navigator.navigate(VerificationDestination.route)
    }, navigateForgetPassword = {}, navigateSignUp = {})
}