package com.cody.haievents.android.common.componets


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// You need to add the image from the prompt to your `res/drawable` folder.
// For this example, I'm creating a placeholder R class.
// In a real project, this would be generated automatically.
private object R {
    object drawable {
        const val theatre_image = 0
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Assuming you have a Material3 theme set up
            // YourTheme {
            ScreenPreview()
            // }
        }
    }
}

@Composable
fun EventCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.width(320.dp)
    ) {
        Column {
            Image(
                // Replace R.drawable.theatre_image with the actual ID of your image asset
                painter = painterResource(id = R.drawable.theatre_image),
                contentDescription = "Theatre stage with comedy and tragedy masks",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    // Clip only the top corners of the image to match the card's shape
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            // Content section with padding
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Title Text
                Text(
                    text = "The Last Leaf – A Live Drama",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Subtitle/Location Text
                Text(
                    text = "The Verse Lounge, Delhi",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant // A slightly muted color
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Row for Date and Time information
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Date Info
                    Icon(
                        // The icon in the image is a grid calendar.
                        // Icons.Default.CalendarToday is a standard Material icon.
                        // For a closer match, you might use Icons.Filled.CalendarMonth
                        // from the 'androidx.compose.material:material-icons-extended' library.
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Date",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "21 July, Wednesday",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    // Time Info
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Time",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "6:30 PM – 7:00 PM",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    MaterialTheme {
        // Use a Box to center the content and set a background color similar to the original image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9)),
            contentAlignment = Alignment.Center
        ) {
            // This Row simulates the main card and the orange element peeking from the side
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                EventCard()
                Spacer(modifier = Modifier.width(4.dp))
                // A Box to represent the orange UI element on the right
                Box(
                    modifier = Modifier
                        .width(25.dp)
                        .height(350.dp)
                        .background(
                            color = Color(0xFFF5A623), // An orange color
                            shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                        )
                )
            }
        }
    }
}