package io.igrvlhlb.codeci.ui.composables
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

import androidx.annotation.FloatRange
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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

fun Modifier.minAspectRatio(
    @FloatRange(
        0.0,
        Double.POSITIVE_INFINITY,
        false,
        true
    ) aspectRatio: Double,
    alignment: Alignment = Alignment.TopStart
) =
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

//fun getAlignmetPosition(width: Int, height: Int, alignment: Alignment) {
//    val (cW, cH) = width/2 to height/2
//    alignment.align()
//}
//
//@Composable
//@Preview
//fun HardwareIndicatorPreview() {
//    Box(
//        modifier = Modifier
//            .background(Color.Blue),
////            .wrapContentSize()
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "TS",
//            color = Color.White,
//            fontWeight = FontWeight.Bold,
//            fontSize = 8.sp,
//            modifier = Modifier
//                .sizeIn(24.dp, 24.dp)
//                .minAspectRatio(2.0),
//        )
//    }
//}

@Composable
fun RoundingFrame(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .border(borderWidth, color, shape)
            .clip(shape)
    ) {
        content()
    }
}