package com.cody.haievents.android.screens.addEvent.eventSubmittedScreen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Main Screen Composable ---

@Composable
fun EventSubmittedScreen(eventName: String) {
    // Colors derived from the image and previous screens
    val goldColor = Color(0xFFC7A441)
    val primaryTextColor = Color.Black.copy(alpha = 0.87f)
    val secondaryTextColor = Color.Gray

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        // Box to center the content on the screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp), // Side padding for the content block
            contentAlignment = Alignment.Center
        ) {
            // Column to arrange content vertically
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 1. Success Icon
                // NOTE: Replace with your actual party popper drawable resource.
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_send), // Placeholder icon
                    contentDescription = "Success",
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                // 2. Title Text
                Text(
                    text = "Event Submitted Successfully!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = primaryTextColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                // 3. Description Text
                // The backslash before the apostrophe is included to match the image exactly.
                Text(
                    text = "Your event \"$eventName\" has been submitted successfully and is under review! You'll be notified once it\\'s live.",
                    fontSize = 16.sp,
                    color = secondaryTextColor,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
                Spacer(modifier = Modifier.height(32.dp))

                // 4. Action Button
                Button(
                    onClick = { /* TODO: Navigate to listed events */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = goldColor)
                ) {
                    Text(
                        text = "Go to Listed Events",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

// --- Preview Function ---

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES, // Shows the dark frame around the white screen
    name = "Event Submitted Screen Preview",
    widthDp = 390,
    heightDp = 844
)
@Composable
fun EventSubmittedScreenPreview() {
    MaterialTheme {
        EventSubmittedScreen(eventName = "vcxvcvx")
    }
}