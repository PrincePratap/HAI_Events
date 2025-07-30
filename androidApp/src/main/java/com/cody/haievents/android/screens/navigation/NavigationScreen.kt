package com.cody.haievents.android.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cody.haievents.android.common.componets.AppBottomNavigationBar
import com.cody.haievents.android.screens.HomeScreen
import com.cody.haievents.android.screens.movies.MovieScreen

import com.cody.haievents.android.screens.profile.ProfileScreen

private val screenBackgroundColor = Color(0xFF000000)

@Composable
fun NavigationScreen(
    navToMovieScreen: () -> Unit,
    navToCreateEventScreen: () -> Unit ={}
) {

    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackgroundColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
//            AppHeader()

            Box(modifier = Modifier.weight(1f)) {
                when (selectedIndex) {
                    0 -> HomeScreen()
                    1 -> MovieScreen(navigateToMovieDetails = { navToMovieScreen()})
//                    2 -> LiveEventsScreen()
                    3 -> ProfileScreen(navigateToCreateEvent = navToCreateEventScreen)
                }
            }

            AppBottomNavigationBar(
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationScreenPreview() {
    MaterialTheme {
        NavigationScreen(navToMovieScreen = {})
    }
}
