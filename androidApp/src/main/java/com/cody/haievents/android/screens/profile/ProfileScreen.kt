package com.cody.haievents.android.screens.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.R

// --- Main Activity (Example Usage) ---


// --- Theme Definition ---
private val GoldColor = Color(0xFFC7A440)
private val DarkBackgroundColor = Color(0xFF1C1C1E)
private val WhiteColor = Color.White
private val GrayColor = Color.Gray





// --- Screen Composable ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))




            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Rajesh Singh",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "rajeshsingh@email.com",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayColor
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- Menu Items ---
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                ProfileMenuItem(icon = Icons.Outlined.Edit, text = "Edit Profile")
                ProfileMenuItem(icon = Icons.Outlined.Mic, text = "Your Listed Events")
                ProfileMenuItem(icon = Icons.Outlined.Lock, text = "Change Password")
                ProfileMenuItem(icon = Icons.Outlined.CardMembership, text = "Membership")
                ProfileMenuItem(icon = Icons.Outlined.Notifications, text = "Notification")
                ProfileMenuItem(icon = Icons.Outlined.HelpOutline, text = "Help & Support")
                ProfileMenuItem(icon = Icons.Outlined.Description, text = "Terms & Condition")
                ProfileMenuItem(icon = Icons.Outlined.Visibility, text = "Private Policy")
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- Log Out Button ---
            Button(
                onClick = { /* Handle log out */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Log Out",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// --- Reusable Menu Item Composable ---
@Composable
fun ProfileMenuItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle item click */ }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Navigate to $text",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// --- Preview ---
@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun ProfileScreenPreview() {

        ProfileScreen()

}

