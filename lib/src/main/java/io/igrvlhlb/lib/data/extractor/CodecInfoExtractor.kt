package io.igrvlhlb.lib.data.extractor

import android.media.MediaCodecInfo
import android.os.Build
import android.util.Range
import io.igrvlhlb.lib.codeci.utils.isSoftwareCodec
import io.igrvlhlb.lib.data.AudioCapabilitiesInfo
import io.igrvlhlb.lib.data.BasicCodecInfo
import io.igrvlhlb.lib.data.BitrateMode
import io.igrvlhlb.lib.data.CodecCapabilitiesInfo
import io.igrvlhlb.lib.data.CodecInfo
import io.igrvlhlb.lib.data.CommonResolutions
import io.igrvlhlb.lib.data.EncoderCapabilitiesInfo
import io.igrvlhlb.lib.data.PerformancePoint
import io.igrvlhlb.lib.data.ReportedPerformancePoint
import io.igrvlhlb.lib.data.ValueRange
import io.igrvlhlb.lib.data.VideoCapabilitiesInfo
import io.igrvlhlb.lib.data.mapper.CodecConstantsMapper.colorFormatToString
import io.igrvlhlb.lib.data.mapper.CodecConstantsMapper.profileLevelToString
import io.igrvlhlb.lib.data.mapper.MediaFormat
import io.igrvlhlb.lib.data.toValueRange
import io.igrvlhlb.lib.utils.fragile

/**
 * Service responsible for extracting and structuring codec information from MediaCodecInfo
 */
class CodecInfoExtractor {

    /**
     * Extracts structured codec information from MediaCodecInfo
     */
    fun extractCodecInfo(codecInfo: MediaCodecInfo): CodecInfo {
        return CodecInfo(
            name = codecInfo.name,
            supportedTypes = codecInfo.supportedTypes.toList(),
            isSoftwareCodec = codecInfo.isSoftwareCodec(),
            isEncoder = codecInfo.isEncoder,
            basicInfo = extractBasicInfo(codecInfo),
            capabilities = extractCapabilities(codecInfo)
        )
    }

    /**
     * Extracts basic codec information with API level compatibility
     */
    private fun extractBasicInfo(codecInfo: MediaCodecInfo): BasicCodecInfo? {
        return if (Build.VERSION.SDK_INT >= 29) {
            BasicCodecInfo(
                canonicalName = codecInfo.canonicalName,
                isHardwareAccelerated = codecInfo.isHardwareAccelerated,
                isSoftwareOnly = codecInfo.isSoftwareOnly,
                isVendor = codecInfo.isVendor
            )
        } else {
            null
        }
    }

    /**
     * Extracts capabilities for all supported MIME types
     */
    private fun extractCapabilities(codecInfo: MediaCodecInfo): List<CodecCapabilitiesInfo> {
        return codecInfo.supportedTypes.map { mimeType ->
            val capabilities = codecInfo.getCapabilitiesForType(mimeType)
            CodecCapabilitiesInfo(
                supportedType = mimeType, // is it the same as capabilities.mimeType?
                mimeType = capabilities.mimeType,
                defaultFormat = MediaFormat(capabilities.defaultFormat),
                colorFormats = capabilities.colorFormats.map { colorFormatToString(it) },
                profileLevels = capabilities.profileLevels.map {
                    profileLevelToString(capabilities.mimeType, it).let {
                        "${it.first}, ${it.second}"
                    }
                },
                maxSupportedInstances = capabilities.maxSupportedInstances,
                audioCapabilities = extractAudioCapabilities(capabilities.audioCapabilities),
                videoCapabilities = extractVideoCapabilities(capabilities.videoCapabilities),
                encoderCapabilities = extractEncoderCapabilities(capabilities.encoderCapabilities)
            )
        }
    }

