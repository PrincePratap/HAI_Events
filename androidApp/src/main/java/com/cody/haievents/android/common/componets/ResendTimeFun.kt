package com.cody.haievents.android.common.componets


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun ResendTimeFun(
    modifier: Modifier = Modifier,
    totalTimeInSeconds: Long = (4 * 60) + 58L // Corresponds to 04:58
) {
    // State to hold the remaining seconds, remembered across recompositions.
    var remainingSeconds by remember { mutableStateOf(totalTimeInSeconds) }

    // A side-effect that runs once when the composable enters the composition
    // and re-launches if the key (remainingSeconds) changes.
    // This is used to create the countdown logic.
    LaunchedEffect(key1 = remainingSeconds) {
        // The loop continues as long as there is time left.
        while (remainingSeconds > 0) {
            delay(1000L) // Wait for 1 second
            remainingSeconds-- // Decrement the timer
        }
    }

    // Format the remaining seconds into MM:SS format.
    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    // Use buildAnnotatedString to create text with multiple styles.
    val annotatedString = buildAnnotatedString {
        // Style for the "Code expire in: " part
        withStyle(style = SpanStyle(color = Color(0xFF424242))) {
            append("Code expire in: ")
        }
        // Style for the timer part
        withStyle(style = SpanStyle(color = Color(0xFFC79100))) {
            append(formattedTime)
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ResendTimeFunPreview() {
    // Using a Surface from Material3 with the theme's background color.
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Box is used to center the content.
        Box(contentAlignment = Alignment.Center) {
            ResendTimeFun()
        }
    }
}