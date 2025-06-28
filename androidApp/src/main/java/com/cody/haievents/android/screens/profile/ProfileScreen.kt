package com.cody.haievents.android.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocalOffer
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
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(contentBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mock Status Bar for Preview
        // Top Bar with back, title, and edit
        ProfileTopBar()
        Spacer(modifier = Modifier.height(20.dp))
        // Profile Header with avatar, name, and email
        ProfileHeader()
        Spacer(modifier = Modifier.height(28.dp))
        // Stats Section
        StatsSection()
        Spacer(modifier = Modifier.height(28.dp))
        // Menu List
        ProfileMenuList()
        // Spacer to push home indicator to the bottom
        Spacer(modifier = Modifier.weight(1f))
        // Mock Home Indicator for Preview
        MockHomeIndicator()
    }
}


@Composable
private fun ProfileTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { /* Handle back press */ },
            modifier = Modifier.background(iconButtonBackgroundColor, CircleShape)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = primaryTextColor)
        }
        Text("Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = primaryTextColor)
        IconButton(
            onClick = { /* Handle edit profile */ },
            modifier = Modifier.background(iconButtonBackgroundColor, CircleShape)
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit Profile", tint = highlightColor)
        }
    }
}

@Composable
private fun ProfileHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(avatarBackgroundColor)
        ) {
            // In a real app, load the image using Coil or Glide.
            // This is a placeholder that simulates the original avatar.
            Image(
                painter = // Using an icon to represent the illustration
                androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Avatar",
                modifier = Modifier.size(90.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Leonardo", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = primaryTextColor)
        Spacer(modifier = Modifier.height(4.dp))
        Text("leonardo@gmail.com", fontSize = 16.sp, color = secondaryTextColor)
    }
}

@Composable
private fun StatsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem(label = "Reward Points", value = "360")
            VerticalDivider()
            StatItem(label = "Travel Trips", value = "238")
            VerticalDivider()
            StatItem(label = "Bucket List", value = "473")
        }
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
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(40.dp)
            .width(1.dp)
            .background(Color.LightGray.copy(alpha = 0.5f))
    )
}

@Composable
private fun ProfileMenuList() {
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
    }
}

@Composable
private fun ProfileMenuItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle item click */ }
            .padding(vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, tint = secondaryTextColor)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, color = primaryTextColor, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = secondaryTextColor)
    }
}

@Composable
private fun MockHomeIndicator() {
    Box(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .width(134.dp)
            .height(5.dp)
            .background(Color.Black, shape = CircleShape)
    )
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
            ProfileScreen()
        }
    }
}