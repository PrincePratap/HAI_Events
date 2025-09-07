package com.cody.haievents.android.screens.auth.forgetpassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.componets.AuthTopBar
import com.cody.haievents.android.common.componets.CommonButton
import com.cody.haievents.android.screens.auth.login.DarkGreyText
import com.cody.haievents.android.screens.auth.login.Gold
import com.cody.haievents.android.screens.auth.login.LoginUiState





@Composable
fun ForgetPasswordScreen(
    uiState: LoginUiState  = LoginUiState(),
    onIdentifierChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }


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
                subtitle = "Enter your registered email or phone number, and we’ll help you reset it"
            )






            // Phone or Email Field
            OutlinedTextField(
                value = uiState.identifiers,
                onValueChange = onIdentifierChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Email or Phone", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Gold,
                    unfocusedIndicatorColor = Gold.copy(alpha = 0.5f),
                    cursorColor = Gold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))








            // Log In Button
            CommonButton(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                text = (if (uiState.isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                } else {
                    "Send OTP"
                }).toString()
            )

            Spacer(modifier = Modifier.weight(1f))

            val annotatedText = buildAnnotatedString {
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
            uiState = LoginUiState(
                identifiers = "user@example.com",
                password = "Secret@123",
                isLoading = false
            ),
            onIdentifierChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onRegisterClick = {},
            onForgotPasswordClick = {}
        )
    }
}