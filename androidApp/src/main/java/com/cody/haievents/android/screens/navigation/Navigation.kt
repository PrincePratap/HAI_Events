package com.cody.haievents.android.screens.navigation

import androidx.compose.runtime.Composable import com.cody.haievents.android.screens.destinations.MovieDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Navigation(navigator: DestinationsNavigator) {

    NavigationScreen(navToMovieScreen = {
        navigator.navigate(MovieDetailDestination.route)
    }, navToCreateEventScreen = {
//        navigator.navigate(CreateEventDestination.route)
    })

}