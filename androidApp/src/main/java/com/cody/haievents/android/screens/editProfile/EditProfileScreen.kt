package com.cody.haievents.android.screens.editProfile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define the theme colors to match the image
val goldColor = Color(0xFFC7A64D)
val lightGrayColor = Color(0xFFE0E0E0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen() {
    // State holders for each form field
    var firstName by remember { mutableStateOf("Rajesh") }
    var lastName by remember { mutableStateOf("Singh") }
    var dob by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("7849046537") }
    var address by remember { mutableStateOf("7849046537") }
    var zipCode by remember { mutableStateOf("") }

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
                    IconButton(onClick = { /* Handle back navigation */ }) {
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
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Form Fields
            LabeledTextField(
                label = "First Name",
                value = firstName,
                onValueChange = { firstName = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabeledTextField(
                label = "Last Name",
                value = lastName,
                onValueChange = { lastName = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabeledTextField(
                label = "Date of Birth",
                value = dob,
                onValueChange = { dob = it },
                placeholder = "dd-mm-yyyy"
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabeledTextField(
                label = "Telephone No.",
                value = telephone,
                onValueChange = { telephone = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabeledTextField(
                label = "Address",
                value = address,
                onValueChange = { address = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabeledTextField(
                label = "ZIP Code",
                value = zipCode,
                onValueChange = { zipCode = it },
                placeholder = "e.g. 400001"
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Profile Image Uploader
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
                        onClick = { /* TODO: Handle file chooser */ },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightGrayColor
                        ),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        Text("Choose File", color = Color.Black.copy(alpha = 0.8f))
                    }
                    Text("No file chosen", color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Save Changes Button
            Button(
                onClick = { /* TODO: Handle save changes */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = goldColor)
            ) {
                Text(
                    text = "Save Changes",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * A reusable Composable for a labeled text input field.
 */
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = goldColor,
                unfocusedBorderColor = goldColor.copy(alpha = 0.7f),
                cursorColor = goldColor,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )
    }
}


@Preview(showBackground = true, )
@Composable
fun EditProfileScreenPreview() {
        EditProfileScreen()
}