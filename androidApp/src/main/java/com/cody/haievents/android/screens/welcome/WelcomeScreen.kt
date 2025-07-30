package com.cody.haievents.android.screens.welcome




import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cody.haievents.android.R





// --- Colors and Styles ---
val darkBackgroundColor = Color(0xFF2E2E2E)
val goldColor = Color(0xFFD0B25E)


@Composable
fun WelcomeScreen(
    clickLogin: () -> Unit = {},
    clickRegister: () -> Unit = {}
) {


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {




            // White content area
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.White,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Logo Placeholder
                    Image(
                        painter = painterResource(id = R.drawable.img_small_logo),
                        contentDescription = "HAI Events Logo",
                        modifier = Modifier.height(55.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Headline
                    Text(
                        text = "Every Event, One App",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Sub-headline
                    Text(
                        text = "From comedy to concerts, explore and book all your favorite live events in one place.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Log In Button
                    Button(
                        onClick = clickLogin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = goldColor)
                    ) {
                        Text(text = "Log In", color = Color.White, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = clickRegister,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, goldColor)
                    ) {
                        Text(text = "Register", color = goldColor, fontSize = 16.sp)
                    }

                    // Pushes the content up slightly from the absolute center
                    Spacer(modifier = Modifier.weight(0.1f))
                }
            }
        }
    }
}

// --- Image Grid Row Composable ---




// --- Preview Composable ---
@Preview(showBackground = true,)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}