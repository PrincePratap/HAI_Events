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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Surface

import androidx.compose.ui.Alignment

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cody.haievents.android.common.theming.MyApplicationTheme


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
    withSpacer: Boolean = true,
    showBackButton: Boolean = false,         // <— toggle visibility (GONE when false)
    onBackClick: () -> Unit = {}            // <— click handler
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Back button aligned to LEFT; removed entirely when showBackButton = false (GONE)
        if (showBackButton) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.Start) // left-align inside the Column
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        if (withSpacer) {
            Spacer(modifier = Modifier.height(80.dp))
        }

        Image(
            painter = painterResource(id = R.drawable.img_small_logo),
            contentDescription = stringResource(R.string.content_desc_app_logo),
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}


// --- PREVIEWS ---

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun AuthTopBarPreview() {

    MyApplicationTheme {
        AuthTopBar(
            title = stringResource(id = R.string.create_your_account),
            subtitle = stringResource(id = R.string.auth_top_bar_subtitle)
        )
    }



}

