package com.cody.haievents.android.screens.auth.forgetpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.R
import com.cody.haievents.android.common.components.inputField.CustomTextField
import com.cody.haievents.android.common.componets.AuthTopBar
import com.cody.haievents.android.common.componets.CommonButton
import com.cody.haievents.android.screens.auth.login.DarkGreyText
import com.cody.haievents.android.screens.auth.login.Gold


@Composable
fun ForgetPasswordScreen(
    uiState: ForgetPasswordUiState = ForgetPasswordUiState(),
    onIdentifierChange: (String) -> Unit = {},
    onSendOTPClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {


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

            AuthTopBar(
                title = "Forgot Password",
                subtitle = "Enter your registered email, and we’ll help you reset it",
                showBackButton = true,
                onBackClick = onBackClick
            )




            CustomTextField(
                value = uiState.email,
                onValueChange = onIdentifierChange,
                placeholder = stringResource(R.string.ps_email_address)
            )





            Spacer(modifier = Modifier.height(16.dp))


            // Log In Button
            CommonButton(
                onClick = onSendOTPClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                text = (if (uiState.isLoading) {
                    "Send OTP..."
                } else {
                    "Send OTP"
                }).toString()
            )

            Spacer(modifier = Modifier.weight(1f))

            val annotatedText = buildAnnotatedString{
                append("Don't have an account? ")
                pushStringAnnotation(tag = "REGISTER", annotation = "register_link")
                withStyle(style = SpanStyle(color = Gold, fontWeight = FontWeight.Bold)) {
                    append("Register")
                }
                pop()
            }

            ClickableText(
                text = annotatedText,
                style = MaterialTheme.typography.bodyMedium.copy(color = DarkGreyText),
                onClick = { offset ->
                    annotatedText.getStringAnnotations("REGISTER", offset, offset)
                        .firstOrNull()?.let {
                            onRegisterClick()
                        }
                }
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(
    name = "",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun ForgetPasswordScreenPreview_Light() {
    MaterialTheme {
        ForgetPasswordScreen(
            uiState = ForgetPasswordUiState(
                email = "user@example.com",
                isLoading = false
            ),
            onIdentifierChange = {},
            onSendOTPClick = {},
            onRegisterClick = {},
        )
    }
}