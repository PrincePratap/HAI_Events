package com.cody.haievents.android.screens.allCategories

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
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.card.CategoryCard
import com.cody.haievents.android.common.theming.screenBackground


// --- Main Screen Composable ---

@Composable
fun AllCategoriesScreen(
    onCategoryClick: (Int) -> Unit = {},
    uiState: AllCategoriesUiState = AllCategoriesUiState()
) {
    // Data for the categories. In a real app, this would come from a ViewModel.


    // Colors matching the design


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = screenBackground
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            item {
               CommonTopBar(title = "All Categories", onBackClick = { /* Handle back click */ })
            }

            // List of category image cards
            uiState.homePageData?.let {
                items(it.data) { category ->
                    CategoryCard(category = category,onClick = { onCategoryClick(it.id) })
                }
            }
        }
    }
}






// --- Preview Function ---

@Preview(showBackground = true, name = "Explore Screen Preview")
@Composable
fun ExploreScreenPreview() {
    MaterialTheme {
        AllCategoriesScreen()
    }
}