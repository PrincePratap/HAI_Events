package com.cody.haievents.android.screens.addEvent.eventDetails

import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

// Imports for building a ticket list for the validator
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.Show.model.Role

@Destination
@Composable
fun EventDetails(navigator: DestinationsNavigator) {

    val viewModel: EventDetailsViewModel = koinViewModel()
    val uiState = viewModel.uiState
    val context = LocalContext.current

    Log.d("EventDetails", "EventDetails Composable Loaded")

    // Hold the picked file URI & display name
    var pickedUri by remember { mutableStateOf<Uri?>(null) }
    var pickedName by remember { mutableStateOf<String?>(null) }

    // Image picker
    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            pickedUri = uri
            if (uri != null) {
                // Try to extract a display name; fall back to default
                pickedName = context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (idx >= 0 && cursor.moveToFirst()) cursor.getString(idx) else null
                } ?: "image_${System.currentTimeMillis()}.jpg"

                // Read bytes and call ViewModel
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    val bytes = stream.readBytes()
                    viewModel.uploadEventImage(bytes, pickedName ?: "image.jpg")
                }
            } else {
                Log.d("EventDetails", "Image picker canceled")
            }
        }

    // React to validation results (optional navigation place-holder)
    LaunchedEffect(uiState.succeed, uiState.errorMessage) {
        uiState.errorMessage?.let { Log.e("EventDetails", "Validation error → $it") }
        if (uiState.succeed) {
            Log.d("EventDetails", "Validation success. Built request: ${uiState.builtRequest}")
            // TODO: navigate to the next screen (e.g., BankTransferDetailsDestination)
            // navigator.navigate(BankTransferDetailsDestination)
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
        clickOnChooseFile = { imagePickerLauncher.launch("image/*") },
    )
}
