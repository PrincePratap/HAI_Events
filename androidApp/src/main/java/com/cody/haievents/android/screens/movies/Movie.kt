package com.cody.haievents.android.screens.movies

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun  Movie()  {
    MovieScreen(navigateToMovieDetails = {
//      navigator.navigate(MovieDetailsDestination)
    })
}