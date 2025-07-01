package com.cody.haievents.android.screens.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.recommendedMoviesList
import com.cody.haievents.android.common.componets.MovieCard
import androidx.compose.foundation.lazy.grid.items





@Composable
fun MovieScreen(navigateToMovieDetails : () -> Unit ){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        items(recommendedMoviesList) { movie ->
            MovieCard(movie = movie, clickOnMovie = {
                navigateToMovieDetails()
            })
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            MovieScreen(navigateToMovieDetails = {

            })
        }
    }
}
