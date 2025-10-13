package com.example.codeci.utils
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Int.intToDp() = with(LocalDensity.current) { this@intToDp.toDp() }