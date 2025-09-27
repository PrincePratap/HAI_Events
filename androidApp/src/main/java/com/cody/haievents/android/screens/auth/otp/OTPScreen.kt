package com.cody.haievents.android.screens.auth.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cody.haievents.android.R
import com.cody.haievents.android.common.componets.AuthTopBar
import com.cody.haievents.android.common.componets.ResendTimeFun
import com.cody.haievents.android.common.theming.darkBackgroundColor
import com.cody.haievents.android.common.theming.goldColor

// We can define the colors used in the app for consistency.


@Composable
fun OTPScreen(
    uiState: OtpUiState = OtpUiState(),
    onOTpValueChanged: (String) -> Unit = {},
    onContinueClicked: () -> Unit = {},
    onResendClicked: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {


    // --- Timer + Resend control state ---
    var isTimerRunning by remember { mutableStateOf(true) }     // start immediately
    var restartKey by remember { mutableIntStateOf(0) }         // bump to reset timer
    val isResendEnabled = !isTimerRunning

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {


            AuthTopBar(
                title = "Verify OTP",
                subtitle = "Enter the 6-digit code sent to your email",
                withSpacer = false,
                showBackButton = true,
                onBackClick = onBackClick
            )


            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))




                OtpInputFields(
                    otpValue = uiState.otp,
                    onOtpValueChanged = onOTpValueChanged
                )
                Spacer(modifier = Modifier.height(10.dp))

                if (isTimerRunning) {
                    ResendTimeFun(
                        isRunning = isTimerRunning,
                        restartKey = restartKey,
                        onFinished = {
                            // Auto-enable resend when timer completes
                            isTimerRunning = false
                        }
                    )
                } else {
                    // Optional: spacer or "You can resend the code now" text
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Expiry timer text
//                ExpiryTimerText(timer = timerString)

                Spacer(modifier = Modifier.height(32.dp))

                // Continue Button
                Button(
                    onClick = onContinueClicked,
                    enabled = uiState.otp.length == 6, // Enable only when OTP is fully entered
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
                ResendOtpText(
                    isEnabled = isResendEnabled,
                    onClick = {
                        if (!isResendEnabled) return@ResendOtpText
                        // 1) trigger your resend action
                        onResendClicked()
                        // 2) restart timer and disable the link
                        isTimerRunning = true
                        restartKey++   // causes ResendTimeFun to reset
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun OtpInputFields(
    otpValue: String,
    onOtpValueChanged: (String) -> Unit,
    otpCount: Int = 6
) {
    // This LaunchedEffect will run once when the composable is first displayed
    // It requests focus on the first text field, which opens the keyboard.
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        value = otpValue,
        onValueChange = {
            onOtpValueChanged(it)
        },
        modifier = Modifier.focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(otpCount) { index ->
                    val char = otpValue.getOrNull(index)?.toString() ?: ""
                    val isFocused = otpValue.length == index

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = if (isFocused) goldColor else Color.LightGray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    )
}



@Composable
fun ResendOtpText(isEnabled: Boolean, onClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
            append("Haven't received it yet? ")
        }
        if (isEnabled) {
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
        } else {
            withStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            ) {
                append("Resend OTP")
            }
        }
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            if (isEnabled) {
                annotatedString.getStringAnnotations(tag = "resend", start = offset, end = offset)
                    .firstOrNull()?.let {
                        onClick()
                    }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OTPScreenPreview() {
    OTPScreen()
}