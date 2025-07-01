package com.cody.haievents.android.common.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.theming.MyBackgroundColor

/**
 * Data class to hold the information for each event category card.
 * In a real app, imageResId would point to your drawable resources.
 */
data class EventCardInfo(
    val titleLine1: String,
    val titleLine2: String,
    val eventCount: String,
    val gradientColors: List<Color>,
    // val imageResId: Int // Uncomment and use R.drawable.your_image in a real project
)

/**
 * The main composable for the "Best Events This Week" section.
 */
@Composable
fun BestEventsSection() {
    // Sample data mirroring the UI in the image.
    val eventCards = listOf(
        EventCardInfo(
            titleLine1 = "Plan for",
            titleLine2 = "Today",
            eventCount = "80+ Events",
            gradientColors = listOf(Color.Transparent, Color(0xD9C48EAA))
        ),
        EventCardInfo(
            titleLine1 = "Plan for",
            titleLine2 = "Tomorrow",
            eventCount = "130+ Events",
            gradientColors = listOf(Color.Transparent, Color(0xD98DB33D))
        ),
        EventCardInfo(
            titleLine1 = "Weekend",
            titleLine2 = "Plans",
            eventCount = "325+ Events",
            gradientColors = listOf(Color.Transparent, Color(0xD966A4A7))
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyBackgroundColor) ,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Best Events This Week",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Monday to Sunday, we got you covered",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // A LazyRow is efficient for horizontal scrolling lists.
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 2.dp) // space for card elevation shadow
        ) {
            items(eventCards) { cardInfo ->
                EventCard(cardInfo = cardInfo)
            }
        }
    }
}

/**
 * A reusable composable for the individual event cards.
 */
@Composable
fun EventCard(cardInfo: EventCardInfo) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // --- Placeholder for Background Image ---
            // In a real app, you would use the Image composable with a resource ID.
            /*
            Image(
                painter = painterResource(id = cardInfo.imageResId),
                contentDescription = cardInfo.titleLine2,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            */
            // For this preview, we use a simple colored box.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE0E0E0)) // A light gray placeholder
            )
            // --- End of Placeholder ---

            // Gradient Overlay that starts from transparent and fades to a solid color.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = cardInfo.gradientColors,
                            startY = 100f // Start gradient lower on the card
                        )
                    )
            )

            // Text content aligned to the bottom-left of the card.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = cardInfo.titleLine1.uppercase(),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 20.sp
                    )
                    Text(
                        text = cardInfo.titleLine2.uppercase(),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = cardInfo.eventCount,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BestEventsSectionPreview() {
    MaterialTheme {
        BestEventsSection()
    }
}