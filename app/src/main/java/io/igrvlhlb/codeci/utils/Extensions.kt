package com.example.codeci.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Int.intToDp() = with(LocalDensity.current) { this@intToDp.toDp() }