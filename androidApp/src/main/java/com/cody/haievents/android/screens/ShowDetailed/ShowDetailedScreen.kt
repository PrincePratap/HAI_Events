package com.cody.haievents.android.screens.ShowDetailed



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define the primary color from the image for reusability
private val goldColor = Color(0xFFD1A34F)

@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Composable
fun DetailedPagePreview() {
        ShowDetailedScreen()
}

@Composable
fun ShowDetailedScreen() {
    Scaffold(
        topBar = {
            Column {
                // Custom status bar to match the image
                // Custom app bar
                AppBar()
            }
        },
        containerColor = Color.White
    ) { paddingValues ->
        // Content that scrolls
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            ImageSection()
            ContentSection(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}



@Composable
private fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(goldColor)
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Raw & Real",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "Share",
            tint = Color.White,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}


@Composable
private fun ImageSection() {
    // In a real app, this would use a library like Coil to load an image.
    // For this preview, a placeholder Box is used to represent the image area.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.1f) // Approximate aspect ratio from the image
            .background(Color(0xFF191932)), // Dark blue from the image's brick wall
        contentAlignment = Alignment.Center
    ) {
        // Placeholder text to represent the content of the image
        Text(
            text = "OPEN MIC",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp
        )
    }
}

@Composable
private fun ContentSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Raw & Real",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Info Grid
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoItem(icon = Icons.Outlined.LocationOn, text = "Kamani Auditorium, Delhi")
                InfoItem(icon = Icons.Outlined.Schedule, text = "7:00 PM")
            }
            Spacer(Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoItem(icon = Icons.Outlined.CalendarToday, text = "Sunday, 20 July 2025")
                InfoItem(icon = Icons.Outlined.Language, text = "Hindi")
            }
        }

        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

        // About the Show Section
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "About the Show",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            val description = "In a world that never stops speaking, what happens to a " +
                    "voice that cannot be heard?\n\"The Silent Echo\" is a deeply " +
                    "emotional and visually poetic play that follows the journey of " +
                    "Aarohi, a mute girl, as she navigates through heartbreak, hope, and....."

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "Read more",
                    color = goldColor,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Read more",
                    tint = goldColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun InfoItem(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
    }
}