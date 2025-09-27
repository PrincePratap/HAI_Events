package com.cody.haievents.android.screens.ShowDetailed



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.BookingBottomBar
// Small utility to avoid ripple on the "Read more" text/icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.composed
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cody.haievents.android.common.componets.dialog.ChairSelectionDialog

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
    navigateToTicketList:  () -> Unit = {},
    navigateToGaneshTheater : (Int) -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = uiState.showDetail?.event?.title ?: "",
                onBackClick =   navigationBack)
        },
        bottomBar = {
            uiState.startPrice?.let {
                BookingBottomBar(
                    amount = it,
                    onBookClick = {
                        if(uiState.showDetail?.theaterType == 2){
                            showDialog = true
                        }else{
                            navigateToTicketList()
                        }
                    }
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

    if (showDialog) {
        ChairSelectionDialog(
            onDismiss = { showDialog = false },
            onSelectCushion = {
                showDialog = false
                navigateToGaneshTheater(1) // Navigate further (Cushion)
            },
            onSelectPlastic = {
                showDialog = false
                navigateToGaneshTheater(2) // Navigate further (Plastic)
            }
        )
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ContentSection(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    date: String,          // show as-is
    time: String,
    language: String,
    description: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        // Venue (full width)
        InfoRowLabeled(
            icon = Icons.Outlined.LocationOn,
            label = "Venue",
            value = location
        )

        // Time (full width)
        InfoRowLabeled(
            icon = Icons.Outlined.Schedule,
            label = "Time",
            value = time
        )

        // Chips (wrap automatically)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoChip(
                icon = Icons.Outlined.CalendarToday,
                text = date
            )
            InfoChip(
                icon = Icons.Outlined.Language,
                text = language.uppercase()
            )
        }

        Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

        // About
        Text(
            text = "About the Show",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        ExpandableText(
            text = description,
            collapsedLines = 4
        )
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

@Composable
private fun InfoRowLabeled(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .size(20.dp)
                .padding(top = 2.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun InfoChip(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/** Collapsible long text with "Read more / Read less" */
@Composable
 fun ExpandableText(
    text: String,
    collapsedLines: Int = 3
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedLines,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = if (expanded) "Read less" else "Read more",
                color = goldColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(end = 2.dp)
                    .noRippleClickable { expanded = !expanded }
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = goldColor,
                modifier = Modifier
                    .size(20.dp)
                    .rotate(if (expanded) 180f else 0f)
                    .noRippleClickable { expanded = !expanded }
            )
        }
    }
}


private fun Modifier.noRippleClickable(onClick: () -> Unit) = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
        role = Role.Button,
        onClick = onClick
    )
}
//        ChairSelectionDialog(
//            onDismiss = {},
//            onSelect = {}
//        )


