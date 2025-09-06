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
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.card.BlogPostCard

// --- Data Models and Dummy Data ---


// --- Main Screen Composable ---

@Composable
fun BlogsScreen(uiState: BlogsUiState = BlogsUiState(),
                onBackPressed: () -> Unit = {},
                onBlogClick: (Int) -> Unit = {}
) {


    Scaffold(
        topBar = {
            CommonTopBar( title = "Blogs", onBackClick = onBackPressed)
        }


    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            uiState.blogsPageData?.let {
                items(it.blogs) { post ->
                    BlogPostCard(post = post, onClick = {
                        onBlogClick(post.id)
                    }   )
                }
            }
        }
    }
}


// --- Preview Function ---

@Preview(showBackground = true)
@Composable
fun BlogsScreenPreview() {
    MaterialTheme {
        BlogsScreen()
    }
}