package com.cody.haievents.android.screens.ShowDetailed



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.BookingBottomBar

// Define the primary color from the image for reusability
private val goldColor = Color(0xFFD1A34F)

@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Composable
fun DetailedPagePreview() {
        ShowDetailedScreen(uiState = ShowDetailedUiState())
}

@Composable
fun ShowDetailedScreen(
    uiState: ShowDetailedUiState,
    navigationBack: () -> Unit = {},
    navigateToTicketList: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Column {

                CommonTopBar(
                    title = uiState.showDetail?.event?.title ?: "",
                    onBackClick =   navigationBack)
            }
        },
        bottomBar = {
            uiState.startPrice?.let {
                BookingBottomBar(
                    amount = it,
                    onBookClick = navigateToTicketList
                )
            }
        },
        containerColor = Color.White
    ) { paddingValues ->
        // Content that scrolls
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            uiState.showDetail?.event?.let { ImageSection(it.imagePath) }
            uiState.showDetail?.event?.let {
                ContentSection(
                    title = it.title,
                    location = it.detail.venue,
                    date = "2023-1",
                    time = it.detail.timeRange,
                    language = it.language,
                    description = it.detail.summary,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}






@Composable
private fun ImageSection(
    imageUrl: String = "", // Pass your image URL here
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.1f) // Keep the same ratio
            .background(Color(0xFF191932)), // fallback bg while loading
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = "https://haievents.com/$imageUrl",
            contentDescription = "Event Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Fits like BookMyShow banner style
        )
    }
}

@Composable
private fun ContentSection(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    date: String,
    time: String,
    language: String,
    description: String
    ) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Info Grid
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoItem(icon = Icons.Outlined.LocationOn, text = location)
                InfoItem(icon = Icons.Outlined.Schedule, text = time)
            }
            Spacer(Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoItem(icon = Icons.Outlined.CalendarToday, text = date)
                InfoItem(icon = Icons.Outlined.Language, text = language)
            }
        }

        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

        // About the Show Section
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "About the Show",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )



            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "Read more",
                    color = goldColor,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Read more",
                    tint = goldColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun InfoItem(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
    }
}