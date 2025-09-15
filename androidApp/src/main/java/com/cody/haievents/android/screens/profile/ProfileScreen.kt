package com.cody.haievents.android.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val GrayColor = Color.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userName  : String = "Cody Rajput ",
    userEmail : String = "CodyRajput@gmail.com",
    onBackClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onYourListedEventsClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onHelpSupportClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {
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
                    IconButton(onClick = onBackClick) {
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

            // --- User info ---
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = userEmail,
                style = MaterialTheme.typography.bodyMedium,
                color = GrayColor
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- Menu Items ---
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                ProfileMenuItem(
                    icon = Icons.Outlined.Edit,
                    text = "Edit Profile",
                    onClick = onEditProfileClick
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Mic,
                    text = "Your Listed Events",
                    onClick = onYourListedEventsClick
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Lock,
                    text = "Change Password",
                    onClick = onChangePasswordClick
                )

                ProfileMenuItem(
                    icon = Icons.Outlined.Notifications,
                    text = "Notification",
                    onClick = onNotificationsClick
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.HelpOutline,
                    text = "Help & Support",
                    onClick = onHelpSupportClick
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Description,
                    text = "Terms & Condition",
                    onClick = onTermsClick
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Visibility,
                    text = "Private Policy",
                    onClick = onPrivacyClick
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- Log Out Button ---
            Button(
                onClick = onLogoutClick,
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

// --- Reusable Menu Item Composable with click ---
@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
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
    ProfileScreen(
        onBackClick = {},
        onEditProfileClick = {},
        onYourListedEventsClick = {},
        onChangePasswordClick = {},
        onNotificationsClick = {},
        onHelpSupportClick = {},
        onTermsClick = {},
        onPrivacyClick = {},
        onLogoutClick = {}
    )
}
