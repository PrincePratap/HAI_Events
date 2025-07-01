package com.cody.haievents.android.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Color Definitions ---
private val screenBackgroundColor = Color(0xFF000000)
private val contentBackgroundColor = Color.White
private val iconButtonBackgroundColor = Color(0xFFF0F0F0)
private val avatarBackgroundColor = Color(0xFFFDE8E8)
private val primaryTextColor = Color.Black
private val secondaryTextColor = Color.Gray
private val highlightColor = Color(0xFF007AFF)

@Composable
fun ProfileScreen(
    navigateToEventDetails: (String) -> Unit = {},
    navigateToOrderDetails: (String) -> Unit = {},
    navigateToStreamLibrary: () -> Unit = {},
    navigateToPlayCreditCard: () -> Unit = {},
    navigateToHelpCentre: () -> Unit = {},
    navigateToAccountSettings: () -> Unit = {},
    navigateToRewards: () -> Unit = {},
    navigateToOffers: () -> Unit = {},
    navigateToFoodAndBeverages: () -> Unit = {},
    navigateToCreateEvent: () -> Unit = {}
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(contentBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mock Status Bar for Preview
        // Top Bar with back, title, and edit
        Spacer(modifier = Modifier.height(20.dp))
        // Profile Header with avatar, name, and email
        ProfileHeader()
        Spacer(modifier = Modifier.height(28.dp))
        // Stats Section
        Spacer(modifier = Modifier.height(28.dp))
        // Menu List
        ProfileMenuList(navigateToCreateEvent)
        // Spacer to push home indicator to the bottom
        Spacer(modifier = Modifier.weight(1f))
        // Mock Home Indicator for Preview
    }
}

@Composable
private fun ProfileHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("Leonardo", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = primaryTextColor)
        Spacer(modifier = Modifier.height(4.dp))
        Text("leonardo@gmail.com", fontSize = 16.sp, color = secondaryTextColor)
    }
}



@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = secondaryTextColor, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(value, color = highlightColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}



@Composable
private fun ProfileMenuList(createEvent : () -> Unit){
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        ProfileMenuItem(icon = Icons.Outlined.Receipt, text = "Your Orders")
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.VideoLibrary, text = "Stream Library")
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.CreditCard, text = "Play Credit card")
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.HelpOutline, text = "Help Centre")
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.Settings, text = "Account & Setting")
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.StarBorder, text = "Rewards")
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.LocalOffer, text = "Offers")
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.Fastfood, text = "Food & Beverages")
        ProfileMenuItem(icon = Icons.Outlined.Event, text = "Create Event" , onClick = createEvent)
        Divider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 1.dp)

        ProfileMenuItem(icon = Icons.Outlined.Logout, text = "Logout")
    }
}

@Composable
private fun ProfileMenuItem(icon: ImageVector, text: String , onClick: () -> Unit = {}){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, tint = secondaryTextColor)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, color = primaryTextColor, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = secondaryTextColor)
    }
}



@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(screenBackgroundColor)
        ) {
            ProfileScreen(
                navigateToEventDetails = {},
                navigateToOrderDetails = {},
                navigateToStreamLibrary = {},
                navigateToPlayCreditCard = {},
                navigateToHelpCentre = {},
                navigateToAccountSettings = {},
                navigateToRewards = {},
                navigateToOffers = {},
                navigateToFoodAndBeverages = {},
                navigateToCreateEvent = {}
            )
        }
    }
}
