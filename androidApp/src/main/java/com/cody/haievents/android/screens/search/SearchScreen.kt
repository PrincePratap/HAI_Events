package com.cody.haievents.android.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.ShowsSearchBar
import com.cody.haievents.android.common.componets.card.SearchEventCard
import com.ramcosta.composedestinations.annotation.Destination

@Composable
fun SearchScreen(
    uiState: SearchUiState = SearchUiState(),
    onQueryChange: (String) -> Unit
) {
    ShowsSearchBar(query = uiState.query, onQueryChange =  onQueryChange  )

//    uiState.showList?.let { show ->
//        Text(
//            text = "Latest Updates",
//            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
//            style = MaterialTheme.typography.titleMedium,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//
//
//        items(show) { headline ->
//            Surface(
//                modifier = Modifier
//                    .width(280.dp) // Fixed width for cards so next peek in
//                    .padding(vertical = 4.dp)
//            ) {
//                SearchEventCard(
//title =
//                )
//            }
//        }
//
//    }


}