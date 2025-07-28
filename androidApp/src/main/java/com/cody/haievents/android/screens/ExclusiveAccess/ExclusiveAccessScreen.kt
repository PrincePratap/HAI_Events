package com.cody.haievents.android.screens.ExclusiveAccess

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Data Models and Dummy Data ---

data class PricingPlan(
    val name: String,
    val price: String,
    val features: List<String>,
    val icon: ImageVector,
    val iconBackgroundColor: Color,
    val cardBrush: Brush,
    val buttonColor: Color,
    val textColor: Color = Color.Black.copy(alpha = 0.8f),
    val buttonTextColor: Color
)

object PlansProvider {
    val plans = listOf(
        PricingPlan(
            name = "Silver Plan",
            price = "₹199",
            features = listOf(
                "2 event listings per month",
                "Early access to features",
                "Email support",
                "Basic analytics"
            ),
            icon = Icons.Default.EmojiEvents,
            iconBackgroundColor = Color(0xFFB0BEC5),
            cardBrush = Brush.verticalGradient(
                colors = listOf(Color(0xFFF1F2F3), Color(0xFFD9DADC))
            ),
            buttonColor = Color(0xFF424242),
            buttonTextColor = Color.White
        ),
        PricingPlan(
            name = "Gold Plan",
            price = "₹499",
            features = listOf(
                "2 event listings per month",
                "Early access to features",
                "Email support",
                "Basic analytics"
            ),
            icon = Icons.Default.WorkspacePremium,
            iconBackgroundColor = Color(0xFFE4C560),
            cardBrush = Brush.verticalGradient(
                colors = listOf(Color(0xFFFAE8B7), Color(0xFFE6CE95))
            ),
            buttonColor = Color(0xFFC7A441),
            buttonTextColor = Color(0xFF4E342E)
        ),
        PricingPlan(
            name = "Pro Host",
            price = "₹899",
            features = listOf(
                "2 event listings per month",
                "Early access to features",
                "Email support",
                "Basic analytics"
            ),
            icon = Icons.Default.Bolt,
            iconBackgroundColor = Color(0xFF4FC3F7),
            cardBrush = Brush.verticalGradient(
                colors = listOf(Color(0xFFB3E5FC), Color(0xFF64B5F6))
            ),
            buttonColor = Color(0xFF0277BD),
            buttonTextColor = Color.White
        )
    )
}

// --- Main Screen Composable ---

@Composable
fun ExclusiveAccessScreen() {
    val screenBackgroundColor = Color(0xFF1C1C1E)

    Column(Modifier.fillMaxSize().background(screenBackgroundColor)) {
        IconButton(onClick = { /* Handle back navigation */ }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ScreenHeader()
            PlansProvider.plans.forEach { plan ->
                PricingPlanCard(plan = plan)
            }
        }
    }
}

// --- Reusable UI Components ---

@Composable
fun ScreenHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = CircleShape,
            color = Color(0xFFF9E79F).copy(alpha = 0.5f),
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                Icons.Default.WorkspacePremium,
                contentDescription = "Exclusive Access",
                tint = Color(0xFFD4AF37),
                modifier = Modifier.padding(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Unlock Exclusive Access",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            color = Color.Black.copy(alpha = 0.9f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Get featured badges, list more events, and boost your visibility to reach thousands of potential attendees",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            lineHeight = 22.sp
        )
    }
}

@Composable
fun PricingPlanCard(plan: PricingPlan) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .background(plan.cardBrush)
                .padding(24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = CircleShape, color = plan.iconBackgroundColor, modifier = Modifier.size(56.dp)) {
                    Icon(plan.icon, contentDescription = null, tint = Color.White, modifier = Modifier.padding(12.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(plan.name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = plan.textColor)
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)) {
                                append(plan.price)
                            }
                            withStyle(style = SpanStyle(fontSize = 14.sp, color = Color.Gray)) {
                                append("/month")
                            }
                        },
                        color = plan.textColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            plan.features.forEach { feature ->
                FeatureListItem(text = feature, textColor = plan.textColor)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* Upgrade action */ },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = plan.buttonColor,
                    contentColor = plan.buttonTextColor
                )
            ) {
                Text("Upgrade Now", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FeatureListItem(text: String, textColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = textColor.copy(alpha = 0.8f),
            modifier = Modifier
                .size(16.dp)
                .background(Color.Transparent, CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = textColor, fontSize = 14.sp)
    }
}

// --- Preview Function ---

@Preview(
    showBackground = true,
    name = "Exclusive Access Screen",
    widthDp = 390,
    heightDp = 844
)
@Composable
fun ExclusiveAccessScreenPreview() {
    MaterialTheme {
        ExclusiveAccessScreen()
    }
}