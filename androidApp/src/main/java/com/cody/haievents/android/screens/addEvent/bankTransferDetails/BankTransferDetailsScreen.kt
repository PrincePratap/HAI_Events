package com.cody.haievents.android.screens.addEvent.bankTransferDetails


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.theming.goldColor
import com.cody.haievents.android.common.theming.lightGoldBorder
import com.cody.haievents.android.common.theming.screenBackgroundColor

// --- Main Screen Composable ---

@Composable
fun BankTransferDetailsScreen() {

    var accountHolderName by remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var ifscCode by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        containerColor = screenBackgroundColor,
        topBar = { AddEventTopBar(backgroundColor = goldColor) }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(24.dp)
        ) {
            // Stepper showing both steps are completed
            Spacer(modifier = Modifier.height(32.dp))

            // Form sections
            EventInputSection(label = "Account Holder Name") {
                EventTextField(
                    value = accountHolderName,
                    onValueChange = { accountHolderName = it },
                    placeholder = "E.g Rajesh Singh",
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "Bank Name") {
                EventTextField(
                    value = bankName,
                    onValueChange = { bankName = it },
                    placeholder = "E.g State bank of India",
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "Account Number") {
                EventTextField(
                    value = accountNumber,
                    onValueChange = { accountNumber = it },
                    placeholder = "E.g 12355657687889",
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "IFSC Code") {
                EventTextField(
                    value = ifscCode,
                    onValueChange = { ifscCode = it },
                    placeholder = "E.g SBIBF78665M",
                    borderColor = lightGoldBorder
                )
            }
            EventInputSection(label = "Phone Number (linked to account)") {
                EventTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    placeholder = "E.g 7879985685",
                    borderColor = lightGoldBorder
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* TODO: Handle form submission */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = goldColor)
            ) {
                Text("Submit", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}


// --- Reusable UI Components (Can be shared across screens) ---

@Composable
fun AddEventTopBar(backgroundColor: Color) {
    Surface(
        color = backgroundColor,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle back navigation */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                text = "Add Your Event",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}



@Composable
fun EventInputSection(label: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
fun EventTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    borderColor: Color
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor.copy(alpha = 0.7f),
            cursorColor = borderColor,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        singleLine = true
    )
}

// --- Preview Function ---

@Preview(
    showBackground = true,
    name = "Bank Details Screen Preview",
    uiMode = Configuration.UI_MODE_NIGHT_NO, // Use light mode to see the white form
    widthDp = 390,
    heightDp = 844
)
@Composable
fun BankDetailsScreenPreview() {
    MaterialTheme {
        BankTransferDetailsScreen()
    }
}