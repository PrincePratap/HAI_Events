package com.cody.haievents.android.screens.addEvent.eventDetails

import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import com.cody.haievents.android.screens.destinations.EventImageAndTicketsDestination

@Destination
@Composable
fun EventDetails(navigator: DestinationsNavigator) {

    val viewModel: EventDetailsViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    Log.d("EventDetails", "EventDetails Composable Loaded")




    // React to validation results (optional navigation place-holder)
    LaunchedEffect(uiState.succeed, uiState.errorMessage) {
        uiState.errorMessage?.let { Log.e("EventDetails", "Validation error → $it") }
        Toast.makeText(context, uiState.errorMessage ?: "Validation success", Toast.LENGTH_SHORT)
            .show()
        if (uiState.succeed) {
            Log.d("EventDetails", "Validation success. Built request: ${uiState.builtRequest}")
            navigator.navigate(EventImageAndTicketsDestination(payload = CreateUserEventRequest(
                title = uiState.eventTitle,
                organizerName = uiState.organiserName,
                email = uiState.contactEmail,
                location = uiState.eventLocation,
                date = uiState.eventDate,
                time = uiState.eventTime,
                description = uiState.eventDescription,

            )))


        }
    }

    EventDetailsScreen(
        uiState = uiState,
        onNextClick = {

            viewModel.checkEventDetails()
        },

        onBackClick = { navigator.popBackStack() },
        onChangeTitle = viewModel::updateEventTitle,
        onChangeOrganiserName = viewModel::updateOrganiserName,
        onChangeContactEmail = viewModel::updateContactEmail,
        onChangeEventLocation = viewModel::updateEventLocation,
        onChangeEventDate = viewModel::updateEventDate,
        onChangeEventTime = viewModel::updateEventTime,
        onChangeEventDescription = viewModel::updateEventDescription,
//        clickOnChooseFile = { imagePickerLauncher.launch("image/*") },
    )
}
