package com.cody.haievents.android.common.componets

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.unit.dp


@Composable
 fun Zoomable(
    modifier: Modifier = Modifier,
    minScale: Float = 0.8f,
    maxScale: Float = 4f,
    content: @Composable BoxScope.() -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val transformState = rememberTransformableState { zoomChange, panChange, _ ->
        val newScale = (scale * zoomChange).coerceIn(minScale, maxScale)
        // When scaling around a pinch focal point, transformable already adjusts panChange.
        scale = newScale
        offset += panChange
    }

    Box(
        modifier = modifier
            // Double‑tap to toggle zoom
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { tap ->
                        val target = if (scale < 1.75f) 2f else 1f
                        val clamped = target.coerceIn(minScale, maxScale)
                        // Nudge content toward the tap point when zooming in
                        val deltaScale = clamped / scale
                        offset = (offset - (tap - Offset.Zero)) * deltaScale + (tap - Offset.Zero)
                        scale = clamped
                    }
                )
            }
            .transformable(transformState)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offset.x
                translationY = offset.y
            }
    ) {
        content()
        // Optional: Zoom controls
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                SmallFloatingActionButton(
                    onClick = { scale = (scale * 1.15f).coerceIn(minScale, maxScale) }
                ) { Text("+") }
                SmallFloatingActionButton(
                    onClick = { scale = (scale / 1.15f).coerceIn(minScale, maxScale) }
                ) { Text("–") }
            }
        }
    }
}