package com.cody.haievents.android.common.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class to represent a movie
data class Movie(
    val title: String,
    val rating: Double,
    val voteCount: String,
    val isPromoted: Boolean,
    val placeholderColor: Color // For image placeholder in preview
)

// Sample data inspired by the image
val recommendedMoviesList = listOf(
    Movie("Maa", 7.1, "1.8K", true, Color(0xFF4A4A4A)),
    Movie("Kannappa", 7.3, "8.7K", true, Color(0xFF6B5B3B)),
    Movie("Sitaare Zameen Par", 8.5, "37.6K", false, Color(0xFFFBC02D)),
    Movie("Project K", 9.0, "120K", true, Color(0xFF1E88E5)),
    Movie("Fighter", 6.9, "22.1K", false, Color(0xFF6A1B9A)),
    Movie("Echoes of Silence", 7.7, "15.4K", false, Color(0xFF37474F)),
    Movie("Veeram", 8.2, "10.2K", true, Color(0xFFB71C1C)),
    Movie("The Last Monsoon", 8.4, "4.3K", false, Color(0xFF00897B)),
    Movie("Mirage", 6.5, "2.8K", false, Color(0xFF5D4037)),
    Movie("Dhruva Nakshatra", 7.8, "11.9K", true, Color(0xFF4CAF50)),
    Movie("Quantum Leap", 7.6, "9.5K", true, Color(0xFF039BE5)),
    Movie("Pehchaan", 8.1, "3.6K", false, Color(0xFFF57C00))
)

// Main composable for the "Recommended Movies" section
@Composable
fun RecommendedMovies() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {
        // Section Header: "Recommended Movies" and "See All"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recommended Movies",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { /* Handle See All click */ }) {
                Text(
                    text = "See All ›",
                    color = Color(0xFFE53935),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Horizontally scrolling list of movies
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recommendedMoviesList) { movie ->
                MovieCard(movie = movie)
            }
        }
    }
}

// Composable for a single movie item card
@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.then(Modifier.width(140.dp)) // Default width if not overridden
    ) {
        // Poster with a "PROMOTED" tag overlay
        Box(
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .background(movie.placeholderColor)
            )

            if (movie.isPromoted) {
                Text(
                    text = "PROMOTED",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                        .background(
                            color = Color(0xFFE53935),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                tint = Color(0xFFE53935),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = movie.rating.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = "${movie.voteCount} votes",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            maxLines = 2
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RecommendedMoviesPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RecommendedMovies()
        }
    }
}