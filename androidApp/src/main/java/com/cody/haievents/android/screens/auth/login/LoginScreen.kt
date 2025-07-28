package com.cody.haievents.android.screens.auth.login

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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// You can create a theme file for your app, but for this self-contained example,
// we'll define the colors here.
val Gold = Color(0xFFD4A645)
val LightGold = Color(0xFFF5D06C)
val DarkGreyText = Color(0xFF333333)
val LightGreyText = Color(0xFF666666)

@Composable
fun LoginScreen() {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val goldGradient = Brush.horizontalGradient(
        colors = listOf(LightGold, Gold)
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
            Spacer(modifier = Modifier.height(80.dp))

            // Logo
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // As the logo graphic is complex, we are using text to represent it.
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

            Spacer(modifier = Modifier.height(48.dp))

            // Welcome Text
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

            // Phone Number Field
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Phone Number", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
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
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Password", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    // The icon in the image is slightly different, but this is a standard replacement.
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description, tint = Color.Gray)
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

            // Forgot Password
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
                onClick = { /* TODO: Handle login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(brush = goldGradient, shape = RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
            ) {
                Text(
                    text = "Log In",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Pushes the registration text to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // Register Link
            val annotatedText = buildAnnotatedString {
                append("Don't have an account ? ")
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
                    annotatedText.getStringAnnotations(tag = "REGISTER", start = offset, end = offset)
                        .firstOrNull()?.let {
                            // TODO: Handle registration navigation
                        }
                }
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2C2C2C, device = "id:pixel_4")
@Composable
fun LoginScreenPreview() {
    // Wrap with your app's theme if you have one.
    // For now, MaterialTheme is used directly.
    MaterialTheme {
        LoginScreen()
    }
}