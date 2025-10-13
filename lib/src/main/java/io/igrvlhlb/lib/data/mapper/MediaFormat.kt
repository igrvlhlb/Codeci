package io.igrvlhlb.lib.data.mapper

import android.annotation.SuppressLint
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_AdaptivePlayback
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_DetachedSurface
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_DynamicColorAspects
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_DynamicTimestamp
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_EncodingStatistics
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_FrameParsing
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_HdrEditing
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_HlgEditing
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_IntraRefresh
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_LowLatency
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_MultipleFrames
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_PartialFrame
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_QpBounds
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_Roi
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_SecurePlayback
import android.media.MediaCodecInfo.CodecCapabilities.FEATURE_TunneledPlayback
import android.media.MediaFormat
import android.media.MediaFormat.KEY_AAC_DRC_ALBUM_MODE
import android.media.MediaFormat.KEY_AAC_DRC_ATTENUATION_FACTOR
import android.media.MediaFormat.KEY_AAC_DRC_BOOST_FACTOR
import android.media.MediaFormat.KEY_AAC_DRC_EFFECT_TYPE
import android.media.MediaFormat.KEY_AAC_DRC_HEAVY_COMPRESSION
import android.media.MediaFormat.KEY_AAC_DRC_OUTPUT_LOUDNESS
import android.media.MediaFormat.KEY_AAC_DRC_TARGET_REFERENCE_LEVEL
import android.media.MediaFormat.KEY_AAC_ENCODED_TARGET_LEVEL
import android.media.MediaFormat.KEY_AAC_MAX_OUTPUT_CHANNEL_COUNT
import android.media.MediaFormat.KEY_AAC_PROFILE
import android.media.MediaFormat.KEY_AAC_SBR_MODE
import android.media.MediaFormat.KEY_BIT_RATE
import android.media.MediaFormat.KEY_BUFFER_BATCH_THRESHOLD_OUTPUT_SIZE
import android.media.MediaFormat.KEY_CAPTION_SERVICE_NUMBER
import android.media.MediaFormat.KEY_CAPTURE_RATE
import android.media.MediaFormat.KEY_CHANNEL_COUNT
import android.media.MediaFormat.KEY_CHANNEL_MASK
import android.media.MediaFormat.KEY_CODECS_STRING
import android.media.MediaFormat.KEY_COLOR_FORMAT
import android.media.MediaFormat.KEY_DURATION
import android.media.MediaFormat.KEY_ENCODER_DELAY
import android.media.MediaFormat.KEY_ENCODER_PADDING
import android.media.MediaFormat.KEY_FLAC_COMPRESSION_LEVEL
import android.media.MediaFormat.KEY_FRAME_RATE
import android.media.MediaFormat.KEY_GRID_COLUMNS
import android.media.MediaFormat.KEY_GRID_ROWS
import android.media.MediaFormat.KEY_HEIGHT
import android.media.MediaFormat.KEY_INTRA_REFRESH_PERIOD
import android.media.MediaFormat.KEY_IS_ADTS
import android.media.MediaFormat.KEY_I_FRAME_INTERVAL
import android.media.MediaFormat.KEY_LANGUAGE
import android.media.MediaFormat.KEY_LATENCY
import android.media.MediaFormat.KEY_MAX_HEIGHT
import android.media.MediaFormat.KEY_MAX_INPUT_SIZE
import android.media.MediaFormat.KEY_MAX_WIDTH
import android.media.MediaFormat.KEY_MIME
import android.media.MediaFormat.KEY_MPEGH_COMPATIBLE_SETS
import android.media.MediaFormat.KEY_MPEGH_PROFILE_LEVEL_INDICATION
import android.media.MediaFormat.KEY_MPEGH_REFERENCE_CHANNEL_LAYOUT
import android.media.MediaFormat.KEY_PCM_ENCODING
import android.media.MediaFormat.KEY_PIXEL_ASPECT_RATIO_HEIGHT
import android.media.MediaFormat.KEY_PIXEL_ASPECT_RATIO_WIDTH
import android.media.MediaFormat.KEY_PUSH_BLANK_BUFFERS_ON_STOP
import android.media.MediaFormat.KEY_REPEAT_PREVIOUS_FRAME_AFTER
import android.media.MediaFormat.KEY_SAMPLE_RATE
import android.media.MediaFormat.KEY_TEMPORAL_LAYERING
import android.media.MediaFormat.KEY_TILE_HEIGHT
import android.media.MediaFormat.KEY_TILE_WIDTH
import android.media.MediaFormat.KEY_WIDTH
import android.media.MediaFormat.TYPE_BYTE_BUFFER
import android.media.MediaFormat.TYPE_FLOAT
import android.media.MediaFormat.TYPE_INTEGER
import android.media.MediaFormat.TYPE_LONG
import android.media.MediaFormat.TYPE_STRING
import androidx.annotation.RequiresApi
import io.igrvlhlb.lib.utils.fragile
import io.igrvlhlb.lib.utils.sdkAtLeast
import io.igrvlhlb.lib.utils.sdkAtLeastOrNull
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer
import java.nio.ByteBuffer

