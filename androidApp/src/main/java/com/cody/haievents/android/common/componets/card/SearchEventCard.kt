package com.cody.haievents.android.common.componets.card


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun SearchEventCard(
    title: String = "Courtroom Chaos — Theatre Play",
    location: String = "Rangshala, Bangalore",
    dateTime: String = "21 Aug | 6:00 PM",
    imageURL : String = "",
    onClickCard: () -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left part: Image with rounded corners
            Card(
                modifier = Modifier.size(90.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                onClick = onClickCard
            ) {
                // Use Coil's AsyncImage to load the image from the URL
                AsyncImage(
                    model = imageURL,
                    contentDescription = "Event Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    // Optional: Show a placeholder while the image is loading
                    placeholder = rememberVectorPainter(image = Icons.Default.Mic),
                    // Optional: Show an error image if loading fails
                    error = rememberVectorPainter(image = Icons.Default.Mic)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right part: Column with event details
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = title,
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp
                )
                Text(
                    text = location,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Text(
                    text = dateTime,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun EventCardScreen() {
    SearchEventCard()

}


@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
        EventCardScreen()
}