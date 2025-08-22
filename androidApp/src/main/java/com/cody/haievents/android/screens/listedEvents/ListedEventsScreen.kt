package com.cody.haievents.android.screens.listedEvents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
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

// --- Data Models for better structure ---

enum class EventStatus(val displayName: String, val color: Color) {
    APPROVED("Approved", Color(0xFF1E8E3E)), // Green
    PENDING("Pending", Color(0xFFE6A700))     // Amber/Yellow
}

data class Event(
    val imageRes: Int,
    val title: String,
    val organizer: String,
    val status: EventStatus,
    val location: String,
    val date: String,
    val description: String
)

// Sample data to populate the list
//val eventList = listOf(
//    Event(
//        imageRes = R.drawable.abracadabra_poster, // Replace with your drawable
//        title = "Pune Poetry Jam",
//        organizer = "By Poetry",
//        status = EventStatus.APPROVED,
//        location = "Kamani Auditorium, Delhi",
//        date = "Sunday, 20 July 2025",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"
//    ),
//    Event(
//        imageRes = R.drawable.music_concert_poster, // Replace with your drawable
//        title = "Virtual Music Concert",
//        organizer = "By Poetry",
//        status = EventStatus.PENDING,
//        location = "Kamani Auditorium, Delhi",
//        date = "Sunday, 20 July 2025",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"
//    )
//)

// --- Main Screen Composable ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListedEventsScreen() {
    // Assuming a theme with a dark background is applied elsewhere,
    // or we can set it here.
    Surface(color = Color(0xFF2E2E2E), modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Your Listed Events",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle back press */ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle add event */ }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Event",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFC4A654) // Golden color
                    )
                )
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 24.dp)
            ) {
//                items(eventList) { event ->
//                    EventCard(event = event)
//                }
            }
        }
    }
}

// --- Reusable Component for an Event Card ---



// --- Helper Composables for smaller UI parts ---

@Composable
fun StatusBadge(status: EventStatus) {
    val backgroundColor = when (status) {
        EventStatus.APPROVED -> Color(0xFFD7F9E3)
        EventStatus.PENDING -> Color(0xFFFFF4D4)
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(color = status.color, shape = CircleShape)
            )
            Text(
                text = status.displayName,
                color = Color.Black.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 14.sp, color = Color.DarkGray)
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = text, fontWeight = FontWeight.SemiBold)
        }
    }
}

// --- Preview Composable ---

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListedEventsScreenPreview() {
    // You might want to wrap this in your app's theme for accurate previews
    // AppTheme {
    ListedEventsScreen()
    // }
}