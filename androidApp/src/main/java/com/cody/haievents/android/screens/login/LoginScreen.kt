package com.cody.haievents.android.screens.login


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Main Composable for the Login Screen
@Composable
fun LoginScreen(
    clickOnLogin: () -> Unit,
    navigateForgetPassword: () -> Unit,
    navigateSignUp: () -> Unit
) {
    // Define colors from the image
    val brightBlue = Color(0xFF4D8AF0)
    val darkHeader = Color(0xFF1C203A)
    val primaryOrange = Color(0xFFF77A34)
    val inputFieldGray = Color(0xFFF2F3F7)
    val textGray = Color(0xFF8A8A8D)

    // The bright blue border is simulated with padding on a Box
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(darkHeader) // Background for the top part
        ) {
            // Top header section
            HeaderSection()

            // Bottom form section
            FormSection(primaryOrange, inputFieldGray, textGray, clickOnLogin)
        }
    }
}

// Composable for the dark header section
@Composable
fun ColumnScope.HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f), // Takes up 1/4 of the vertical space
        contentAlignment = Alignment.Center
    ) {
        // The decorative pattern in the background is complex.
        // For a real app, you might use a background Image or custom Canvas drawing.
        // Here, we focus on the main layout and components.
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Log In",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please sign in to your existing account",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Composable for the white form section
@Composable
fun ColumnScope.FormSection(
    primaryOrange: Color,
    inputFieldGray: Color,
    textGray: Color,
    clickOnLogin: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(3f), // Takes up 3/4 of the vertical space
        color = Color.White,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Email Field
            FormField(label = "EMAIL", placeholder = "example@gmail.com")

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            PasswordFormField(label = "PASSWORD")

            Spacer(modifier = Modifier.height(16.dp))

            // Remember me and Forgot Password row
            RememberMeRow(primaryOrange)

            Spacer(modifier = Modifier.height(24.dp))

            // LOG IN Button
            Button(
                onClick = {
                    clickOnLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "LOG IN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sign up prompt
            SignUpPrompt(primaryOrange)

            Spacer(modifier = Modifier.weight(1f)) // Pushes social logins to the bottom

            // "Or" text
            Text("Or", color = textGray, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}


// Reusable composable for a standard text input field
@Composable
fun FormField(label: String, placeholder: String) {
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(placeholder, color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF2F3F7),
                unfocusedContainerColor = Color(0xFFF2F3F7),
                disabledContainerColor = Color(0xFFF2F3F7),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

// Reusable composable for the password field
@Composable
fun PasswordFormField(label: String) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(
                mask = '●'
            ),
            trailingIcon = {
                val image =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description, tint = Color.Gray)
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF2F3F7),
                unfocusedContainerColor = Color(0xFFF2F3F7),
                disabledContainerColor = Color(0xFFF2F3F7),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

// Composable for the "Remember me" and "Forgot Password" row
@Composable
fun RememberMeRow(primaryOrange: Color) {
    var checkedState by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = { checkedState = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = primaryOrange,
                    uncheckedColor = Color.Gray
                )
            )
            Text("Remember me", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
        TextButton(onClick = { /* TODO: Handle forgot password */ }) {
            Text("Forgot Password", color = primaryOrange, fontWeight = FontWeight.Bold)
        }
    }
}

// Composable for the "Don't have an account? SIGN UP" text
@Composable
fun SignUpPrompt(primaryOrange: Color) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("Don't have an account? ")
        }
        pushStringAnnotation(tag = "SIGNUP", annotation = "signup")
        withStyle(style = SpanStyle(color = primaryOrange, fontWeight = FontWeight.Bold)) {
            append("SIGN UP")
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "SIGNUP", start = offset, end = offset)
                .firstOrNull()?.let {
                    Log.d("SignUpPrompt", "Clicked on SIGN UP")
                    // TODO: Navigate to Sign Up screen
                }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun LoginScreenPreview() {
    LoginScreen(clickOnLogin = {}, navigateSignUp = {}, navigateForgetPassword = {})
}
