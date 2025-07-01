package com.cody.haievents.android.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
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

// A custom theme can be defined here, but for simplicity, we'll use the default MaterialTheme.
// For this preview, let's define the specific colors seen in the image.
private val lightGrayBackground = Color(0xFFF5F5F5)
private val iconBackgroundColor = Color(0xFFFFF2F4)
private val iconTintColor = Color(0xFFE53935) // A representative red color for icons

// Data class to hold information for each help topic
data class HelpTopic(
    val title: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpCentreScreen() {
    Scaffold(
        containerColor = lightGrayBackground,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            "Help Centre",
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle back press */ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
            }
        }
    ) { paddingValues ->
        val topics = listOf(
            HelpTopic("Payment &\nRefund", Icons.Default.Payment),
            HelpTopic("Cancellation &\nExchange", Icons.Default.SyncAlt),
            HelpTopic("Cinema\nRelated...", Icons.Default.Theaters),
            HelpTopic("Ticket\nBooking...", Icons.Default.Chat)
        )

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "How can we help you?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )




            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                // Since the grid itself doesn't need to scroll within the Column,
                // we can disable its scrolling and let the Column handle it if needed.
                // For this static screen, it's not strictly necessary.
                // For a taller screen, we would make the parent Column scrollable.
                userScrollEnabled = false,
                content = {
                    items(topics) { topic ->
                        HelpTopicCard(topic)
                    }
                }
            )

        }
    }
}




@Composable
fun HelpTopicCard(topic: HelpTopic) {
    Card(
        modifier = Modifier.height(120.dp), // Fixed height to maintain grid structure
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = topic.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(iconBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = topic.icon,
                        contentDescription = topic.title,
                        tint = iconTintColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 380)
@Composable
fun HelpCentreScreenPreview() {
    MaterialTheme {
        HelpCentreScreen()
    }
}
