package com.cody.haievents.android.screens.categorieShows

import androidx.compose.runtime.Composable


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.Show.model.EventDatas
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.card.OpenMicCard


data class OpenMicItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageResId: Int // Resource ID for the image
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesShowsScreen(
    uiState: CategoriesShowsUiState = CategoriesShowsUiState(),
    onBackClick: () -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Categories",
                onBackClick = onBackClick
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF0EFEF)) // Light grey background
            ) {

                val events: List<EventDatas> =
                    uiState.homePageData
                        ?.items
                        ?.filter { it.type == "movie" }        // optional, if you only want movies
                        ?.map { it.data }                      // unwrap to EventDatas
                        .orEmpty()

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = events,
                        key = { it.id }                        // stable keys help with recomposition
                    ) { event ->
                        OpenMicCard(
                            event = event,
                            onClick = { onItemClick(event.id) }
                        )
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewOpenMicListScreen() {
    MaterialTheme {
        CategoriesShowsScreen()
    }
}