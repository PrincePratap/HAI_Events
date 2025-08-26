package com.cody.haievents.android.common.componets.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cody.haievents.android.common.componets.InfoItem
import com.cody.haievents.auth.data.model.FeaturedItem
import com.cody.haievents.auth.data.model.ItemData
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TheatreShowCard(show: ItemData, onItemClick: (Int) -> Unit) {
    Card(
        modifier =
        Modifier.width(320.dp).clickable{ show.id?.let { onItemClick(it) } }  ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Placeholder for the image. In a real app, you would use a library
            // like Coil or Glide to load an image from a URL.

            AsyncImage(
                model = "https://haievents.com/${show.imagePath}",  // URL from your API
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
//                error = painterResource(com.cody.haievents.android.R.drawable.image_placeholder),  // optional fallback image resource
//                placeholder = painterResource(R.drawable.image_placeholder)  // optional placeholder
            )


            Column(modifier = Modifier.padding(16.dp)) {
                show.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                show.venue?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    show.date?.let { InfoItem(icon = Icons.Default.CalendarToday, text = it) }
                    show.time?.let { InfoItem(icon = Icons.Default.Schedule, text = it) }
                }
            }
        }
    }
}


@Preview()
@Composable
fun TheatreShowCardPreviewLight() {

        TheatreShowCard(
            show = ItemData(
                id = 1,
                title = "Hamlet",
                slug = "hamlet-2025",
                imagePath = "https://images.unsplash.com/photo-1515165562835-c3b8c2e3a1a5?q=80&w=1080&auto=format&fit=crop",
                categoryId = 3,
                date = "Aug 22, 2025",
                time = "7:30 PM",
                summary = "A timeless Shakespearean tragedy performed by a stellar cast.",
                venue = "Grand Theatre, Mumbai"
            ),
            onItemClick = { /* no-op for preview */ }
        )

}

@Preview(
    name = "TheatreShowCard • Dark",
    showBackground = true,
    widthDp = 360,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TheatreShowCardPreviewDark() {

        TheatreShowCard(
            show = ItemData(
                id = 2,
                title = "Macbeth",
                slug = "macbeth-2025",
                imagePath = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?q=80&w=1080&auto=format&fit=crop",
                categoryId = 3,
                date = "Aug 24, 2025",
                time = "8:00 PM",
                summary = "Dark ambition and fate collide in this gripping production.",
                venue = "City Arts Centre, Pune"
            ),
            onItemClick = { /* no-op for preview */ }
        )

}
