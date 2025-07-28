package com.cody.haievents.android.screens.explore

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Note: To make the preview work with actual images, you would need to add
 * drawable resources to your project's `res/drawable` folder and replace
 * the placeholder IDs in the `DummyImageRepo` object.
 */
object DummyImageRepo {
    // In a real app, these would be your actual resource IDs, e.g., R.drawable.open_mic
    val open_mic = 0
    val theatre_show = 1
    val concert = 2
    val stand_up_comedy = 3
}

// --- Main Screen Composable ---

@Composable
fun ExploreScreen() {
    // Data for the categories. In a real app, this would come from a ViewModel.
    val categories = listOf(
        Category("OPEN MIC", DummyImageRepo.open_mic),
        Category("THEATRE SHOW", DummyImageRepo.theatre_show),
        Category("CONCERT", DummyImageRepo.concert),
        Category("STAND-UP\nCOMEDY", DummyImageRepo.stand_up_comedy) // Use \n for an explicit line break
    )

    // Colors matching the design
    val screenBackgroundColor = Color(0xFF1C1C1E)
    val headerBackgroundColor = Color(0xFFC7A441)
    val exploreTitleColor = Color.White.copy(alpha = 0.8f)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = screenBackgroundColor
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // "Explore" title
                Text(
                    text = "Explore",
                    color = exploreTitleColor,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 8.dp)
                )
            }

            item {
                // Gold header card
                CategoryHeader(backgroundColor = headerBackgroundColor)
            }

            // List of category image cards
            items(categories) { category ->
                CategoryCard(category = category)
            }
        }
    }
}

// --- Data Class and Helper Composables ---

data class Category(val name: String, @DrawableRes val imageResId: Int)

@Composable
fun CategoryHeader(backgroundColor: Color) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Explore by Category",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Discover events that match your vibe",
                color = Color.White.copy(alpha = 0.9f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun CategoryCard(category: Category) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        // Background Image - Using a placeholder for preview robustness
        Image(
            // To see real images, replace this with your actual painter resources.
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray) // Placeholder color
        )

        // Dark gradient scrim for text readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.1f),
                            Color.Black.copy(alpha = 0.6f)
                        ),
                        startY = 0f,
                        endY = 400f
                    )
                )
        )

        // Category Text
        Text(
            text = category.name,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp // Adjust line height for multi-line text
        )
    }
}

// --- Preview Function ---

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Explore Screen Preview",
    widthDp = 390, // Approximate width of the device in the image
    heightDp = 844 // Approximate height of the device in the image
)
@Composable
fun ExploreScreenPreview() {
    MaterialTheme {
        ExploreScreen()
    }
}