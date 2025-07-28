package com.cody.haievents.android.screens.Blogs



import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

// --- Data Models and Dummy Data ---

data class BlogPost(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)

object BlogRepo {
    // Replace with your actual drawable resource ID
    private const val BLOG_IMAGE = 0

    val posts = listOf(
        BlogPost(
            id = 1,
            imageRes = BLOG_IMAGE,
            title = "5 Tips to Own the Stage Like a Pro",
            description = "Confidence, timing & delivery — all you need to make the crowd love you"
        ),
        BlogPost(
            id = 2,
            imageRes = BLOG_IMAGE,
            title = "5 Tips to Own the Stage Like a Pro",
            description = "Confidence, timing & delivery — all you need to make the crowd love you"
        )
    )
}

// --- Main Screen Composable ---

@Composable
fun BlogsScreen() {
    val goldColor = Color(0xFFC7A441)
    val screenBackgroundColor = Color(0xFFF5F5F5) // A light gray background

    Scaffold(
        containerColor = screenBackgroundColor,
        topBar = { BlogHeader(backgroundColor = goldColor) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(BlogRepo.posts, key = { it.id }) { post ->
                BlogPostCard(post = post, buttonColor = goldColor)
            }
        }
    }
}


// --- Reusable UI Components ---

@Composable
fun BlogHeader(backgroundColor: Color) {
    Surface(
        color = backgroundColor,
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Our Latest Blogs",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Thoughts worth sharing",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun BlogPostCard(post: BlogPost, buttonColor: Color) {
    val cardBackgroundColor = Color(0xFFFDF8EE)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                // Using a generic placeholder for preview robustness
                painter = painterResource(id = android.R.drawable.ic_menu_camera),
                contentDescription = post.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = post.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black.copy(alpha = 0.85f),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.description,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* TODO: Handle read more click */ },
                modifier = Modifier
                    .fillMaxWidth(0.9f) // Button is not full width
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(
                    text = "Read More",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// --- Preview Function ---

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES, // Use dark mode to get the dark frame
    name = "Blogs Screen Preview",
    widthDp = 390,
    heightDp = 844
)
@Composable
fun BlogsScreenPreview() {
    MaterialTheme {
        BlogsScreen()
    }
}