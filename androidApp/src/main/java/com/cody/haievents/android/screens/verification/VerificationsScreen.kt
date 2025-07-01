package com.cody.haievents.android.screens.verification


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// Main Composable for the Verification Screen
@Composable
fun VerificationScreen( clickOnVerify: () -> Unit) {
    // Define colors from the image
    val darkHeader = Color(0xFF1C203A)
    val primaryOrange = Color(0xFFF77A34)
    val lightGrayBackground = Color(0xFFF2F3F7)
    val otpBoxColor = Color(0xFFF2F3F7)
    val textGray = Color(0xFF8A8A8D)

    var otpCode by remember { mutableStateOf("2015") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = lightGrayBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerificationHeader(darkHeader)
            VerificationBody(
                primaryOrange = primaryOrange,
                otpBoxColor = otpBoxColor,
                textGray = textGray,
                otpCode = otpCode,
                clickOnVerify
            )
            Spacer(Modifier.weight(1f))
//            NumericKeypad(
//                onKeyPress = { key ->
//                    if (otpCode.length < 4) {
//                        otpCode += key
//                    }
//                },
//                onBackspace = {
//                    if (otpCode.isNotEmpty()) {
//                        otpCode = otpCode.dropLast(1)
//                    }
//                }
//            )
            // For the small gray line at the very bottom (common on iOS devices)
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .width(134.dp)
                    .height(5.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(100))
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// Composable for the dark header section
@Composable
fun VerificationHeader(backgroundColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        IconButton(
            onClick = { /* TODO: Handle back navigation */ },
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .clip(CircleShape)
                .background(Color.White)
                .size(40.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = backgroundColor
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Verification",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We have sent a code to your email",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "example@gmail.com",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Composable for the white body section containing OTP inputs and Verify button
@Composable
fun VerificationBody(
    primaryOrange: Color,
    otpBoxColor: Color,
    textGray: Color,
    otpCode: String,
    clickOnVerify: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // "CODE" and "Resend" row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CODE",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                ResendTimer(textGray = textGray)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // OTP boxes
            OtpInputRow(otpCode = otpCode, otpBoxColor = otpBoxColor)

            Spacer(modifier = Modifier.height(24.dp))

            // "VERIFY" Button
            Button(
                onClick = {clickOnVerify()  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "VERIFY",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ResendTimer(textGray: Color) {
    var countdown by remember { mutableStateOf(50) }
    var isTimerRunning by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isTimerRunning) {
        if (isTimerRunning) {
            while (countdown > 0) {
                delay(1000L)
                countdown--
            }
            isTimerRunning = false
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TextButton(
            onClick = {
                if (!isTimerRunning) {
                    countdown = 50 // Reset timer
                    isTimerRunning = true
                }
            },
            enabled = !isTimerRunning
        ) {
            Text(
                "Resend",
                color = if (isTimerRunning) textGray else Color.Black,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        }
        if (isTimerRunning) {
            Text(
                " in.${countdown}sec",
                color = textGray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun OtpInputRow(otpCode: String, otpBoxColor: Color) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        (0..3).forEach { index ->
            val digit = otpCode.getOrNull(index)?.toString() ?: ""
            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 70.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(otpBoxColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = digit,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

// Composable for the custom numeric keypad
@Composable
//fun NumericKeypad(onKeyPress: (String) -> Unit, onBackspace: () -> Unit) {
//    val keys = listOf(
//        listOf("1" to "", "2" to "ABC", "3" to "DEF"),
//        listOf("4" to "GHI", "5" to "JKL", "6" to "MNO"),
//        listOf("7" to "PQRS", "8" to "TUV", "9" to "WXYZ")
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 24.dp, vertical = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        keys.forEach { row ->
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                row.forEach { (number, letters) ->
//                    KeypadButton(
//                        number = number,
//                        letters = letters,
//                        modifier = Modifier.weight(1f),
//                        onClick = { onKeyPress(number) }
//                    )
//                }
//            }
//        }
//        // Bottom row with 0 and backspace
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            Spacer(modifier = Modifier.weight(1f)) // Empty space to center the 0 key
//            KeypadButton(
//                number = "0",
//                letters = "",
//                modifier = Modifier.weight(1f),
//                onClick = { onKeyPress("0") }
//            )
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .height(60.dp)
//                    .clickable { onBackspace() },
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    Icons.AutoMirrored.Filled.Backspace,
//                    contentDescription = "Backspace",
//                    tint = Color.Gray,
//                    modifier = Modifier.size(28.dp)
//                )
//            }
//        }
//    }
//}



@Preview(showBackground = true)
fun VerificationScreenPreview() {
    VerificationScreen(clickOnVerify = {})
}