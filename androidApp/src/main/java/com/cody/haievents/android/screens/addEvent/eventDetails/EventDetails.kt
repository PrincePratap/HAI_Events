package com.cody.haievents.android.screens.addEvent.eventDetails

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.android.screens.auth.login.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun EventDetails(navigator: DestinationsNavigator) {

    val viewModel: EventDetailsViewModel = koinViewModel()
    Log.d("LoginComposable", "Login Composable Loaded")

    val uiState = viewModel.uiState
    val context = LocalContext.current


    EventDetailsScreen(
        uiState = uiState,
        onNextClick = {},
        onBackClick = {},
        onChangeTitle = {},
        onChangeOrganiserName = {},
        onChangeContactEmail = {},
        onChangeEventLocation = {},
        onChangeEventDate = {},
        onChangeEventTime = {},
        onChangeEventDescription = {},
        onChangeTicketType = {}
    )
}