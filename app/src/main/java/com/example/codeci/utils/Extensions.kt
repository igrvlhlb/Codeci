package com.example.codeci.utils

import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaCodecList

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
    return isSoftwareCodec(this.name)
}