package com.cody.haievents.android.screens



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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailScreen() {
    // Define colors from the image
    val darkBackground = Color(0xFF2E2E2E)
    val buttonRed = Color(0xFFE53935)
    val textWhite = Color.White
    val textGray = Color.LightGray
    val darkGrayButton = Color(0xFF3C3C3C)
    val dividerColor = Color(0xFF555555)

    // Placeholder colors for cast images. In a real app, you'd load these with Coil/Glide.
    val castImagePlaceholders = listOf(
        Color(0xFFE1C4B5), // skin tone
        Color(0xFF8D242B), // red shirt
        Color(0xFF1E3A4B), // dark silhouette
        Color(0xFFA685B7), // purple/blue light
        Color(0xFFD69460)  // sunset silhouette
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackground
    ) {
        Column(Modifier.fillMaxSize()) {
            // Main content area that is scrollable
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    // "Movie Details" Header Text
                    Text(
                        text = "Movie Details",
                        color = textWhite,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                item {
                    // Poster with overlay controls
                    PosterSection()
                }

                item {
                    // Details below the poster
                    MovieDetailsContent(
                        textWhite = textWhite,
                        textGray = textGray,
                        darkGrayButton = darkGrayButton,
                        dividerColor = dividerColor,
                        castImagePlaceholders = castImagePlaceholders
                    )
                }
            }

            // Sticky "Book Tickets" button at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(darkBackground) // To overlay correctly on scroll
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { /* TODO: Handle booking */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonRed)
                ) {
                    Text(
                        text = "Book Tickets",
                        color = textWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun PosterSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        // In a real app, load the image from a URL or drawable using Coil:
        // painter = rememberAsyncImagePainter("https://.../poster.jpg")
        // For this example, we use a placeholder that mimics the poster's style.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFD32F2F), Color.Black)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder text to mimic the "EVIL DEAD" logo
            Text(
                text = "EVIㄴ\nDEAD",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 80.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 70.sp,
                style = MaterialTheme.typography.headlineLarge
            )
        }

        // Top banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(Color.Black.copy(alpha = 0.3f))
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "A NEW VISION FROM THE PRODUCERS OF THE ORIGINAL CLASSIC",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Overlay buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 12.dp, end = 12.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /* TODO: Handle back navigation */ },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            IconButton(
                onClick = { /* TODO: Handle more options */ },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options", tint = Color.White)
            }
        }
    }
}

@Composable
fun MovieDetailsContent(
    textWhite: Color,
    textGray: Color,
    darkGrayButton: Color,
    dividerColor: Color,
    castImagePlaceholders: List<Color>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Title and Trailer Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "EVIL DEAD RISE",
                    color = textWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Replicating typo from the image
                Text(
                    text = "HORHOR 2D.3D.4DX",
                    color = textGray,
                    fontSize = 14.sp
                )
            }
            Button(
                onClick = { /* TODO: Play trailer */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = darkGrayButton),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Watch Trailer",
                    color = textWhite,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = textWhite,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Info Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoItem(title = "Censor Rating", value = "A", textGray, textWhite)
            InfoItem(title = "Duration", value = "1hr:38min", textGray, textWhite)
            InfoItem(title = "Release date", value = "21 April 2023", textGray, textWhite)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Replicating typo from the image
        InfoItem(title = "Available in lanuage's", value = "English", textGray, textWhite)

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(color = dividerColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // Story Plot
        Text(
            text = "Story Plot",
            color = textWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Evil Dead is a 2013 American supernatural horror film directed by Fede Álvarez who co-wrote the screenplay with Rodo Sayagues. Dubbed a \"re-imagining\" of",
            color = textGray,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = dividerColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // Cast
        Text(
            text = "Cast",
            color = textWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(castImagePlaceholders.size) { index ->
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(castImagePlaceholders[index])
                )
            }
        }
    }
}

@Composable
fun InfoItem(title: String, value: String, titleColor: Color, valueColor: Color) {
    Column {
        Text(text = title, color = titleColor, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = valueColor, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen()
}