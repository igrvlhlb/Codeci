package io.igrvlhlb.lib.data

import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR
import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR_FD
import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CQ
import android.media.MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_VBR
import android.util.Range
import io.igrvlhlb.lib.codeci.utils.roundTo
import io.igrvlhlb.lib.data.mapper.MediaFormat
import io.igrvlhlb.lib.utils.sdkAtLeast

/**
 * Structured representation of codec information extracted from MediaCodecInfo
 */
data class CodecInfo(
    val name: String,
    val supportedTypes: List<String>,
    val isSoftwareCodec: Boolean,
    val isEncoder: Boolean,
    val basicInfo: BasicCodecInfo,
    val capabilities: List<CodecCapabilitiesInfo>
)

/**
 * Basic codec information available on all Android versions
 */
data class BasicCodecInfo(
    val canonicalName: String?,
    val isHardwareAccelerated: Boolean?,
    val isSoftwareOnly: Boolean?,
    val isVendor: Boolean?
)

/**
 * Information about codec capabilities for a specific MIME type
 */
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
data class AudioCapabilitiesInfo(
    val bitrateRange: Range<Int>,
    val maxInputChannelCount: Int,
    val supportedSampleRates: List<Int>?,
    val supportedSampleRateRanges: List<Pair<Int, Int>>?,
    val minInputChannelCount: Int?,
    val inputChannelCountRanges: List<Pair<Int, Int>>?
)

/**
 * Video-specific codec capabilities
 */
data class VideoCapabilitiesInfo(
    val bitrateRange: Range<Int>,
    val supportedFrameRates: Range<Int>?,
    val supportedHeights: Range<Int>,
    val supportedWidths: Range<Int>,
    val widthAlignment: Int,
    val heightAlignment: Int,
    val maxSupportedFrameRates: List<PerformancePoint>,
    val achievableFrameRates: List<PerformancePoint>,
    val supportedPerformancePoints: List<String>?,
)

/**
 * Encoder-specific capabilities
 */
data class EncoderCapabilitiesInfo(
    val complexityRange: Range<Int>?,
    val qualityRange: Range<Int>?,
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

data class PerformancePoint(val width: Int, val height: Int, val frameRates: Range<Double>) {

    constructor(resolution: Resolution, frameRates: Range<Double>) : this(
        resolution.width,
        resolution.height,
        frameRates
    )

    val resolution: Resolution
        get() = Resolution(width, height)

    override fun toString(): String {
        val frameRateString = if (frameRates.upper == 0.0) {
            "Unknown"
        } else {
            "[${frameRates.lower.roundTo(2)} - ${frameRates.upper.roundTo(2)}]"
        }
        return "${width}x${height} @ $frameRateString fps"
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