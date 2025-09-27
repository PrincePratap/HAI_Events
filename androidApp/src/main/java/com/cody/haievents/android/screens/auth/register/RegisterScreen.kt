package com.cody.haievents.android.screens.auth.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.R
import com.cody.haievents.android.common.components.inputField.CustomTextField
import com.cody.haievents.android.common.components.inputField.PasswordTextField
import com.cody.haievents.android.common.componets.AuthTopBar
import com.cody.haievents.android.common.componets.LoginNavigationText
import com.cody.haievents.android.common.componets.TermsAndConditionsText
import com.cody.haievents.android.common.theming.GoldenYellow
import com.cody.haievents.android.common.theming.MyApplicationTheme
import com.ramcosta.composedestinations.annotation.Destination

private const val TAG = "RegisterScreen"

@Destination
@Composable
fun RegisterScreen(
    uiState: RegisterUiState = RegisterUiState(),
    onFirstNameChange: (String) -> Unit = {},
    onLastNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPhoneChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onConfirmPasswordChange: (String) -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    clickOnBackButton: () -> Unit = {}
) {
    var agreedToTerms by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)     // <-- enables scrolling
                .imePadding()                     // <-- avoid keyboard overlap
                .background(Color.White)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTopBar(
                title = stringResource(id = R.string.create_your_account),
                subtitle = stringResource(id = R.string.auth_top_bar_subtitle),
                showBackButton = true,
                onBackClick = clickOnBackButton
            )

            CustomTextField(
                value = uiState.firstName,
                onValueChange = onFirstNameChange,
                placeholder = stringResource(R.string.ps_full_first_name)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = uiState.lastName,
                onValueChange = onLastNameChange,
                placeholder = stringResource(R.string.ps_full_last_name)
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                placeholder = stringResource(R.string.ps_email_address),
                keyboardOptions = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = uiState.telephone,
                onValueChange = onPhoneChange,
                placeholder = stringResource(R.string.ps_phone_number),
                keyboardOptions = KeyboardType.Phone
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(R.string.ps_password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = uiState.confirmPassword,
                onValueChange = onConfirmPasswordChange,
                placeholder = stringResource(R.string.ps_confirm_password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = {
                        agreedToTerms = it
                        Log.d(TAG, "Terms agreement changed to: $it")
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = GoldenYellow,
                        checkmarkColor = Color.White
                    )
                )
                TermsAndConditionsText(highlightColor = GoldenYellow)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    Log.d(TAG, "Sign Up button clicked.")
                    onRegisterClick()
                },
                enabled = agreedToTerms,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GoldenYellow)
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            LoginNavigationText(onLoginClick = onLoginClick)

            Spacer(modifier = Modifier.height(16.dp)) // bottom padding so last item isn't stuck
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun SignUpScreenPreview() {
    MyApplicationTheme {
        RegisterScreen()
    }
}
