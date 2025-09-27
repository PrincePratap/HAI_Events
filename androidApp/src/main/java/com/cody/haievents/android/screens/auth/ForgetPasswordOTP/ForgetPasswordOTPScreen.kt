package com.cody.haievents.android.screens.auth.ForgetPasswordOTP

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.componets.AuthTopBar
import com.cody.haievents.android.common.componets.ResendTimeFun
import com.cody.haievents.android.common.theming.darkBackgroundColor
import com.cody.haievents.android.common.theming.goldColor
import com.cody.haievents.android.screens.auth.otp.OtpInputFields
import com.cody.haievents.android.screens.auth.otp.ResendOtpText
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*



@Composable
fun ForgetPasswordOTPScreen(
    uiState: ForgetPasswordOTPUiState = ForgetPasswordOTPUiState(),
    onOTpValueChanged: (String) -> Unit = {},
    onContinueClicked: () -> Unit = {},
    onResendClicked: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    // --- Timer + Resend control state ---
    var isTimerRunning by remember { mutableStateOf(true) }     // start immediately
    var restartKey by remember { mutableIntStateOf(0) }         // bump to reset timer
    val isResendEnabled = !isTimerRunning                       // enabled only after timer ends

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

                // Timer (shows while running; you can choose to hide it when finished)
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

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onContinueClicked,
                    enabled = uiState.otp.length == 6,
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




@Preview(
    name = "ForgetPasswordOTP – Light / Empty",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun Preview_ForgetPasswordOTPScreen_Empty() {
    ForgetPasswordOTPScreen(
        uiState = ForgetPasswordOTPUiState(
            otp = "" // Button disabled
        ),
        onOTpValueChanged = {},
        onContinueClicked = {},
        onResendClicked = {}
    )
}

@Preview(
    name = "ForgetPasswordOTP – Light / Filled",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun Preview_ForgetPasswordOTPScreen_Filled() {
    ForgetPasswordOTPScreen(
        uiState = ForgetPasswordOTPUiState(
            otp = "123456" // Button enabled
        ),
        onOTpValueChanged = {},
        onContinueClicked = {},
        onResendClicked = {}
    )
}

@Preview(
    name = "ForgetPasswordOTP – Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
private fun Preview_ForgetPasswordOTPScreen_Dark() {
    ForgetPasswordOTPScreen(
        uiState = ForgetPasswordOTPUiState(
            otp = "12" // Partial OTP
        ),
        onOTpValueChanged = {},
        onContinueClicked = {},
        onResendClicked = {}
    )
}