@Serializable(with = MediaFormatSerializer::class)
class MediaFormat(private val mediaFormat: MediaFormat) {

    override fun toString(): String {
        return getMappings().map {
            "${it.key}=${it.value}"
        }.joinToString(",", "{", "}")
    }

    fun getMappings(): Map<String, MediaFormatValue<*>> {
        val keys = getKeys()
        val features = getFeatures()
        val mapping = (keys + features).mapNotNull { key ->
            val mediaFormatValue = if (sdkAtLeast(29)) {
                MediaFormatValue.extractValue(mediaFormat, key)
            } else {
                ALL_KEYS_MAP[key]?.let { type -> MediaFormatValue.extractValue(mediaFormat, key, type) }
            }
            if (mediaFormatValue != null) {
                key to mediaFormatValue
            } else null
        }.toMap()
        return mapping
    }

    private fun getKeys(): Set<String> {
        return sdkAtLeastOrElse(29, ALL_KEYS) { mediaFormat.keys.toSet() }
    }

    private fun getFeatures(): Set<String> {
        return sdkAtLeastOrElse(29, FEATURE_KEYS) { mediaFormat.features.map { "$FEATURE_PREFIX$it" }.toSet() }
    }

    companion object {

        private val FEATURE_KEYS = FEATURES_MAP.keys.toSet()

        private val ALL_KEYS_MAP = (COMMON_KEYS_MAP + VIDEO_KEYS_MAP + AUDIO_KEYS_MAP + SUBTITLE_KEYS_MAP + IMAGE_KEYS_MAP)
        private val ALL_KEYS = ALL_KEYS_MAP.keys
    }
}

enum class MediaFormatTypes {
    STRING,
    INTEGER,
    LONG,
    FLOAT,
    BYTE_BUFFER;

    companion object {
        @RequiresApi(29)
        fun fromMediaFormatType(type: Int): MediaFormatTypes? = when (type) {
            TYPE_STRING -> STRING
            TYPE_INTEGER -> INTEGER
            TYPE_LONG -> LONG
            TYPE_FLOAT -> FLOAT
            TYPE_BYTE_BUFFER -> BYTE_BUFFER
            else -> null
        }
    }
}

sealed class MediaFormatValue<T: Any>(val value: T) {
    class StringValue(value: String): MediaFormatValue<String>(value) {
        override fun toString(): String = value
    }
    class IntValue(value: Int): MediaFormatValue<Int>(value) {
        override fun toString(): String = value.toString()
    }
    class LongValue(value: Long): MediaFormatValue<Long>(value) {
        override fun toString(): String = value.toString()
    }
    class FloatValue(value: Float): MediaFormatValue<Float>(value) {
        override fun toString(): String = value.toString()
    }
    class ByteBufferValue(value: ByteBuffer): MediaFormatValue<ByteBuffer>(value) {
        override fun toString(): String = "[${value.asCharBuffer()}]"
    }

