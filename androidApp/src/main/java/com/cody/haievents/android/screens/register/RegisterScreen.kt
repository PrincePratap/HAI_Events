package com.cody.haievents.android.screens.register

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination




import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.R
import com.cody.haievents.android.common.components.inputField.CustomTextField
import com.cody.haievents.android.common.components.inputField.PasswordTextField
import com.cody.haievents.android.common.theming.GoldenYellow

@Composable
fun RegisterScreen() {
    val goldColor = Color(0xFFC79100)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            StatusBar()

            Column(
                modifier = Modifier
                    .weight(1f) // Ensures this column takes up available space, pushing the login redirect to the bottom
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // 2. Logo
                Image(
                    painter = painterResource(id = R.drawable.img_small_logo), // Replace with your image name
                    contentDescription = "Splash Logo",
                    modifier = Modifier.size(100.dp) // Adjust size as needed
                )
                Spacer(modifier = Modifier.height(24.dp))

                // 3. Header Text
                Text(
                    text = "Create Your Account",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Join the community and start booking your\nfavorite events today!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                // 4. Input Fields
                SignUpForm()
            }

            // 7. Login Redirect at the bottom of the screen
            LoginRedirect(
                highlightColor = goldColor,
                modifier = Modifier
                    .padding(bottom = 32.dp, top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}



@Composable
fun SignUpForm() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(true) }

    CustomTextField(
        value = fullName,
        onValueChange = { fullName = it },
    )
    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = email,
        onValueChange = { email = it },
        keyboardType = KeyboardType.Email
    )
    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = phone,
        onValueChange = { phone = it },
        keyboardType = KeyboardType.Phone
    )
    Spacer(modifier = Modifier.height(16.dp))

    PasswordTextField(
        value = password,
        onValueChange = { password = it },
    )
    Spacer(modifier = Modifier.height(16.dp))

    PasswordTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
    )
    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = agreedToTerms,
            onCheckedChange = { agreedToTerms = it },
            colors = CheckboxDefaults.colors(
                checkedColor = GoldenYellow,
                checkmarkColor = Color.White
            )
        )
        TermsAndConditionsText(GoldenYellow)
    }

    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = { /* Handle sign up */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = GoldenYellow)
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}



@Composable
fun StatusBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "9:41", color = Color.White, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(Icons.Filled.SignalCellularAlt, contentDescription = "Signal strength", tint = Color.White, modifier = Modifier.size(16.dp))
            Icon(Icons.Filled.Wifi, contentDescription = "WiFi status", tint = Color.White, modifier = Modifier.size(16.dp))
            Icon(Icons.Filled.BatteryFull, contentDescription = "Battery status", tint = Color.White, modifier = Modifier.size(20.dp))
        }
    }
}



@Composable
fun TermsAndConditionsText(highlightColor: Color) {
    val annotatedString = buildAnnotatedString {
        append("Agree with ")
        pushStringAnnotation(tag = "TERMS", annotation = "https://example.com/terms")
        withStyle(style = SpanStyle(color = highlightColor, fontWeight = FontWeight.Bold)) {
            append("Terms & Conditions")
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "TERMS", start = offset, end = offset)
                .firstOrNull()?.let {
                    Log.d("SignUpScreen", "Clicked on: ${it.item}")
                }
        }
    )
}

@Composable
fun LoginRedirect(highlightColor: Color, modifier: Modifier = Modifier) {
    val annotatedString = buildAnnotatedString {
        append("Already have an account? ")
        pushStringAnnotation(tag = "LOGIN", annotation = "login_screen")
        // Note: The original image had a typo "Loginf". Corrected to "Login".
        withStyle(style = SpanStyle(color = highlightColor, fontWeight = FontWeight.Bold)) {
            append("Login")
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black, textAlign = TextAlign.Center),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "LOGIN", start = offset, end = offset)
                .firstOrNull()?.let {
                    Log.d("SignUpScreen", "Navigate to ${it.item}")
                }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}