package io.igrvlhlb.lib

import android.media.MediaCodecInfo
import android.media.MediaCodecList
import io.igrvlhlb.lib.data.CodecInfo
import io.igrvlhlb.lib.data.extractor.CodecInfoExtractor

/**
 * Repository for managing codec information extraction and caching
 */
class CodecInfoRepository {

    private val codecInfoExtractor = CodecInfoExtractor()
    private val cachedCodecInfos = mutableMapOf<String, CodecInfo>()

    /**
     * Gets structured codec information for a specific MediaCodecInfo
     * Results are cached to avoid re-extraction
     */
    fun getCodecInfo(mediaCodecInfo: MediaCodecInfo): CodecInfo {
        return cachedCodecInfos.getOrPut(mediaCodecInfo.name) {
            codecInfoExtractor.extractCodecInfo(mediaCodecInfo)
        }
    }

    /**
     * Gets structured codec information for all available codecs
     */
    fun getAllCodecInfos(): List<CodecInfo> {
        val codecList = MediaCodecList(MediaCodecList.ALL_CODECS)
        return codecList.codecInfos.map { getCodecInfo(it) }
    }

    /**
     * Gets structured codec information for regular codecs only
     */
    fun getRegularCodecInfos(): List<CodecInfo> {
        val codecList = MediaCodecList(MediaCodecList.REGULAR_CODECS)
        return codecList.codecInfos.map { getCodecInfo(it) }
    }

    /**
     * Clears the cache of extracted codec information
     */
    fun clearCache() {
        cachedCodecInfos.clear()
    }

    /**
     * Filters codec infos by encoder/decoder type
     */
    fun getCodecInfosByType(isEncoder: Boolean): List<CodecInfo> {
        return getAllCodecInfos().filter { it.isEncoder == isEncoder }
    }

    /**
     * Filters codec infos by software/hardware type
     */
    fun getCodecInfosByImplementation(isSoftware: Boolean): List<CodecInfo> {
        return getAllCodecInfos().filter { it.isSoftwareCodec == isSoftware }
    }

    /**
     * Filters codec infos by MIME type
     */
    fun getCodecInfosByMimeType(mimeType: String): List<CodecInfo> {
        return getAllCodecInfos().filter { codecInfo ->
            codecInfo.supportedTypes.any { it.equals(mimeType, ignoreCase = true) }
        }
    }
}