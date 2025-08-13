package io.igrvlhlb.lib.data.formatter

import android.os.Build
import android.util.Range
import io.igrvlhlb.lib.codeci.utils.ifZeroThen
import io.igrvlhlb.lib.codeci.utils.roundTo
import io.igrvlhlb.lib.data.AudioCapabilitiesInfo
import io.igrvlhlb.lib.data.BasicCodecInfo
import io.igrvlhlb.lib.data.CodecCapabilitiesInfo
import io.igrvlhlb.lib.data.CodecInfo
import io.igrvlhlb.lib.data.EncoderCapabilitiesInfo
import io.igrvlhlb.lib.data.VideoCapabilitiesInfo

/**
 * Utility class for formatting codec information into human-readable strings
 */
object CodecInfoFormatter {

    /**
     * Formats a CodecInfo object into a comprehensive string representation
     */
    fun formatCodecInfo(codecInfo: CodecInfo): String {
        return buildString {
            appendLine("=== Device INFO ===")
            appendLine("Android Version: ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})")
            if (Build.VERSION.SDK_INT >= 31)
                appendLine("Media Perormance Class: ${Build.VERSION.MEDIA_PERFORMANCE_CLASS}")
            appendLine("=== CODEC INFO ===")
            appendLine("Name: ${codecInfo.name}")
            appendLine("Supported Types: ${codecInfo.supportedTypes.joinToString(", ")}")
            appendLine("Is Software Codec: ${codecInfo.isSoftwareCodec}")
            appendLine("Is Encoder: ${codecInfo.isEncoder}")
            appendLine()

            append(formatBasicInfo(codecInfo.basicInfo))
            appendLine()

            codecInfo.capabilities.forEach { capability ->
                append(formatCapability(capability))
                appendLine()
            }
        }
    }

    /**
     * Formats basic codec information
     */
    fun formatBasicInfo(basicInfo: BasicCodecInfo): String {
        return buildString {
            appendLine("=== BASIC INFO ===")
            basicInfo.canonicalName?.let { appendLine("Canonical Name: $it") }
            basicInfo.isHardwareAccelerated?.let { appendLine("Is Hardware Accelerated: $it") }
            basicInfo.isSoftwareOnly?.let { appendLine("Is Software Only: $it") }
            basicInfo.isVendor?.let { appendLine("Is Vendor: $it") }
        }
    }

    /**
     * Formats a single capability information
     */
    fun formatCapability(capability: CodecCapabilitiesInfo): String {
        return buildString {
            appendLine("=== CAPABILITIES (${capability.mimeType}) ===")
            appendLine("MIME Type: ${capability.mimeType}")
            appendLine("Default Format: ${capability.defaultFormat}")
            appendLine("Max Supported Instances: ${capability.maxSupportedInstances}")

            if (capability.colorFormats.isNotEmpty()) {
                appendLine("Color Formats:")
                capability.colorFormats.forEach { format ->
                    appendLine("  - $format")
                }
            }

            if (capability.profileLevels.isNotEmpty()) {
                appendLine("Profile Levels:")
                capability.profileLevels.forEach { profile ->
                    appendLine("  - $profile")
                }
            }

            capability.audioCapabilities?.let {
                append(formatAudioCapabilities(it))
            }

            capability.videoCapabilities?.let {
                append(formatVideoCapabilities(it))
            }

            capability.encoderCapabilities?.let {
                append(formatEncoderCapabilities(it))
            }
        }
    }

    /**
     * Formats audio capabilities
     */
    fun formatAudioCapabilities(audioCapabilities: AudioCapabilitiesInfo): String {
        return buildString {
            appendLine("--- AUDIO CAPABILITIES ---")
            appendLine("Bitrate Range: ${formatRange(audioCapabilities.bitrateRange)} bps")
            appendLine("Max Input Channel Count: ${audioCapabilities.maxInputChannelCount}")

            audioCapabilities.supportedSampleRates?.let {
                appendLine("Supported Sample Rates: ${it.joinToString(", ")} Hz")
            }

            audioCapabilities.supportedSampleRateRanges?.let { ranges ->
                appendLine("Supported Sample Rate Ranges:")
                ranges.forEach { (min, max) ->
                    appendLine("  - $min Hz to $max Hz")
                }
            }

            audioCapabilities.minInputChannelCount?.let {
                appendLine("Min Input Channel Count: $it")
            }

            audioCapabilities.inputChannelCountRanges?.let { ranges ->
                appendLine("Input Channel Count Ranges:")
                ranges.forEach { (min, max) ->
                    appendLine("  - $min to $max channels")
                }
            }
        }
    }

    /**
     * Formats video capabilities
     */
    fun formatVideoCapabilities(videoCapabilities: VideoCapabilitiesInfo): String {
        return buildString {
            appendLine("--- VIDEO CAPABILITIES ---")
            appendLine("Bitrate Range: ${formatRange(videoCapabilities.bitrateRange)} bps")
            appendLine("Supported Heights: ${formatRange(videoCapabilities.supportedHeights)} px")
            appendLine("Supported Widths: ${formatRange(videoCapabilities.supportedWidths)} px")
            appendLine("Width Alignment: ${videoCapabilities.widthAlignment}")
            appendLine("Height Alignment: ${videoCapabilities.heightAlignment}")

            videoCapabilities.supportedFrameRates?.let {
                appendLine("Supported Frame Rates: ${formatRange(it)} fps")
            }

            appendLine("Supported Performance Points:")
            videoCapabilities.supportedPerformancePoints?.let { points ->
                points.forEach { point ->
                    appendLine("  - $point") // Assuming point.toString() gives a meaningful representation
                }
            } ?: appendLine("  - None")

            appendLine("Max Supported Frame Rates:")
            videoCapabilities.maxSupportedFrameRates.forEach { point ->
                val upper = point.frameRates.upper
                appendLine("  - ${point.width}x${point.height} : ${point.frameRates.upper.roundTo(2).ifZeroThen { "Not supported" }}")
            }
            appendLine("Available Frame Rates:")
            videoCapabilities.achievableFrameRates.forEach { point ->
                appendLine("  - $point")
            }
        }
    }

    /**
     * Formats encoder capabilities
     */
    fun formatEncoderCapabilities(encoderCapabilities: EncoderCapabilitiesInfo): String {
        return buildString {
            appendLine("--- ENCODER CAPABILITIES ---")

            encoderCapabilities.complexityRange?.let {
                appendLine("Complexity Range: ${formatRange(it)}")
            }

            encoderCapabilities.qualityRange?.let {
                appendLine("Quality Range: ${formatRange(it)}")
            }

            appendLine("Supported Bitrate Modes:")
            encoderCapabilities.isBitrateModeSupported.forEach { (mode, supported) ->
                appendLine("  - ${mode.name}: ${if (supported) "Supported" else "Not Supported"}")
            }
        }
    }

    /**
     * Formats a Range object into a readable string
     */
    private fun formatRange(range: Range<Int>): String {
        return "${range.lower} - ${range.upper}"
    }

    /**
     * Gets a summary string for a codec (useful for lists)
     */
    fun getCodecSummary(codecInfo: CodecInfo): String {
        val type = if (codecInfo.isEncoder) "Encoder" else "Decoder"
        val implementation = if (codecInfo.isSoftwareCodec) "Software" else "Hardware"
        return "${codecInfo.name} ($type, $implementation)"
    }

    /**
     * Gets the supported MIME types as a formatted string
     */
    fun getSupportedTypesString(codecInfo: CodecInfo): String {
        return codecInfo.supportedTypes.joinToString(", ")
    }
}