    companion object {
        @RequiresApi(29)
        fun extractValue(mediaFormat: MediaFormat, key: String): MediaFormatValue<*>? {
            val type = mediaFormat.getValueTypeForKey(key)
            return MediaFormatTypes.fromMediaFormatType(type)?.let {
                extractValue(mediaFormat, key, it)
            }
        }
        fun extractValue(mediaFormat: MediaFormat, key: String, type: MediaFormatTypes): MediaFormatValue<*>? {
            if (!mediaFormat.containsKey(key)) return null
            return fragile {
                when (type) {
                    MediaFormatTypes.STRING -> {
                        StringValue(mediaFormat.getString(key)!!)
                    }

                    MediaFormatTypes.INTEGER -> {
                        IntValue(mediaFormat.getInteger(key))
                    }

                    MediaFormatTypes.LONG -> {
                        LongValue(mediaFormat.getLong(key))
                    }

                    MediaFormatTypes.FLOAT -> {
                        FloatValue(mediaFormat.getFloat(key))
                    }

                    MediaFormatTypes.BYTE_BUFFER -> {
                        ByteBufferValue(mediaFormat.getByteBuffer(key)!!)
                    }
                }
            }
        }
    }
}

private val COMMON_KEYS_MAP = listOfNotNull(
    KEY_MIME to MediaFormatTypes.STRING,
    KEY_MAX_INPUT_SIZE to MediaFormatTypes.INTEGER,
    KEY_BIT_RATE to MediaFormatTypes.INTEGER,
    KEY_DURATION to MediaFormatTypes.LONG,
    sdkAtLeastOrNull(30) { KEY_CODECS_STRING to MediaFormatTypes.STRING  },
    sdkAtLeastOrNull(30) { KEY_PIXEL_ASPECT_RATIO_WIDTH to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(30) { KEY_PIXEL_ASPECT_RATIO_HEIGHT to MediaFormatTypes.INTEGER },
).toMap()

private val VIDEO_KEYS_MAP = listOfNotNull(
    KEY_WIDTH to MediaFormatTypes.INTEGER,
    KEY_HEIGHT to MediaFormatTypes.INTEGER,
    KEY_COLOR_FORMAT to MediaFormatTypes.INTEGER,
    KEY_FRAME_RATE to MediaFormatTypes.FLOAT,
    KEY_CAPTURE_RATE to MediaFormatTypes.INTEGER,
    KEY_I_FRAME_INTERVAL to MediaFormatTypes.FLOAT,
    KEY_INTRA_REFRESH_PERIOD to MediaFormatTypes.INTEGER,
    KEY_MAX_WIDTH to MediaFormatTypes.FLOAT,
    KEY_MAX_HEIGHT to MediaFormatTypes.INTEGER,
    KEY_REPEAT_PREVIOUS_FRAME_AFTER to MediaFormatTypes.LONG,
    KEY_PUSH_BLANK_BUFFERS_ON_STOP to MediaFormatTypes.INTEGER,
    KEY_TEMPORAL_LAYERING to MediaFormatTypes.STRING,
    sdkAtLeastOrNull(26) { KEY_LATENCY to MediaFormatTypes.INTEGER },
).toMap()

private val AUDIO_KEYS_MAP = listOfNotNull(
    KEY_CHANNEL_COUNT to MediaFormatTypes.INTEGER,
    KEY_SAMPLE_RATE to MediaFormatTypes.INTEGER,
    KEY_PCM_ENCODING to MediaFormatTypes.INTEGER,
    KEY_IS_ADTS to MediaFormatTypes.INTEGER,
    KEY_AAC_PROFILE to MediaFormatTypes.INTEGER,
    KEY_AAC_SBR_MODE to MediaFormatTypes.INTEGER,
    KEY_AAC_DRC_TARGET_REFERENCE_LEVEL to MediaFormatTypes.INTEGER,
    KEY_AAC_ENCODED_TARGET_LEVEL to MediaFormatTypes.INTEGER,
    KEY_AAC_DRC_BOOST_FACTOR to MediaFormatTypes.INTEGER,
    KEY_AAC_DRC_ATTENUATION_FACTOR to MediaFormatTypes.INTEGER,
    KEY_AAC_DRC_HEAVY_COMPRESSION to MediaFormatTypes.INTEGER,
    KEY_AAC_MAX_OUTPUT_CHANNEL_COUNT to MediaFormatTypes.INTEGER,
    KEY_CHANNEL_MASK to MediaFormatTypes.INTEGER,
    KEY_FLAC_COMPRESSION_LEVEL to MediaFormatTypes.INTEGER,
//            KEY_MAX_BUFFER_BATCH_OUTPUT_SIZE, // This constant can't be found
    sdkAtLeastOrNull(28) { KEY_AAC_DRC_EFFECT_TYPE to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(30) { KEY_AAC_DRC_OUTPUT_LOUDNESS to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(30) { KEY_AAC_DRC_ALBUM_MODE to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(30) { KEY_ENCODER_DELAY to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(31) { KEY_ENCODER_PADDING to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(31) { KEY_MPEGH_PROFILE_LEVEL_INDICATION to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(31) { KEY_MPEGH_COMPATIBLE_SETS to MediaFormatTypes.BYTE_BUFFER },
    sdkAtLeastOrNull(31) { KEY_MPEGH_REFERENCE_CHANNEL_LAYOUT to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(35) { KEY_BUFFER_BATCH_THRESHOLD_OUTPUT_SIZE to MediaFormatTypes.INTEGER },
).toMap()


private val SUBTITLE_KEYS_MAP = listOfNotNull(
    KEY_MIME to MediaFormatTypes.STRING,
    KEY_LANGUAGE to MediaFormatTypes.STRING,
    sdkAtLeastOrNull(30) { KEY_CAPTION_SERVICE_NUMBER to MediaFormatTypes.INTEGER },
).toMap()

private val IMAGE_KEYS_MAP = listOfNotNull(
    KEY_MIME to MediaFormatTypes.STRING,
    KEY_WIDTH to MediaFormatTypes.INTEGER,
    KEY_HEIGHT to MediaFormatTypes.INTEGER,
    KEY_COLOR_FORMAT to MediaFormatTypes.INTEGER,
    sdkAtLeastOrNull(28) { KEY_TILE_WIDTH to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(28) { KEY_TILE_HEIGHT to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(28) { KEY_GRID_ROWS to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(28) { KEY_GRID_COLUMNS to MediaFormatTypes.INTEGER },
).toMap()

private val FEATURES_MAP = listOfNotNull(
    FEATURE_AdaptivePlayback to MediaFormatTypes.INTEGER,
    FEATURE_SecurePlayback to MediaFormatTypes.INTEGER,
    FEATURE_TunneledPlayback to MediaFormatTypes.INTEGER,
    sdkAtLeastOrNull(29) { FEATURE_DynamicTimestamp to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(29) { FEATURE_FrameParsing to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(29) { FEATURE_MultipleFrames to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(26) { FEATURE_PartialFrame to MediaFormatTypes.INTEGER },
    FEATURE_IntraRefresh to MediaFormatTypes.INTEGER,
    sdkAtLeastOrNull(30) { FEATURE_LowLatency to MediaFormatTypes.INTEGER },
//            FEATURE_SpecialCodec to MediaFormatTypes.INTEGER // This constant can't be found
    sdkAtLeastOrNull(31) { FEATURE_QpBounds to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(33) { FEATURE_EncodingStatistics to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(33) { FEATURE_HdrEditing to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(35) { FEATURE_HlgEditing to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(35) { FEATURE_DynamicColorAspects to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(35) { FEATURE_Roi to MediaFormatTypes.INTEGER },
    sdkAtLeastOrNull(35) { FEATURE_DetachedSurface to MediaFormatTypes.INTEGER },
).associate { "$FEATURE_PREFIX${it.first}" to it.second }

private const val FEATURE_PREFIX = "feature-"

object MediaFormatSerializer : KSerializer<io.igrvlhlb.lib.data.mapper.MediaFormat> {
    private val mapSerializer = serializer<Map<String, String>>()
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("MediaFormat")
    override fun serialize(
        encoder: Encoder,
        value: io.igrvlhlb.lib.data.mapper.MediaFormat
    ) {
        val data = value.getMappings().mapValues { it.value.toString() }
        encoder.encodeSerializableValue(mapSerializer, data)
    }

    override fun deserialize(decoder: Decoder): io.igrvlhlb.lib.data.mapper.MediaFormat {
        throw UnsupportedOperationException("Deserialization is not supported")
    }
}