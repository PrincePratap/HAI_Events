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
    totalTimeInSeconds: Long = (1 * 60) + 58L, // 01:58
    isRunning: Boolean,
    restartKey: Int,                 // change this value to reset the timer
    onFinished: () -> Unit
) {
    // Reset remaining time whenever restartKey changes.
    var remainingSeconds by remember(restartKey) { mutableStateOf(totalTimeInSeconds) }

    LaunchedEffect(isRunning, remainingSeconds) {
        if (!isRunning) return@LaunchedEffect
        while (remainingSeconds > 0 && isRunning) {
            delay(1000L)
            remainingSeconds--
        }
        if (remainingSeconds == 0L) {
            onFinished()
        }
    }

    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFF424242))) {
            append("Resend OTP in: ")
        }
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
//            ResendTimeFun()
        }
    }
}