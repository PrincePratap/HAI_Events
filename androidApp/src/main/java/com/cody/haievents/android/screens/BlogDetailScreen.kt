package com.cody.haievents.android.screens


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
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

// --- Dummy Data for the Blog Post ---
object BlogPostContent {
    val title = "5 Tips to Own the Stage Like a Pro"
    // Replace with your actual drawable resource ID
    val imageRes = 0

    val bodyParagraphs = listOf(
        "For many, performing on stage for the first time isn't just about reciting a piece — it's about stepping out of one's own fears and owning a moment that defines courage. I still remember the first time I held a mic on an open stage. The lights were too bright, the audience too silent, and my heartbeat too loud. I was holding a folded sheet of paper — my poem that I had rewritten a hundred times but still wasn't sure about.",
        "As I began reading, my voice trembled, my hands shook, and I forgot the next line halfway. But then something magical happened. A few heads in the audience nodded, someone smiled, and suddenly the words came back to me like a familiar song. I finished the piece with shaky breath, looked up — and people clapped. Not out of sympathy, but connection. That's when I realised: this stage, this mic, this moment — it belonged to me.",
        "What followed was more than just applause. A stranger came up to me after the show and said, \"Your poem felt like my story.\" That one sentence meant more than a hundred likes online. It meant I had reached someone. And that's the beauty of performing — it's raw, real, and completely personal. Since then, I've performed many times, at many places, but that first moment remains unmatched. Every new performer deserves to feel that rush — of fear, adrenaline, and unexpected joy. So if you've written something, practiced something, dreamed of something — go up there and share it. Because someone in the crowd is waiting to feel seen."
    )
}

// --- Main Screen Composable ---

@Composable
fun BlogDetailScreen() {
    val goldColor = Color(0xFFC7A441)
    val screenBackgroundColor = Color(0xFF1C1C1E)

    Scaffold(
        containerColor = screenBackgroundColor,
        topBar = { BlogDetailTopBar(backgroundColor = goldColor) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Blog Title
            Text(
                text = BlogPostContent.title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Blog Image
            Image(
                // Using a generic placeholder for preview robustness
                painter = painterResource(id = android.R.drawable.ic_menu_camera),
                contentDescription = BlogPostContent.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Blog Body
            BlogPostContent.bodyParagraphs.forEach { paragraph ->
                Text(
                    text = paragraph,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    lineHeight = 24.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


// --- Reusable UI Components ---

@Composable
fun BlogDetailTopBar(backgroundColor: Color) {
    Surface(
        color = backgroundColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                text = "Blogs",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

// --- Preview Function ---

@Preview(
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Blog Detail Screen Preview",
    widthDp = 390,
    heightDp = 844
)
@Composable
fun BlogDetailScreenPreview() {
    MaterialTheme {
        BlogDetailScreen()
    }
}