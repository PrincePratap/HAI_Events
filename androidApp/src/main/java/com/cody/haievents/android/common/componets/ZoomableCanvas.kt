package com.cody.haievents.android.common.componets

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Composable
 fun ZoomableCanvas(
    modifier: Modifier = Modifier,
    minScale: Float = 0.5f,
    maxScale: Float = 3f,
    // expose scale if you want to show % somewhere
    content: @Composable (scale: Float) -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var translation by remember { mutableStateOf(Offset.Zero) }

    val transformState = rememberTransformableState { zoomChange, panChange, _ ->
        // pinch-to-zoom
        val newScale = (scale * zoomChange).coerceIn(minScale, maxScale)
        // adjust pan responsiveness as scale changes
        val pan = panChange * (if (newScale > 1f) 1f else 0f)
        scale = newScale
        translation += pan
    }

    // double-tap to toggle zoom (center-ish)
    val doubleTapModifier = Modifier.pointerInput(Unit) {
        detectTapGestures(
            onDoubleTap = {
                if (scale <= 1f + 1e-3f) {
                    scale = 2f.coerceIn(minScale, maxScale)
                } else {
                    scale = 1f
                    translation = Offset.Zero
                }
            }
        )
    }

    Box(
        modifier = modifier
            .then(doubleTapModifier)
            .transformable(transformState) // pinch + pan gestures
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = translation.x,
                    translationY = translation.y
                )
        ) {
            content(scale)
        }

        // Optional: simple zoom controls (overlay)
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(tonalElevation = 2.dp, shape = MaterialTheme.shapes.small) {
                Row {
                    IconButton(onClick = {
                        scale = max(1f, (scale - 0.25f)).coerceAtLeast(minScale)
                        if (scale == 1f) translation = Offset.Zero
                    }) { Icon(androidx.compose.material.icons.Icons.Default.Remove, contentDescription = "Zoom out") }

                    IconButton(onClick = {
                        scale = min(maxScale, scale + 0.25f)
                    }) { Icon(androidx.compose.material.icons.Icons.Default.Add, contentDescription = "Zoom in") }
                }
            }
        }
    }
}