    /**
     * Extracts audio capabilities if available
     */
    private fun extractAudioCapabilities(
        audioCapabilities: MediaCodecInfo.AudioCapabilities?
    ): AudioCapabilitiesInfo? {
        return audioCapabilities?.let { audio ->
            AudioCapabilitiesInfo(
                bitrateRange = audio.bitrateRange.toValueRange(),
                maxInputChannelCount = audio.maxInputChannelCount,
                // Uses a fragile block to safely handle potential crashes observed on API 24
                supportedSampleRates =  fragile { audio.supportedSampleRates }?.toList(),
                supportedSampleRateRanges = audio.supportedSampleRateRanges?.map {
                    ValueRange(it.lower, it.upper)
                },
                minInputChannelCount = if (Build.VERSION.SDK_INT >= 31) {
                    audio.minInputChannelCount
                } else null,
                inputChannelCountRanges = if (Build.VERSION.SDK_INT >= 31) {
                    audio.inputChannelCountRanges.map { ValueRange(it.lower, it.upper) }
                } else null
            )
        }
    }

    /**
     * Extracts video capabilities if available
     */
    private fun extractVideoCapabilities(
        videoCapabilities: MediaCodecInfo.VideoCapabilities?
    ): VideoCapabilitiesInfo? {
        return videoCapabilities?.let { capability ->
            VideoCapabilitiesInfo(
                bitrateRange = capability.bitrateRange.toValueRange(),
                supportedFrameRates = capability.supportedFrameRates.toValueRange(),
                supportedHeights = capability.supportedHeights.toValueRange(),
                supportedWidths = capability.supportedWidths.toValueRange(),
                widthAlignment = capability.widthAlignment,
                heightAlignment = capability.heightAlignment,
                maxSupportedFrameRates = extractMaxSupportedFrameRates(capability),
                achievableFrameRates = extractAchievableFrameRates(capability),
                supportedPerformancePoints = extractPerformancePoints(capability),
            )
        }
    }

    /**
     * Extracts performance points for video capabilities (API 29+)
     */
    private fun extractPerformancePoints(
        videoCapabilities: MediaCodecInfo.VideoCapabilities
    ): List<ReportedPerformancePoint>? = if (Build.VERSION.SDK_INT >= 29) {
        videoCapabilities.supportedPerformancePoints?.mapNotNull { PerformancePointParser(it).parse() }
    } else {
        null
    }

    /**
     * Extracts encoder capabilities if available
     */
    private fun extractEncoderCapabilities(
        encoderCapabilities: MediaCodecInfo.EncoderCapabilities?
    ): EncoderCapabilitiesInfo? {
        return encoderCapabilities?.let { encoder ->
            EncoderCapabilitiesInfo(
                complexityRange = encoder.complexityRange.toValueRange(),
                qualityRange = if (Build.VERSION.SDK_INT >= 28) encoder.qualityRange.toValueRange() else null,
                isBitrateModeSupported = mapOf(
                    BitrateMode.CQ to encoder.isBitrateModeSupported(BitrateMode.CQ.value),
                    BitrateMode.VBR to encoder.isBitrateModeSupported(BitrateMode.VBR.value),
                    BitrateMode.CBR to encoder.isBitrateModeSupported(BitrateMode.CBR.value),
                    BitrateMode.CBR_FD to if (Build.VERSION.SDK_INT >= 31) {
                        encoder.isBitrateModeSupported(BitrateMode.CBR_FD.value)
                    } else false
                )
            )
        }
    }

    private fun extractMaxSupportedFrameRates(
        videoCapabilities: MediaCodecInfo.VideoCapabilities
    ): List<PerformancePoint> {
        return CommonResolutions.entries.map {
            val supportedFrameRate = try {
                videoCapabilities.getSupportedFrameRatesFor(
                    it.resolution.width,
                    it.resolution.height
                )
            } catch (e: Exception) {
                Range(0.0, 0.0) // Fallback if resolution is not supported
            }
            PerformancePoint(it.resolution, supportedFrameRate.toValueRange())
        }
    }

    private fun extractAchievableFrameRates(
        videoCapabilities: MediaCodecInfo.VideoCapabilities
    ): List<PerformancePoint> {
        return CommonResolutions.entries.map { commonResolution ->
            val achievableFrameRates = try {
                videoCapabilities.getAchievableFrameRatesFor(
                    commonResolution.resolution.width,
                    commonResolution.resolution.height
                ) ?: Range(0.0, 0.0)
            } catch (e: Exception) {
                Range(0.0, 0.0) // Fallback if resolution is not supported
            }
            PerformancePoint(commonResolution.resolution, achievableFrameRates.toValueRange())
        }
    }
}