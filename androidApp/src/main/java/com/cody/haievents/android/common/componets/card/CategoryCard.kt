package com.cody.haievents.android.common.componets.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import com.cody.haievents.Show.model.CategoryItem

@Composable
fun CategoryCard(
    category: CategoryItem,
    onClick: (CategoryItem) -> Unit = {}
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(16.dp)) // keep ripple bounded
            .clickable { onClick(category) },
        contentAlignment = Alignment.Center
    ) {
        // Background Image from URL (with fallback placeholder)
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("https://haievents.com/"+category.imagePath)
                .crossfade(true)
                .build(),
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_menu_report_image),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        )

        // Dark gradient overlay for text readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.10f),
                            Color.Black.copy(alpha = 0.60f)
                        )
                    )
                )
        )

        // Category Name
        Text(
            text = category.name,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    CategoryCard(
        category = CategoryItem(
            id = 1,
            name = "OPEN MIC",
            imagePath = "https://picsum.photos/600/400", // demo URL
            createdAt = "",
            updatedAt = ""
        ),
        onClick = { /* preview click */ }
    )
}
