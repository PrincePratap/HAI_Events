package com.cody.haievents.android.screens.auth.otp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// We can define the colors used in the app for consistency.
val goldColor = Color(0xFFC9A43B)
val darkBackgroundColor = Color(0xFF1C1C1E)

@Composable
fun OTPScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackgroundColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {


            // 2. The main content area with a white background
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()) // Make content scrollable
            ) {
                // Back arrow
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                // Main content, centered horizontally
                Column(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))



                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "Enter the OTP",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Enter the 4-digit code sent to +91-8709879077",
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // OTP Input Boxes
                    OtpInputFields()

                    Spacer(modifier = Modifier.height(20.dp))

                    // Expiry timer text
                    ExpiryTimerText()

                    Spacer(modifier = Modifier.height(32.dp))

                    // Continue Button
                    Button(
                        onClick = { /* Handle continue action */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = goldColor)
                    ) {
                        Text(
                            text = "Continue",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Resend OTP link
                    ResendOtpText()

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}





@Composable
fun OtpInputFields() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(4) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White)
                    .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                // In a real app, this would be a BasicTextField to handle input
            }
        }
    }
}

@Composable
fun ExpiryTimerText() {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
            append("Code expire in: ")
        }
        withStyle(
            style = SpanStyle(
                color = goldColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        ) {
            append("04:58")
        }
    }
    Text(annotatedString)
}

@Composable
fun ResendOtpText() {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
            append("Haven't received it yet? ")
        }
        pushStringAnnotation(tag = "resend", annotation = "resend_otp")
        withStyle(
            style = SpanStyle(
                color = goldColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        ) {
            append("Resend OTP")
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "resend", start = offset, end = offset)
                .firstOrNull()?.let {
                    // Handle "Resend OTP" click
                    println("Resend OTP clicked!")
                }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun OTPScreenPreview() {


        OTPScreen()

}