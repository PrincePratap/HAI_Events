package com.cody.haievents.android.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.cody.haievents.android.screens.NavGraphs
import com.cody.haievents.android.screens.destinations.SplashDestination

import com.ramcosta.composedestinations.DestinationsNavHost


@Composable
fun  HaiEventsApp() {

    val navHostController = rememberNavController()
    DestinationsNavHost(navGraph = NavGraphs.root, navController = navHostController)


    navHostController.navigate(SplashDestination.route) {
//        popUpTo(HomeDestination.route) {
//            inclusive = true
//        }
    }
}