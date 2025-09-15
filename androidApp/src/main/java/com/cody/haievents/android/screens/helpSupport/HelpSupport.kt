package com.cody.haievents.android.screens.helpSupport

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HelpSupport(
    navigator: DestinationsNavigator
) {
    HelpSupportScreen(onBackClick = { navigator.navigateUp() })
}