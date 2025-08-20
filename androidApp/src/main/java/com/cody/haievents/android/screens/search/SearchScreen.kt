package com.cody.haievents.android.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.ShowsSearchBar
import com.cody.haievents.android.common.componets.card.SearchEventCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Correct import for LazyColumn items


@Composable
fun SearchScreen(
    uiState: SearchUiState, // Removed default value to encourage providing state
    onQueryChange: (String) -> Unit,
    navigateToShowDetails: (Int) -> Unit
) {
    // 1. Use a Column to structure the screen vertically
    Column(modifier = Modifier.fillMaxSize()) {
        ShowsSearchBar(
            query = uiState.query,
            onQueryChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Let's use the null-safe call on the list directly
        if (!uiState.showList.isNullOrEmpty()) {
            // 2. Add the LazyColumn for a scrollable list of items
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp) // Adds space between items
            ) {
                // 3. Add a header as the first item in the LazyColumn
                // This makes the header scroll away with the list content.
                item {
                    Text(
                        text = "Latest Updates",
                        style = MaterialTheme.typography.titleLarge, // Using titleLarge for more emphasis
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Consider using MaterialTheme.colorScheme.onSurface
                    )
                }

                // 4. Use the `items` extension function for the list
                items(
                    items = uiState.showList,
                    key = { show -> show.data.id } // Providing a key is a performance best practice
                ) { showItem ->
                    // 5. Populate the card with data from the list item
                    SearchEventCard(
                        onClickCard = {navigateToShowDetails(showItem.data.id)},
                        title = showItem.type,
                        location = showItem.data.venue,
                        dateTime = showItem.data.date + " " + showItem.data.time,
                        imageURL = showItem.data.image
                    )
                }
            }
        }
    }
}