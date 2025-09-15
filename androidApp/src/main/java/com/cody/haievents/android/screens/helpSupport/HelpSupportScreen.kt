package com.cody.haievents.android.screens.helpSupport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.card.CustomerSupportCard
import com.cody.haievents.android.screens.blogsDetail.BlogParagraph
import com.cody.haievents.android.screens.blogsDetail.BlogsDetailUiState



@Composable
fun HelpSupportScreen(
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
            CustomerSupportCard()

        }
    }
}