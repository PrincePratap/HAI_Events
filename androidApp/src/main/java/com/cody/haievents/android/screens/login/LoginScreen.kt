package com.cody.haievents.android.screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.common.theming.MyRed

@Composable
fun LoginScreen(
    clickOnLogin: () -> Unit = {},
    navigateForgetPassword: () -> Unit = {},
    navigateSignUp: () -> Unit = {}
) {
    val inputFieldGray = Color(0xFFF2F3F7)
    val textGray = Color(0xFF8A8A8D)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MyRed)
        ) {
            HeaderSection()

            FormSection(MyRed, inputFieldGray, textGray, clickOnLogin)
        }
    }
}

@Composable
fun ColumnScope.HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
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
                text = "Enter your mobile number to continue",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

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
            .weight(3f),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MobileNumberField(label = "MOBILE NUMBER", placeholder = "Enter mobile number")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { clickOnLogin() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MyRed),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "CONTINUE",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            SignUpPrompt(primaryOrange)

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun MobileNumberField(label: String, placeholder: String) {
    var mobile by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = mobile,
            onValueChange = {
                if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                    mobile = it
                }
            },
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
            singleLine = true,
            prefix = {
                Text("+91 ", color = MyRed, fontWeight = FontWeight.SemiBold)
            }
        )
    }
}

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
                }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
