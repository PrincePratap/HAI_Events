package com.cody.haievents.android.screens.myEvents



import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cancel
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

// --- Data Models ---

enum class EventStatus(
    val displayText: String,
    val color: Color,
    val backgroundColor: Color
) {
    APPROVED("Approved", Color(0xFF388E3C), Color(0xFFE8F5E9)),
    PENDING("Pending", Color(0xFFF9A825), Color(0xFFFFFDE7))
}

data class ListedEvent(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val title: String,
    val organizer: String,
    val location: String,
    val date: String,
    val description: String,
    val status: EventStatus
)

object DummyEventsRepo {
    // Replace with your actual drawable resource IDs
    private const val ABRACADABRA_IMAGE = 0
    private const val CONCERT_IMAGE = 1

    val events = listOf(
        ListedEvent(
            id = 1,
            imageRes = ABRACADABRA_IMAGE,
            title = "Pune Poetry Jam",
            organizer = "By Poetry",
            location = "Kamani Auditorium, Delhi",
            date = "Sunday, 20 July 2025",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
            status = EventStatus.APPROVED
        ),
        ListedEvent(
            id = 2,
            imageRes = CONCERT_IMAGE,
            title = "Virtual Music Concert",
            organizer = "By Poetry",
            location = "Kamani Auditorium, Delhi",
            date = "Sunday, 20 July 2025",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
            status = EventStatus.PENDING
        )
    )
}

// --- Main Screen Composable ---

@Composable
fun MyEventsScreen() {
    val goldColor = Color(0xFFC7A441)
    val screenBackgroundColor = Color(0xFF1C1C1E)

    Scaffold(
        containerColor = screenBackgroundColor,
        topBar = { ListedEventsTopBar(backgroundColor = goldColor) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(DummyEventsRepo.events, key = { it.id }) { event ->
                ListedEventCard(event = event)
            }
        }
    }
}

// --- Reusable UI Components ---

@Composable
fun ListedEventsTopBar(backgroundColor: Color) {
    Surface(color = backgroundColor, shadowElevation = 4.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text("Your Listed Events", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            IconButton(onClick = { /* Handle add event */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add Event", tint = Color.White, modifier = Modifier.size(28.dp))
            }
        }
    }
}

@Composable
fun ListedEventCard(event: ListedEvent) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_report_image), // Placeholder
                contentDescription = event.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = event.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    EventStatusBadge(status = event.status)
                }
                Text(event.organizer, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))
                InfoRow(icon = Icons.Default.LocationOn, text = event.location)
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(icon = Icons.Default.CalendarToday, text = event.date)
                Spacer(modifier = Modifier.height(16.dp))
                Text(event.description, fontSize = 14.sp, color = Color.DarkGray, lineHeight = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))
                ActionButtonsRow()
            }
        }
    }
}

@Composable
fun EventStatusBadge(status: EventStatus) {
    Surface(
        shape = CircleShape,
        color = status.backgroundColor,
        modifier = Modifier.height(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(status.color, CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(status.displayText, color = status.color, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.DarkGray, fontSize = 14.sp)
    }
}

@Composable
fun ActionButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ActionButton(text = "Edit", icon = Icons.Default.Edit, contentColor = Color(0xFF0D47A1), backgroundColor = Color(0xFFE3F2FD), modifier = Modifier.weight(1f))
        ActionButton(text = "Cancel", icon = Icons.Default.Cancel, contentColor = Color(0xFFB71C1C), backgroundColor = Color(0xFFFFEBEE), modifier = Modifier.weight(1f))
        ActionButton(text = "Share", icon = Icons.Default.Share, contentColor = Color(0xFF1B5E20), backgroundColor = Color(0xFFE8F5E9), modifier = Modifier.weight(1f))
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    contentColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        border = BorderStroke(1.dp, contentColor.copy(alpha = 0.5f)),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }
    }
}

// --- Preview Function ---

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Listed Events Screen Preview",
    widthDp = 390,
    heightDp = 844
)
@Composable
fun ListedEventsScreenPreview() {
    MaterialTheme {
        MyEventsScreen()
    }
}