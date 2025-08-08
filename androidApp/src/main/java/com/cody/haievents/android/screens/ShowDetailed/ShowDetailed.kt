package com.cody.haievents.android.screens.ShowDetailed

import android.util.Log
import androidx.compose.runtime.Composable
import com.cody.haievents.android.screens.auth.login.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun ShowDetailed(
    navigator: DestinationsNavigator,
    showId: Int
    ) {

    val viewModel: ShowDetailedViewModel = koinViewModel()
    Log.d("LoginComposable", "Login Composable Loaded")

    val uiState = viewModel.uiState

    ShowDetailedScreen(uiState)


}