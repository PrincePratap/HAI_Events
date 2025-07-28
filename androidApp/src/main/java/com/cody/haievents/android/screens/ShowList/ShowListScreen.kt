package com.cody.haievents.android.screens.ShowList




import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Note: To see actual images in the preview, you would need to add your own
 * drawable resources and replace the placeholder IDs in the `DummyOpenMicEventRepo`.
 */
object DummyOpenMicEventRepo {
    // Placeholder resource IDs
    val raw_real = 0
    val openverse = 1
    val make_noise = 2
    val charlies_mic = 3
    val podcaster = 4
    val wine_talk = 5
    val on_air = 6
    val gold_mic = 7
}

data class OpenMicEvent(
    val id: Int,
    val title: String,
    val location: String,
    val dateTime: String,
    @DrawableRes val imageResId: Int
)

// --- Main Screen Composable ---

@Composable
fun ShowListScreen() {
    val events = listOf(
        OpenMicEvent(1, "Raw & Real", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.raw_real),
        OpenMicEvent(2, "OpenVerse", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.openverse),
        OpenMicEvent(3, "Acoustic Vibes – Indie..", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.make_noise),
        OpenMicEvent(4, "Acoustic Vibes – Indie..", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.charlies_mic),
        OpenMicEvent(5, "Acoustic Vibes – Indie..", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.podcaster),
        OpenMicEvent(6, "Acoustic Vibes – Indie..", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.wine_talk),
        OpenMicEvent(7, "Acoustic Vibes – Indie..", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.on_air),
        OpenMicEvent(8, "Acoustic Vibes – Indie..", "CP, Delhi", "23 Jul, 9 PM", DummyOpenMicEventRepo.gold_mic)
    )

    val screenBackgroundColor = Color(0xFF1C1C1E)
    val headerColor = Color(0xFFC7A441)

    Scaffold(
        containerColor = screenBackgroundColor,
        topBar = {
            OpenMicTopBar(backgroundColor = headerColor)
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(events, key = { it.id }) { event ->
                OpenMicEventCard(event = event)
            }
        }
    }
}

// --- Reusable UI Components ---

@Composable
fun OpenMicTopBar(backgroundColor: Color) {

}

@Composable
fun OpenMicEventCard(event: OpenMicEvent) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                // Using a generic placeholder for preview.
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = event.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = event.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${event.location} • ${event.dateTime}",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}


// --- Preview Function ---

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Open Mic List Preview",
    widthDp = 390,
    heightDp = 844
)
@Composable
fun OpenMicListScreenPreview() {
    MaterialTheme {
        ShowListScreen()
    }
}