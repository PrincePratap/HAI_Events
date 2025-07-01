package com.cody.haievents.android.common.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.theming.LargeTextSize
import com.cody.haievents.android.common.theming.MediumTextSize
import com.cody.haievents.android.common.theming.SmallTextSize

// Data model for a category
data class Category(
    val name: String,
    val emoji: String
)

// Sample data representing the categories shown in the image
val travelCategories = listOf(
    Category("Movies", "🎬"),           // Film slate
    Category("HSBC Lounge", "🛋️"),     // Couch for lounge
    Category("Music Shows", "🎵"),      // Musical note
    Category("Comedy Shows", "😂"),     // Face with tears of joy
    Category("Plays", "🎭"),            // Performing arts mask
    Category("Performances", "🎤")      // Microphone
)


@Composable
fun CategoriesComponent() {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        // --- Title ---
        Text(
            text = "Categories",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = LargeTextSize
            ),
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 4.dp)
        )

        // --- Categories List ---
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(travelCategories) { category ->
                CategoryItem(category = category)
            }
        }
    }
}


@Composable
private fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Smaller Circular Icon
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(56.dp) // reduced from 72.dp
                .shadow(elevation = 4.dp, shape = CircleShape, spotColor = Color.Gray.copy(alpha = 0.3f))
                .background(Color(0xFFF5F5F5), CircleShape)
        ) {
            Text(
                text = category.emoji,
                fontSize = 24.sp // reduced from 36.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // reduced from 12.dp

        // Smaller Label
        Text(
            text = category.name,
            color = Color.Black,
            fontSize = 12.sp // or you can still use SmallTextSize if it’s appropriate
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesComponentPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            CategoriesComponent()
        }
    }
}