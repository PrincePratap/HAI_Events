package com.cody.haievents.android.common.componets



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cody.haievents.android.common.componets.card.TheatreShowCard
import com.cody.haievents.auth.data.model.FeaturedCategory
import com.cody.haievents.auth.data.model.FeaturedItem
import com.cody.haievents.auth.data.model.ItemData


@Composable
fun TheatreShowsCard(item: FeaturedCategory? = null, onItemClick: (Int) -> Unit = {}){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
            item?.name?.let { TheatreShowsHeader(headerText = it) }
            Spacer(modifier = Modifier.height(16.dp))
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (item != null) {
                items(item.items) { show ->
                    show.data?.let { TheatreShowCard(show = it, onItemClick = {onItemClick(it)}) }
                }
            }
        }
    }
}

@Composable
fun TheatreShowsHeader(headerText: String = ""){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = headerText,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            // The yellow underline
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(3.dp)
                    .background(Color(0xFFE5B80B)) // Gold/Yellow color from image
            )
        }
        Text(
            text = "View All",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.clickable {}
        )
    }
}


@Composable
fun InfoItem(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true ,)
@Composable
fun TheatreShowsScreenPreview() {

    TheatreShowsCard()

}