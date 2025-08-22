package com.cody.haievents.android.screens.editProfile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.componets.LabeledTextField
import com.cody.haievents.android.common.theming.goldColor
import com.cody.haievents.android.common.theming.lightGrayColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    uiState: EditProfileState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onDobChange: (String) -> Unit,
    onTelephoneChange: (String) -> Unit = {},
    onAddressChange: (String) -> Unit,
    onZipChange: (String) -> Unit,
    onPickImage: (Uri?) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onDismissError: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Image picker
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        onPickImage(uri)
    }

    // Show error snackbar when errorMessage changes
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            onDismissError()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Profile",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = goldColor
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.White
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {

                // --- Form Fields ---
                LabeledTextField(
                    label = "First Name",
                    value = uiState.firstName,
                    onValueChange = onFirstNameChange,
                    capitalization = KeyboardCapitalization.Words
                )
                Spacer(modifier = Modifier.height(16.dp))

                LabeledTextField(
                    label = "Last Name",
                    value = uiState.lastName,
                    onValueChange = onLastNameChange,
                    capitalization = KeyboardCapitalization.Words
                )
                Spacer(modifier = Modifier.height(16.dp))

                LabeledTextField(
                    label = "Date of Birth",
                    value = uiState.dob,
                    onValueChange = onDobChange,
                    placeholder = "dd-mm-yyyy",
                    keyboardType = KeyboardType.Number
                )
                Spacer(modifier = Modifier.height(16.dp))

                LabeledTextField(
                    label = "Telephone No.",
                    value = uiState.telephone,
                    onValueChange = onTelephoneChange,
                    keyboardType = KeyboardType.Phone
                )
                Spacer(modifier = Modifier.height(16.dp))

                LabeledTextField(
                    label = "Address",
                    value = uiState.address,
                    onValueChange = onAddressChange,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(16.dp))

                LabeledTextField(
                    label = "ZIP Code",
                    value = uiState.zipCode,
                    onValueChange = onZipChange,
                    placeholder = "e.g. 400001",
                    keyboardType = KeyboardType.Number
                )
                Spacer(modifier = Modifier.height(16.dp))

                // --- Profile Image Uploader ---
                Text(
                    text = "Profile Image",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            width = 1.dp,
                            color = goldColor.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { imagePicker.launch("image/*") },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = lightGrayColor
                            ),
                            elevation = ButtonDefaults.buttonElevation(0.dp)
                        ) {
                            Text("Choose File", color = Color.Black.copy(alpha = 0.8f))
                        }
                        Text(
                            text = "No file chosen", // You can reflect chosen filename via uiState later
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- Save Changes Button ---
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        onSave()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = goldColor),
                    enabled = !uiState.isLoading && uiState.firstName.isNotBlank()
                ) {
                    Text(
                        text = if (uiState.isLoading) "Saving..." else "Save Changes",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (uiState.succeed) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Profile updated successfully.",
                        color = Color(0xFF2E7D32),
                        fontSize = 14.sp
                    )
                }
            }

            // Loading overlay
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = goldColor)
                }
            }
        }
    }
}

/* ---------------------------- PREVIEW ---------------------------- */

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        uiState = EditProfileState(
            firstName = "Aditya",
            lastName = "Singh",
            dob = "01-01-2000",
            telephone = "7849046537",
            address = "221B Baker Street",
            zipCode = "400001",
            isLoading = false,
            errorMessage = null,
            succeed = false
        ),
        onFirstNameChange = {},
        onLastNameChange = {},
        onDobChange = {},
        onTelephoneChange = {},
        onAddressChange = {},
        onZipChange = {},
        onPickImage = {},
        onSave = {},
        onBack = {},
        onDismissError = {}
    )
}
