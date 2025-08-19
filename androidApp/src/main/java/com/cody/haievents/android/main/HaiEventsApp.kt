package com.cody.haievents.android.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cody.haievents.android.common.componets.CustomBottomNavBar
import com.cody.haievents.android.common.componets.navItems
import com.cody.haievents.android.screens.NavGraphs
import com.cody.haievents.android.screens.destinations.SplashDestination

import com.ramcosta.composedestinations.DestinationsNavHost


@Composable
fun  HaiEventsApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedItem = navItems.firstOrNull { it.route == currentRoute } ?: navItems[0]
    var hasNavigatedToSplash by remember { mutableStateOf(false) }

    val navHostController = rememberNavController()
    DestinationsNavHost(navGraph = NavGraphs.root, navController = navHostController)


    Scaffold(
        bottomBar = {
            CustomBottomNavBar(
                selectedItem = selectedItem,
                onItemSelected = {
                    if (it.route != currentRoute) {
                        navController.navigate(it.route) {
                            launchSingleTop = true
                        }
                    }
                },
                navHostController = navController
            )
        }
    ) { innerPadding ->
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }

    // Navigate to Splash only once
    LaunchedEffect(Unit) {
        if (!hasNavigatedToSplash) {
            navController.navigate(SplashDestination.route) {
                popUpTo(0) { inclusive = true }
            }
            hasNavigatedToSplash = true
        }
    }


}