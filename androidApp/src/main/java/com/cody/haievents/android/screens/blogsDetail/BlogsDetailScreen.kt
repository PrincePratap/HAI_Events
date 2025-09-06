package com.cody.haievents.android.screens.blogsDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.Alignment

import com.cody.haievents.android.common.components.CommonTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogsDetailScreen(
    uiState: BlogsDetailUiState = BlogsDetailUiState(),
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { CommonTopBar(title = "Blog Detail", onBackClick = onBackClick) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage ?: "Something went wrong",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp)
                    )
                }

                else -> {
                    val blog = uiState.homePageData?.blog // Expecting Blog? (id, title, slug, content, image, date, time)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(Modifier.height(16.dp))

                        // Title
                        Text(
                            text = blog?.title.orEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4C3E00)
                            )
                        )

                        // Date • Time
                        if (!blog?.date.isNullOrBlank() || !blog?.time.isNullOrBlank()) {
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = listOfNotNull(blog?.date, blog?.time)
                                    .filter { !it.isNullOrBlank() }
                                    .joinToString(" • "),
                                modifier = Modifier.padding(horizontal = 16.dp),
                                style = MaterialTheme.typography.labelLarge.copy(
                                    color = Color(0xFF6B5E2A)
                                )
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        // Image from URL (handles full or relative path)
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(resolveImageUrl(blog?.image))
                                .crossfade(true)
                                .build(),
                            contentDescription = blog?.title ?: "Blog image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .padding(horizontal = 16.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.height(16.dp))

                        // Content
                        BlogParagraph(
                            text = blog?.content.orEmpty()
                        )

                        Spacer(Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

/**
 * If API returns a relative image like "img/blogs/file.jpg",
 * prepend your backend base url here.
 */
private fun resolveImageUrl(path: String?): String? {
    if (path.isNullOrBlank()) return null
    val looksAbsolute = path.startsWith("http://") || path.startsWith("https://")
    return if (looksAbsolute) path else "https://haievents.com/$path"
}

@Composable
fun BlogParagraph(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodyLarge.copy(
            color = Color(0xFF4C3E00),
            lineHeight = 24.sp
        )
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun BlogsDetailScreenPreview() {
    MaterialTheme {
        BlogsDetailScreen()
    }
}
