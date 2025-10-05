package io.igrvlhlb.lib

import android.media.MediaCodecInfo
import android.media.MediaCodecList
import io.igrvlhlb.lib.codeci.utils.isSoftwareCodec
import io.igrvlhlb.lib.data.CodecInfo
import io.igrvlhlb.lib.data.extractor.CodecInfoExtractor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.util.stream.Stream

/**
 * Repository for managing codec information extraction, caching, and filtering
 */
class CodecInfoRepository {

    private val codecInfoExtractor = CodecInfoExtractor()

    val allCodecsInfoInternal by lazy {
        val allCodecs = MediaCodecList(MediaCodecList.ALL_CODECS).codecInfos
        val comparator = CodecPairComparator()
        sortedSetOf(comparator, *allCodecs)
    }

    val regularCodecsInfoInternal by lazy {
        val regularCodecs = MediaCodecList(MediaCodecList.REGULAR_CODECS).codecInfos
        val comparator = CodecPairComparator()
        sortedSetOf(comparator, *regularCodecs)
    }

    val allCodecsInfo by lazy {
        allCodecsInfoInternal.map { codecInfoExtractor.extractCodecInfo(it) }
    }

    val regularCodecsInfo by lazy {
        regularCodecsInfoInternal.map { codecInfoExtractor.extractCodecInfo(it) }
    }

    /**
     * Gets structured codec information for a specific MediaCodecInfo
     * Results are cached to avoid re-extraction
     */
    fun getCodecInfo(mediaCodecInfo: MediaCodecInfo): CodecInfo {
        return allCodecsInfo.find{ it.name == mediaCodecInfo.name } ?: codecInfoExtractor.extractCodecInfo(mediaCodecInfo)
    }

    /**
     * Gets filtered list of MediaCodecInfo based on multiple criteria
     */
    fun getFilteredCodecs(
        codecType: CodecType,
        mediaType: MediaType,
        hwAccel: CodecImplementation,
        mimeType: String = ""
    ): Collection<MediaCodecInfo> {
        return allCodecsInfoInternal
            .filterByCodecType(codecType)
            .filterByMediaType(mediaType)
            .filterByAcceleration(hwAccel)
            .filterByMimeType(mimeType)
    }

    /**
     * Gets available MIME types for filtered codecs
     */
    fun getAvailableMimeTypes(
        codecType: CodecType,
        mediaType: MediaType,
        hwAccel: CodecImplementation
    ): Collection<String> {
        return allCodecsInfoInternal
            .filterByCodecType(codecType)
            .filterByMediaType(mediaType)
            .filterByAcceleration(hwAccel)
            .getMimeTypeList()
    }

    /**
     * Filters codec infos by encoder/decoder type
     */
    fun getCodecInfosByType(isEncoder: Boolean): List<CodecInfo> {
        return allCodecsInfo.filter { it.isEncoder == isEncoder }
    }

    /**
     * Filters codec infos by software/hardware type
     */
    fun getCodecInfosByImplementation(isSoftware: Boolean): List<CodecInfo> {
        return allCodecsInfo.filter { it.isSoftwareCodec == isSoftware }
    }

    /**
     * Filters codec infos by MIME type
     */
    fun getCodecInfosByMimeType(mimeType: String): List<CodecInfo> {
        return allCodecsInfo.filter { codecInfo ->
            codecInfo.supportedTypes.any { it.contains(mimeType, ignoreCase = true) }
        }
    }

    // Extension functions for filtering MediaCodecInfo collections
    private fun Collection<MediaCodecInfo>.filterByCodecType(codecType: CodecType) =
        when (codecType) {
            CodecType.ALL -> this
            CodecType.ENCODER -> filter { it.isEncoder }
            CodecType.DECODER -> filter { !it.isEncoder }
        }

    private fun Collection<MediaCodecInfo>.filterByMediaType(mediaType: MediaType) =
        when (mediaType) {
            MediaType.ALL -> this
            MediaType.AUDIO -> filter { it.supportedTypes.any { it.startsWith("audio/") } }
            MediaType.VIDEO -> filter { it.supportedTypes.any { it.startsWith("video/") } }
        }

    private fun Collection<MediaCodecInfo>.filterByAcceleration(hwAccel: CodecImplementation) =
        when (hwAccel) {
            CodecImplementation.ALL -> this
            CodecImplementation.HARDWARE -> filter { !it.isSoftwareCodec() }
            CodecImplementation.SOFTWARE -> filter { it.isSoftwareCodec() }
        }

    private fun Collection<MediaCodecInfo>.filterByMimeType(mimeType: String) =
        if (mimeType.isNotEmpty())
            filter { it.supportedTypes.any { it.endsWith(mimeType) } }.toList()
        else this

    private fun Collection<MediaCodecInfo>.getMimeTypeList() =
        flatMap { it.supportedTypes.map { it.split('/', '.').last() } }.distinct()

    private class CodecPairComparator: Comparator<MediaCodecInfo> {
        override fun compare(
            o1: MediaCodecInfo,
            o2: MediaCodecInfo
        ): Int {
            val typeCmp = compareType(o1, o2)
            return if (typeCmp != 0) typeCmp else o1.name.compareTo(o2.name)
        }

        private fun compareType(t1: MediaCodecInfo, t2: MediaCodecInfo): Int =
            t1.supportedTypes[0].compareTo(t2.supportedTypes[0])
    }

    enum class MediaType {
        ALL,
        AUDIO,
        VIDEO;
    }

    enum class CodecType {
        ALL,
        ENCODER,
        DECODER,
    }

    enum class CodecImplementation {
        ALL,
        HARDWARE,
        SOFTWARE,
    }
}