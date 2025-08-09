package com.cody.haievents.android.common.componets
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun BookingBottomBar(
    amount: Int = 0,
    onBookClick: () -> Unit = {}
) {
    // Use Surface for the card-like container with a white background.
    Surface(
        color = MaterialTheme.colorScheme.surface, // Typically white in a light theme
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp // Adds a subtle shadow to lift it from the background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left side: Pricing information
            Column {
                Text(
                    text = "Starts From",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "₹ $amount",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Right side: Custom Gradient "Book" Button
            GradientButton(
                text = "Book",
                onClick =  onBookClick
            )
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // A horizontal gradient matching the image's gold colors.
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFFBE79A), // Lighter gold
            Color(0xFFC9A249)  // Darker gold
        )
    )

    Box(
        modifier = modifier
            .background(gradientBrush, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 48.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF7F7F7)
@Composable
fun BookingBottomBarPreview() {
    // Wrap with MaterialTheme for proper styling
    MaterialTheme {
        BookingBottomBar()
    }
}