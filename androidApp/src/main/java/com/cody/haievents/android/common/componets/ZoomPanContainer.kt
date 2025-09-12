package com.cody.haievents.android.common.componets

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Safe zoom/pan wrapper that avoids gigantic layers on large screens.
 * - Caps scale with a dynamic limit from content size (~33M px budget).
 * - Uses graphicsLayer scaling (no re-measure explosions).
 * - Keeps panning bounded.
 */
@Composable
fun ZoomPanContainer(
    modifier: Modifier = Modifier,
    minScale: Float = 1f,
    maxScaleHardCap: Float = 3f,
    content: @Composable (scale: Float) -> Unit
) {
    BoxWithConstraints(modifier) {
        val density = LocalDensity.current
        val viewportWidthPx = with(density) { maxWidth.toPx() }
        val viewportHeightPx = with(density) { maxHeight.toPx() }

        var contentWidthPx by remember { mutableStateOf(0f) }
        var contentHeightPx by remember { mutableStateOf(0f) }

        // Android compositor rough layer limit (~33.5M px). Keep a little buffer.
        val pixelBudget = 33_000_000f

        val dynamicMaxScale = remember(viewportWidthPx, viewportHeightPx, contentWidthPx, contentHeightPx) {
            if (contentWidthPx <= 0f || contentHeightPx <= 0f) {
                maxScaleHardCap
            } else {
                val allowed = sqrt(pixelBudget / (contentWidthPx * contentHeightPx))
                min(max(1f, allowed), maxScaleHardCap)
            }
        }

        var scale by remember { mutableStateOf(1f) }
        var offset by remember { mutableStateOf(Offset.Zero) }

        fun boundedOffset(raw: Offset): Offset {
            if (contentWidthPx == 0f || contentHeightPx == 0f) return Offset.Zero
            val scaledW = contentWidthPx * scale
            val scaledH = contentHeightPx * scale
            val excessW = max(0f, (scaledW - viewportWidthPx) / 2f)
            val excessH = max(0f, (scaledH - viewportHeightPx) / 2f)
            return Offset(
                x = raw.x.coerceIn(-excessW, excessW),
                y = raw.y.coerceIn(-excessH, excessH)
            )
        }

        Box(
            Modifier.pointerInput(dynamicMaxScale) {
                detectTransformGestures { _, pan, zoom, _ ->
                    val newScale = (scale * zoom).coerceIn(minScale, dynamicMaxScale)
                    val scaleChange = if (scale == 0f) 1f else newScale / scale
                    val newOffset = (offset + pan) * scaleChange
                    scale = newScale
                    offset = boundedOffset(newOffset)
                }
            }
        ) {
            Box(
                Modifier
                    .onSizeChanged { s ->
                        contentWidthPx = s.width.toFloat()
                        contentHeightPx = s.height.toFloat()
                        offset = boundedOffset(offset)
                    }
                    .graphicsLayer {
                        translationX = offset.x
                        translationY = offset.y
                        scaleX = scale
                        scaleY = scale
                        clip = true
                    }
            ) {
                content(scale)
            }
        }
    }
}
