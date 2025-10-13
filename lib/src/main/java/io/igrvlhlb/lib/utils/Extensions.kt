package io.igrvlhlb.lib.codeci.utils
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import android.media.MediaCodecInfo
import android.os.Build
import kotlin.math.pow
import kotlin.math.roundToLong

/*
Function MediaCodecList.isSoftwareCodec() obtained from
https://android.googlesource.com/platform/frameworks/av/+/master/media/libstagefright/MediaCodecList.cpp#320
*/
fun isSoftwareCodec(codecName: String): Boolean {
/*
 * Copyright 2012, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
    return codecName.startsWith("OMX.google.", true)
            || codecName.startsWith("c2.android.", true)
            || (!codecName.startsWith("OMX.", true)
            && !codecName.startsWith("c2.", true));
}

fun MediaCodecInfo.isSoftwareCodec(): Boolean {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        isSoftwareCodec(name)
    } else {
        !isHardwareAccelerated || isSoftwareOnly
    }
}

fun Number.ifZeroThen(f: () -> Any) = if (this.toDouble() == 0.0) f() else this

fun Number.roundTo(decimals: Int): Double {
    val factor = (10.0).pow(decimals)
    return (this.toDouble() * factor).roundToLong() / factor
}