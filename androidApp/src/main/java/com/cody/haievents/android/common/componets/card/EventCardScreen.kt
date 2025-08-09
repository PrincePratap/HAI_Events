package com.cody.haievents.android.common.componets.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun EventCard() {
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
                elevation = CardDefaults.cardElevation(0.dp) // No shadow
            ) {
                // In a real application, you would use an Image composable like this:
                // Image(
                //     painter = painterResource(id = R.drawable.your_logo_here),
                //     contentDescription = "Event Logo",
                //     modifier = Modifier.fillMaxSize(),
                //     contentScale = ContentScale.Crop
                // )

                // For this example, a placeholder is used to represent the logo.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFD600)), // A vibrant yellow from the image
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Open Mic Logo Placeholder",
                        tint = Color.Black,
                        modifier = Modifier.size(44.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right part: Column with event details
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Courtroom Chaos — Theatre Play",
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp
                )
                Text(
                    text = "Rangshala, Bangalore",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Text(
                    text = "21 Aug | 6:00 PM",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun EventCardScreen() {
    EventCard()

}


@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
        EventCardScreen()
}