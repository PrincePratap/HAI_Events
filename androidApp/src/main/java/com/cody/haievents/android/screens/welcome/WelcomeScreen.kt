package com.cody.haievents.android.screens.welcome



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.R

// Note: To use this code, you would need to add actual drawable resources for the images
// and the logo. I've used placeholders (R.drawable.placeholder_*) for demonstration.
// You can create these placeholder files in your res/drawable folder.



// --- Colors and Styles ---
val darkBackgroundColor = Color(0xFF2E2E2E)
val goldColor = Color(0xFFD0B25E)


// --- Main Welcome Screen Composable ---
@Composable
fun WelcomeScreen(
    clickLogin: () -> Unit = {},
    clickRegister: () -> Unit = {}
) {
//    // Image resource IDs (replace with your actual drawables)
//    val imageRow1 = listOf(
//        R.drawable.event_theater,
//        R.drawable.event_crowd,
//        R.drawable.event_fireworks
//    )
//    val imageRow2 = listOf(
//        R.drawable.event_comedy,
//        R.drawable.event_dj,
//        R.drawable.event_concert_stage
//    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Spacer for the status bar area
            Spacer(modifier = Modifier.height(60.dp))

//            // Image Rows
//            ImageGridRow(images = imageRow1)
//            Spacer(modifier = Modifier.height(12.dp))
//            ImageGridRow(images = imageRow2)

            Spacer(modifier = Modifier.height(24.dp))

            // White content area
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                AuthContent()
            }
        }
    }
}

// --- Image Grid Row Composable ---
@Composable
fun ImageGridRow(images: List<Int>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images.size) { index ->
            Image(
                painter = painterResource(id = images[index]),
                contentDescription = null, // Decorative images
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(130.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

// --- Authentication Content Composable (Logo, Text, Buttons) ---
@Composable
fun AuthContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo Placeholder
        Image(
            painter = painterResource(id = R.drawable.img_small_logo),
            contentDescription = "HAI Events Logo",
            modifier = Modifier.height(55.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Headline
        Text(
            text = "Every Event, One App",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Sub-headline
        Text(
            text = "From comedy to concerts, explore and book all your favorite live events in one place.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Log In Button
        Button(
            onClick = { /* TODO: Handle Log In */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = goldColor)
        ) {
            Text(text = "Log In", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        OutlinedButton(
            onClick = { /* TODO: Handle Register */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, goldColor)
        ) {
            Text(text = "Register", color = goldColor, fontSize = 16.sp)
        }

        // Pushes the content up slightly from the absolute center
        Spacer(modifier = Modifier.weight(0.1f))
    }
}


// --- Preview Composable ---
@Preview(showBackground = true,)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}