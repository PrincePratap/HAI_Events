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
import com.cody.haievents.android.screens.auth.otp.OtpUiState
import com.cody.haievents.android.screens.auth.otp.ResendOtpText


@Composable
fun ForgetPasswordOTPScreen(
    uiState: ForgetPasswordOTPUiState = ForgetPasswordOTPUiState(),
    onOTpValueChanged: (String) -> Unit = {},
    onContinueClicked: () -> Unit = {},
    onResendClicked: () -> Unit = {},
) {

    val isResendEnabled = false

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
            IconButton(onClick = { /* Handle back navigation */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            AuthTopBar(
                title = "Verify OTP",
                subtitle = "Enter the 6-digit code sent to +91-8709879077",
                withSpacer = false
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

                ResendTimeFun()

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
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}