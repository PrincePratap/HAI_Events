package com.cody.haievents.android.screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.unit.sp

val Gold = Color(0xFFD4A645)
val LightGold = Color(0xFFF5D06C)
val DarkGreyText = Color(0xFF333333)
val LightGreyText = Color(0xFF666666)

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onIdentifierChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val goldGradient = Brush.horizontalGradient(colors = listOf(LightGold, Gold))

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

            // Logo Text
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "HAI",
                    fontWeight = FontWeight.Bold,
                    fontSize = 52.sp,
                    letterSpacing = 4.sp,
                    color = Gold
                )
                Text(
                    text = "EVENTS.COM",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp,
                    color = Gold
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Welcome to HAI Events",
                style = MaterialTheme.typography.headlineSmall,
                color = DarkGreyText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Log in to book your next unforgettable experience",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGreyText
            )

            Spacer(modifier = Modifier.height(32.dp))

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

            // Password Field
            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Password", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = description, tint = Color.Gray)
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Gold,
                    unfocusedIndicatorColor = Gold.copy(alpha = 0.5f),
                    cursorColor = Gold
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Forgot Password?",
                color = Gold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Log In Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Gold)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                } else {
                    Text(
                        text = "Log In",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

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
                            // TODO: Navigate to register screen
                        }
                }
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun LoginScreenPreview() {
    val dummyState = LoginUiState(
        identifiers = "test@example.com",
        password = "password123",
        isLoading = false,
        errorMessage = null,
        succeed = false
    )

    LoginScreen(
        uiState = dummyState,
        onIdentifierChange = {},
        onPasswordChange = {},
        onLoginClick = {}
    )
}
