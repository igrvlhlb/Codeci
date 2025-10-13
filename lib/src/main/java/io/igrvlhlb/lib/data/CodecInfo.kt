package io.igrvlhlb.lib.data

import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR
import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR_FD
import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CQ
import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_VBR
import io.igrvlhlb.lib.codeci.utils.roundTo
import io.igrvlhlb.lib.data.mapper.MediaFormat
import io.igrvlhlb.lib.utils.sdkAtLeast
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration


/**
 * Structured representation of codec information extracted from MediaCodecInfo
 */
@Serializable
data class CodecInfo(
    val name: String,
    val supportedTypes: List<String>,
    val isSoftwareCodec: Boolean,
    val isEncoder: Boolean,
    val basicInfo: BasicCodecInfo?,
    val capabilities: List<CodecCapabilitiesInfo>
) {
    fun serialize(optPrettyPrint: Boolean = false, optExplicitNulls: Boolean = false): String {
        return when {
            optPrettyPrint && optExplicitNulls -> {
                PRETTY_JSON_EXPLICIT_NULLS.encodeToString(this)
            }
            optPrettyPrint -> PRETTY_JSON.encodeToString(this)
            optExplicitNulls -> PLAIN_JSON.encodeToString(this)
            else -> PLAIN_JSON_NO_NULLS.encodeToString(this)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    companion object {
        private val PLAIN_JSON = Json
        private val PLAIN_JSON_NO_NULLS = Json {
            explicitNulls = false
        }
        private val PRETTY_JSON = Json {
            prettyPrint = true
            explicitNulls = false
            prettyPrintIndent = "\t"
        }
        private val PRETTY_JSON_EXPLICIT_NULLS = Json {
            prettyPrint = true
            explicitNulls = true
            prettyPrintIndent = "\t"
        }
    }
}

/**
 * Basic codec information available on all Android versions
 */
@Serializable
data class BasicCodecInfo(
    val canonicalName: String,
    val isHardwareAccelerated: Boolean,
    val isSoftwareOnly: Boolean,
    val isVendor: Boolean
)

/**
 * Information about codec capabilities for a specific MIME type
 */
@Serializable
data class CodecCapabilitiesInfo(
    val supportedType: String,
    val mimeType: String,
    val defaultFormat: MediaFormat?,
    val colorFormats: List<String>,
    val profileLevels: List<String>,
    val maxSupportedInstances: Int,
    val audioCapabilities: AudioCapabilitiesInfo?,
    val videoCapabilities: VideoCapabilitiesInfo?,
    val encoderCapabilities: EncoderCapabilitiesInfo?
)

/**
 * Audio-specific codec capabilities
 */
@Serializable
data class AudioCapabilitiesInfo(
    val bitrateRange: ValueRange<Int>,
    val maxInputChannelCount: Int,
    val supportedSampleRates: List<Int>?,
    val supportedSampleRateRanges: List<ValueRange<Int>>?,
    val minInputChannelCount: Int?,
    val inputChannelCountRanges: List<ValueRange<Int>>?
)

/**
 * Video-specific codec capabilities
 */
@Serializable
data class VideoCapabilitiesInfo(
    val bitrateRange: ValueRange<Int>,
    val supportedFrameRates: ValueRange<Int>?,
    val supportedHeights: ValueRange<Int>,
    val supportedWidths: ValueRange<Int>,
    val widthAlignment: Int,
    val heightAlignment: Int,
    val maxSupportedFrameRates: List<PerformancePoint>,
    val achievableFrameRates: List<PerformancePoint>,
    val supportedPerformancePoints: List<ReportedPerformancePoint>?,
)

/**
 * Encoder-specific capabilities
 */
@Serializable
data class EncoderCapabilitiesInfo(
    val complexityRange: ValueRange<Int>?,
    val qualityRange: ValueRange<Int>?,
    val isBitrateModeSupported: Map<BitrateMode, Boolean>
)

/**
 * Bitrate modes for encoders
 */
enum class BitrateMode(val value: Int) {
    CQ(BITRATE_MODE_CQ),
    VBR(BITRATE_MODE_VBR),
    CBR(BITRATE_MODE_CBR),
    CBR_FD(if (sdkAtLeast(31)) BITRATE_MODE_CBR_FD else -1)
}

data class Resolution(val width: Int, val height: Int)

@Serializable
data class PerformancePoint(
    val width: Int,
    val height: Int,
    val frameRates: ValueRange<Double>,
) {
    constructor(resolution: Resolution, frameRates: ValueRange<Double>) : this(
        resolution.width,
        resolution.height,
        frameRates
    )

    val resolution: Resolution
        get() = Resolution(width, height)

    override fun toString(): String {
        val frameRateString = if (frameRates.upper == 0.0) {
            "Unknown"
        } else if (frameRates.upper != frameRates.lower) {
            "[${frameRates.lower.roundTo(2)} - ${frameRates.upper.roundTo(2)}]"
        } else {
            frameRates.lower.roundTo(2).toString()
        }
        return "${width}x${height}@${frameRateString} fps"
    }
}

@Serializable
data class ReportedPerformancePoint(
    val width: Int,
    val height: Int,
    val frameRate: Double,
    val maxFrameRate: Double? = null,
    val blockWidth: Int? = null,
    val blockHeight: Int? = null,
) {
    override fun toString(): String {
        val base = "PerformancePoint(${width}x${height}@${frameRate.roundTo(0).toInt()}fps"
        val maxFpsPart = maxFrameRate?.let { ", max ${it.roundTo(0).toInt()}fps" } ?: ""
        val blockPart = if (blockWidth != null && blockHeight != null) {
            ", ${blockWidth}x${blockHeight} blocks"
        } else ""
        return base + maxFpsPart + blockPart + ")"
    }
}

enum class CommonResolutions(val resolution: Resolution) {
    R144p(Resolution(256, 144)),
    R240p(Resolution(426, 240)),
    R360p(Resolution(640, 360)),
    R480p(Resolution(854, 480)),
    R720p(Resolution(1280, 720)),
    R1080p(Resolution(1920, 1080)),
    R1440p(Resolution(2560, 1440)),
    R4K(Resolution(3840, 2160)),
    R8K(Resolution(7680, 4320))
}


@Serializable
data class ValueRange<T: Comparable<T>>(val lower: T, val upper: T) {
    constructor(range: android.util.Range<T>) : this(range.lower, range.upper)
    constructor(range: Pair<T, T>) : this(range.first, range.second)

    override fun toString(): String {
        return "($lower - $upper)"
    }
}

fun <T: Comparable<T>>android.util.Range<T>.toValueRange() = ValueRange(this)