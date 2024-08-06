package io.igrvlhlb.codeci.utils

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout

@Composable
fun WrappingSquareLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        var maxWidth = 0
        var maxHeight = 0
        val placeables = measurables.map {
            it.measure(constraints).apply {
                maxWidth = width.coerceAtLeast(maxWidth)
                maxHeight = height.coerceAtLeast(maxHeight)
            }
        }
        val maxSize = maxOf(maxWidth, maxHeight)
        layout(maxSize, maxSize) {
            placeables.map {
                val x = (maxSize - it.width) / 2
                val y = (maxSize - it.height) / 2
                it.place(x, y)
            }
        }
    }
}

fun Modifier.minAspectRatio(@FloatRange(0.0, Double.POSITIVE_INFINITY, false, true) aspectRatio: Double) =
    layout { measurable, constraints ->
        val newConstraints = with(measurable.measure(constraints)) {
            val currentAspectRatio = width.toFloat() / height
            if (currentAspectRatio > aspectRatio) {
                val h = (width / aspectRatio).toInt()
                constraints.copy(minHeight = h.coerceAtMost(constraints.maxHeight))
            } else {
                val w = (height * aspectRatio).toInt()
                constraints.copy(minWidth = w.coerceAtMost(constraints.maxWidth))
            }
        }
        val placeable = measurable.measure(newConstraints)
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }