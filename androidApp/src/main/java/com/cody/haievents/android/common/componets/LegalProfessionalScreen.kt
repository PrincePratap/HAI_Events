package com.cody.haievents.android.common.componets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
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

// You can place this in your theme folder, e.g., ui/theme/Theme.kt
// For simplicity, we'll define them here.
val MyCasesColor = Color(0xFF4A90E2)
val MyCasesBackgroundColor = Color(0xFFEAF2FD)
val CalendarColor = Color(0xFF50E3C2)
val CalendarBackgroundColor = Color(0xFFE8FAF5)
val ClientsColor = Color(0xFFBD10E0)
val ClientsBackgroundColor = Color(0xFFF7E7FB)
val SettingsColor = Color(0xFFF5A623)
val SettingsBackgroundColor = Color(0xFFFEF5E9)

// A simple data class to hold information for our navigation items


@Composable
fun LegalProfessionalScreen() {
    // List of navigation items
//    val navItems = listOf(
//        NavItem("My Cases", Icons.AutoMirrored.Filled.MenuBook, MyCasesColor, MyCasesBackgroundColor),
//        NavItem("Calendar", Icons.Filled.CalendarToday, CalendarColor, CalendarBackgroundColor),
//        NavItem("Clients", Icons.Filled.People, ClientsColor, ClientsBackgroundColor),
//        NavItem("Settings", Icons.Filled.Settings, SettingsColor, SettingsBackgroundColor)
//    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface) // Use a theme-appropriate background
            .padding(16.dp)
    ) {
        ProfileHeader()
        Spacer(modifier = Modifier.height(32.dp))
//        DashboardGrid(navItems)
    }
}

@Composable
fun ProfileHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFF4A90E2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "PS",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Adv. Priya Sharma",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Legal Professional",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun DashboardGrid(items: List<NavItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            DashboardItemCard(item = item)
        }
    }
}


@Composable
fun DashboardItemCard(item: NavItem) {
//    Card(
//        shape = RoundedCornerShape(10.dp),
//        colors = CardDefaults.cardColors(containerColor = item.backgroundColor),
//        modifier = Modifier
//            .aspectRatio(1.2f)
//            .padding(4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = item.icon,
//                contentDescription = item.title,
//                tint = item.color,
//                modifier = Modifier.size(20.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = item.title,
//                color = item.color,
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 14.sp
//            )
//        }
//    }
}



@Preview(showBackground = true, widthDp = 380, heightDp = 800)
@Composable
fun LegalProfessionalScreenPreview() {
    // Wrap with your app's Material3 theme
    MaterialTheme {
        Surface(color = Color(0xFFF7F9FC)) { // A slightly off-white background as seen in the image
            LegalProfessionalScreen()
        }
    }
}