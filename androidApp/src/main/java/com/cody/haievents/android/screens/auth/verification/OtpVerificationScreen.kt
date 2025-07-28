package com.cody.haievents.android.screens.auth.verification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// Reusing colors for consistency
val Gold = Color(0xFFD4A645)
val LightGold = Color(0xFFF5D06C)
val DarkGreyText = Color(0xFF333333)
val LightGreyText = Color(0xFF666666)

@Composable
fun OtpVerificationScreen() {
    var otpValue by remember { mutableStateOf("") }

    val goldGradient = Brush.horizontalGradient(
        colors = listOf(LightGold, Gold)
    )

    // Countdown timer state
    var timeLeft by remember { mutableStateOf(298) } // 04:58 in seconds
    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }
    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val timeString = String.format("%02d:%02d", minutes, seconds)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // Logo
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // In a real app, this would be an Image composable.
                Text(
                    text = "HAI",
                    fontWeight = FontWeight.Bold,
                    fontSize = 52.sp,
                    letterSpacing = 4.sp,
                    style = LocalTextStyle.current.copy(
                        brush = goldGradient
                    )
                )
                Text(
                    text = "EVENTS.COM",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp,
                    color = Gold
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Header Text
            Text(
                text = "Enter the OTP",
                style = MaterialTheme.typography.headlineMedium,
                color = DarkGreyText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter the 4-digit code sent to +91-8709879077",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGreyText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // OTP Input Field
            OtpTextField(
                otpText = otpValue,
                onOtpTextChange = { value, _ ->
                    if (value.length <= 4) {
                        otpValue = value
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Countdown Timer
            val timerText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = LightGreyText)) {
                    append("Code expire in: ")
                }
                withStyle(style = SpanStyle(color = Gold, fontWeight = FontWeight.SemiBold)) {
                    append(timeString)
                }
            }
            Text(text = timerText)


            Spacer(modifier = Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = { /* TODO: Handle OTP verification */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(brush = goldGradient, shape = RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
                enabled = otpValue.length == 4 // Button is enabled only when OTP is fully entered
            ) {
                Text(text = "Continue", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Resend OTP
            val resendText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = LightGreyText)) {
                    append("Haven't received it yet? ")
                }
                pushStringAnnotation(tag = "RESEND", annotation = "resend_otp")
                withStyle(style = SpanStyle(color = Gold, fontWeight = FontWeight.Bold)) {
                    append("Resend OTP")
                }
                pop()
            }

            ClickableText(
                text = resendText,
                onClick = { offset ->
                    resendText.getStringAnnotations(tag = "RESEND", start = offset, end = offset)
                        .firstOrNull()?.let {
                            // TODO: Handle OTP resend logic
                            timeLeft = 298 // Reset timer
                        }
                }
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it, it.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    val char = when {
                        index >= otpText.length -> ""
                        else -> otpText[index].toString()
                    }
                    val isFocused = otpText.length == index
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .padding(horizontal = 8.dp)
                            .border(
                                1.dp,
                                when {
                                    isFocused -> Gold
                                    else -> Gold.copy(alpha = 0.5f)
                                },
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            style = MaterialTheme.typography.headlineSmall,
                            color = DarkGreyText,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF2C2C2C, device = "id:pixel_4")
@Composable
fun OtpVerificationScreenPreview() {
    MaterialTheme {
        OtpVerificationScreen()
    }
}