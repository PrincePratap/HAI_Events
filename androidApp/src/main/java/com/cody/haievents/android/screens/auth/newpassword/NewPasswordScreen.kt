package com.cody.haievents.android.screens.auth.newpassword

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.components.inputField.PasswordTextField
import com.cody.haievents.android.common.componets.AuthTopBar
import com.cody.haievents.android.common.componets.CommonButton
import com.cody.haievents.android.screens.auth.login.Gold
import com.cody.haievents.android.screens.auth.login.LoginUiState


@Composable
fun NewPasswordScreen(
    uiState: NewPasswordUiState = NewPasswordUiState(),
    onPasswordChange: (String) -> Unit = { },
    onConfirmPasswordChange: (String) -> Unit = {},
    onSetNewPasswordClick: () -> Unit = {},
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
                title = "Set New Password",
                subtitle = "Create a strong password to secure your account"
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = "Enter new password"
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(
                value = uiState.confirmPassword,
                onValueChange = onConfirmPasswordChange,
                placeholder = "Enter confirm password"
            )







            Spacer(modifier = Modifier.height(32.dp))

            // Log In Button
            CommonButton(
                onClick = onSetNewPasswordClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                text = (if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    "Set New Password"
                }).toString()
            )

            Spacer(modifier = Modifier.weight(1f))


            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(
    name = "NewPassword - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "id:pixel_5"
)
@Composable
private fun PreviewNewPasswordScreen_Light() {
    var ui by remember { mutableStateOf(LoginUiState(password = "", isLoading = false)) }
    var confirm by remember { mutableStateOf("") }

    NewPasswordScreen(
        onPasswordChange = { ui = ui.copy(password = it) },
        onConfirmPasswordChange = { confirm = it },
    )
}
