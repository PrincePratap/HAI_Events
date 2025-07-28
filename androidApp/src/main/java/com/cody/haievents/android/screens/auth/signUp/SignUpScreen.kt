package com.cody.haievents.android.screens.auth.signUp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Reusing colors from the previous screen for consistency
val Gold = Color(0xFFD4A645)
val LightGold = Color(0xFFF5D06C)
val DarkGreyText = Color(0xFF333333)
val LightGreyText = Color(0xFF666666)

@Composable
fun SignUpScreen() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(true) } // Checked in the image

    val goldGradient = Brush.horizontalGradient(
        colors = listOf(LightGold, Gold)
    )
    val textFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedIndicatorColor = Gold,
        unfocusedIndicatorColor = Gold.copy(alpha = 0.5f),
        cursorColor = Gold
    )

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
            Spacer(modifier = Modifier.height(60.dp))

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

            Spacer(modifier = Modifier.height(40.dp))

            // Header Text
            Text(
                text = "Create Your Account",
                style = MaterialTheme.typography.headlineSmall,
                color = DarkGreyText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Join the community and start booking your\nfavorite events today!",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGreyText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Input Fields
            OutlinedTextField(value = fullName, onValueChange = { fullName = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Full Name", color = Color.Gray) }, shape = RoundedCornerShape(12.dp), singleLine = true, colors = textFieldColors)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Email Address", color = Color.Gray) }, shape = RoundedCornerShape(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), singleLine = true, colors = textFieldColors)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = phone, onValueChange = { phone = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Phone Number", color = Color.Gray) }, shape = RoundedCornerShape(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), singleLine = true, colors = textFieldColors)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Password", color = Color.Gray) }, shape = RoundedCornerShape(12.dp), singleLine = true, visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), trailingIcon = { IconButton(onClick = { passwordVisible = !passwordVisible }) { Icon(if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, "Toggle password visibility", tint = Color.Gray) } }, colors = textFieldColors)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Confirm Password", color = Color.Gray) }, shape = RoundedCornerShape(12.dp), singleLine = true, visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), trailingIcon = { IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) { Icon(if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, "Toggle password visibility", tint = Color.Gray) } }, colors = textFieldColors)

            Spacer(modifier = Modifier.height(16.dp))

            // Terms and Conditions
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Gold,
                        uncheckedColor = Gold,
                        checkmarkColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                val termsText = buildAnnotatedString {
                    append("Agree with ")
                    pushStringAnnotation(tag = "TERMS", annotation = "terms_link")
                    withStyle(style = SpanStyle(color = Gold, textDecoration = TextDecoration.Underline)) {
                        append("Terms & Conditions")
                    }
                    pop()
                }
                ClickableText(text = termsText, style = MaterialTheme.typography.bodyMedium.copy(color = DarkGreyText), onClick = { offset ->
                    termsText.getStringAnnotations(tag = "TERMS", start = offset, end = offset).firstOrNull()?.let {
                        // TODO: Navigate to Terms & Conditions screen
                    }
                })
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sign Up Button
            Button(
                onClick = { /* TODO: Handle sign up */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(brush = goldGradient, shape = RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
            ) {
                Text(text = "Sign Up", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Login Link
            val loginText = buildAnnotatedString {
                append("Already have an account? ")
                pushStringAnnotation(tag = "LOGIN", annotation = "login_link")
                withStyle(style = SpanStyle(color = Gold, fontWeight = FontWeight.Bold)) {
                    append("Login here")
                }
                pop()
            }
            ClickableText(text = loginText, style = MaterialTheme.typography.bodyMedium.copy(color = DarkGreyText), onClick = { offset ->
                loginText.getStringAnnotations(tag = "LOGIN", start = offset, end = offset).firstOrNull()?.let {
                    // TODO: Handle navigation to Login screen
                }
            })

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2C2C2C, device = "id:pixel_4")
@Composable
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen()
    }
}