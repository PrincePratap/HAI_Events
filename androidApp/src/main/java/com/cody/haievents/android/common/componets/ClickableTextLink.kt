package com.cody.haievents.android.common.componets

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.cody.haievents.android.R
import com.cody.haievents.android.common.theming.GoldenYellow


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

@Composable
 fun LoginNavigationText(onLoginClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.already_have_account))
        append(" ")
        pushStringAnnotation(tag = "LOGIN", annotation = "LOGIN")
        withStyle(
            style = SpanStyle(
                color = GoldenYellow,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(stringResource(R.string.login_now))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "LOGIN", start = offset, end = offset)
                .firstOrNull()?.let {
//                    Log.d(TAG, "Login navigation text clicked.")
                    onLoginClick()
                }
        },
        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
    )
}
