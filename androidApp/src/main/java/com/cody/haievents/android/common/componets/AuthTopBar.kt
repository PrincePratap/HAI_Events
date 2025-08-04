package com.cody.haievents.android.common.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.R


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Surface

import androidx.compose.ui.Alignment

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview


/**
 * A reusable top bar for authentication screens.
 *
 * @param modifier The modifier to be applied to the component.
 * @param title The main title text to display.
 * @param subtitle The subtitle text to display.
 */
@Composable
fun AuthTopBar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp) // Adds space between all items
    ) {
        // 1. Logo
        Image(
            painter = painterResource(id = R.drawable.img_small_logo),
            contentDescription = stringResource(R.string.content_desc_app_logo),
            modifier = Modifier.size(100.dp)
        )

        // Add larger space after the logo
        // Spacer(modifier = Modifier.height(16.dp)) // Arrangement.spacedBy handles this now

        // 2. Header Text
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            // Use colors from the theme for better dark/light mode support
            color = MaterialTheme.colorScheme.onSurface
        )

        // 3. Subtitle Text
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            // onSurfaceVariant is great for secondary text
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}


// --- PREVIEWS ---

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AuthTopBarPreview() {
    // It's a best practice to wrap previews in your app's theme
        // Surface provides a background color from the theme
        Surface {
            AuthTopBar(
                modifier = Modifier.padding(16.dp),
                title = stringResource(id = R.string.create_your_account),
                subtitle = stringResource(id = R.string.auth_top_bar_subtitle)
            )
        }

}

