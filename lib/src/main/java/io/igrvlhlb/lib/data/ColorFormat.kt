package io.igrvlhlb.lib.data
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import android.media.MediaCodecInfo
import android.os.Build
import androidx.annotation.RequiresApi

enum class ColorFormat(val value: Int) {
    COLOR_FormatMonochrome(MediaCodecInfo.CodecCapabilities.COLOR_FormatMonochrome),
    COLOR_Format8bitRGB332(MediaCodecInfo.CodecCapabilities.COLOR_Format8bitRGB332),
    COLOR_Format12bitRGB444(MediaCodecInfo.CodecCapabilities.COLOR_Format12bitRGB444),
    COLOR_Format16bitARGB4444(MediaCodecInfo.CodecCapabilities.COLOR_Format16bitARGB4444),
    COLOR_Format16bitARGB1555(MediaCodecInfo.CodecCapabilities.COLOR_Format16bitARGB1555),
    COLOR_Format16bitRGB565(MediaCodecInfo.CodecCapabilities.COLOR_Format16bitRGB565),
    COLOR_Format16bitBGR565(MediaCodecInfo.CodecCapabilities.COLOR_Format16bitBGR565),
    COLOR_Format18bitRGB666(MediaCodecInfo.CodecCapabilities.COLOR_Format18bitRGB666),
    COLOR_Format18bitARGB1665(MediaCodecInfo.CodecCapabilities.COLOR_Format18bitARGB1665),
    COLOR_Format19bitARGB1666(MediaCodecInfo.CodecCapabilities.COLOR_Format19bitARGB1666),
    COLOR_Format24bitRGB888(MediaCodecInfo.CodecCapabilities.COLOR_Format24bitRGB888),
    COLOR_Format24bitBGR888(MediaCodecInfo.CodecCapabilities.COLOR_Format24bitBGR888),
    COLOR_Format24bitARGB1887(MediaCodecInfo.CodecCapabilities.COLOR_Format24bitARGB1887),
    COLOR_Format25bitARGB1888(MediaCodecInfo.CodecCapabilities.COLOR_Format25bitARGB1888),
    COLOR_Format32bitBGRA8888(MediaCodecInfo.CodecCapabilities.COLOR_Format32bitBGRA8888),
    COLOR_Format32bitARGB8888(MediaCodecInfo.CodecCapabilities.COLOR_Format32bitARGB8888),
    COLOR_FormatYUV411Planar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV411Planar),
    COLOR_FormatYUV411PackedPlanar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV411PackedPlanar),
    COLOR_FormatYUV420Planar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Planar),
    COLOR_FormatYUV420PackedPlanar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420PackedPlanar),
    COLOR_FormatYUV420SemiPlanar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar),
    COLOR_FormatYUV422Planar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422Planar),
    COLOR_FormatYUV422PackedPlanar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422PackedPlanar),
    COLOR_FormatYUV422SemiPlanar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422SemiPlanar),
    COLOR_FormatYCbYCr(MediaCodecInfo.CodecCapabilities.COLOR_FormatYCbYCr),
    COLOR_FormatYCrYCb(MediaCodecInfo.CodecCapabilities.COLOR_FormatYCrYCb),
    COLOR_FormatCbYCrY(MediaCodecInfo.CodecCapabilities.COLOR_FormatCbYCrY),
    COLOR_FormatCrYCbY(MediaCodecInfo.CodecCapabilities.COLOR_FormatCrYCbY),
    COLOR_FormatYUV444Interleaved(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV444Interleaved),
    COLOR_FormatRawBayer8bit(MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer8bit),
    COLOR_FormatRawBayer10bit(MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer10bit),
    COLOR_FormatRawBayer8bitcompressed(MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer8bitcompressed),
    COLOR_FormatL2(MediaCodecInfo.CodecCapabilities.COLOR_FormatL2),
    COLOR_FormatL4(MediaCodecInfo.CodecCapabilities.COLOR_FormatL4),
    COLOR_FormatL8(MediaCodecInfo.CodecCapabilities.COLOR_FormatL8),
    COLOR_FormatL16(MediaCodecInfo.CodecCapabilities.COLOR_FormatL16),
    COLOR_FormatL24(MediaCodecInfo.CodecCapabilities.COLOR_FormatL24),
    COLOR_FormatL32(MediaCodecInfo.CodecCapabilities.COLOR_FormatL32),
    COLOR_FormatYUV420PackedSemiPlanar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420PackedSemiPlanar),
    COLOR_FormatYUV422PackedSemiPlanar(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422PackedSemiPlanar),
    COLOR_Format18BitBGR666(MediaCodecInfo.CodecCapabilities.COLOR_Format18BitBGR666),
    COLOR_Format24BitARGB6666(MediaCodecInfo.CodecCapabilities.COLOR_Format24BitARGB6666),
    COLOR_Format24BitABGR6666(MediaCodecInfo.CodecCapabilities.COLOR_Format24BitABGR6666),
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    COLOR_FormatYUVP010(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUVP010),
    COLOR_TI_FormatYUV420PackedSemiPlanar(MediaCodecInfo.CodecCapabilities.COLOR_TI_FormatYUV420PackedSemiPlanar),
    COLOR_FormatSurface(MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface),
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    COLOR_Format64bitABGRFloat(MediaCodecInfo.CodecCapabilities.COLOR_Format64bitABGRFloat),
    COLOR_Format32bitABGR8888(MediaCodecInfo.CodecCapabilities.COLOR_Format32bitABGR8888),
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    COLOR_Format32bitABGR2101010(MediaCodecInfo.CodecCapabilities.COLOR_Format32bitABGR2101010),
    COLOR_FormatYUV420Flexible(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Flexible),
    COLOR_FormatYUV422Flexible(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422Flexible),
    COLOR_FormatYUV444Flexible(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV444Flexible),
    COLOR_FormatRGBFlexible(MediaCodecInfo.CodecCapabilities.COLOR_FormatRGBFlexible),
    COLOR_FormatRGBAFlexible(MediaCodecInfo.CodecCapabilities.COLOR_FormatRGBAFlexible),
    COLOR_QCOM_FormatYUV420SemiPlanar(MediaCodecInfo.CodecCapabilities.COLOR_QCOM_FormatYUV420SemiPlanar),
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    COLOR_FormatYUVP210(MediaCodecInfo.CodecCapabilities.COLOR_FormatYUVP210);

    val formatName get() = this.name.substring(12)

    override fun toString(): String {
        return "$name(0x${value.toString(16)})"
    }

    companion object {
        fun from(value: Int): ColorFormat? = ColorFormat.entries.find { it.value == value }
    }
}