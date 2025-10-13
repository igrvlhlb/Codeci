package io.igrvlhlb.lib.data.extractor
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import android.media.MediaCodecInfo
import io.igrvlhlb.lib.data.PerformancePoint
import io.igrvlhlb.lib.data.ReportedPerformancePoint
import io.igrvlhlb.lib.data.ValueRange

class PerformancePointParser(pp: String) {
    constructor(pp: MediaCodecInfo.VideoCapabilities.PerformancePoint) : this(pp.toString())

    private val ppString: String = pp
    private var pointer = 0

    fun expect(regex: Regex): String? {
        if (pointer >= ppString.length) return null
        val res = regex.matchAt(ppString, pointer)
        if (res == null) return null
        pointer = res.range.last + 1
        return res.value
    }

    fun expect(str: String) : String? {
        if (pointer >= ppString.length) return null
        return ppString.substring(pointer).takeIf { it.startsWith(str) }?.also {
            pointer += str.length
        }
    }

    fun parse(): ReportedPerformancePoint? {
        expect("PerformancePoint(")
        val width = expect(numberRegex)?.toDouble() ?: return null
        expect("x") ?: return null
        val height = expect(numberRegex)?.toDouble() ?: return null
        expect("@") ?: return null
        val origRate = expect(numberRegex)?.toDouble() ?: return null
        var maxFrameRate: Double? = null
        var blockWidth: Double? = null
        var blockHeight: Double? = null
        if (expect(", max ") != null) {
            maxFrameRate = expect(numberRegex)?.toDouble() ?: return null
            expect("fps") ?: return null
        }
        if (expect(", ") != null) {
            blockWidth = expect(numberRegex)?.toDouble() ?: return null
            expect("x") ?: return null
            blockHeight = expect(numberRegex)?.toDouble() ?: return null
            expect(" blocks") ?: return null
        }
        return ReportedPerformancePoint(
            width = width.toInt(),
            height = height.toInt(),
            frameRate = origRate,
            blockWidth = blockWidth?.toInt(),
            blockHeight = blockHeight?.toInt(),
        )
    }

    companion object {
        private val numberRegex = Regex("""(\d+\.?\d*)""")
    }
}