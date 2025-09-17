package com.example.codeci.ui.main

import android.media.MediaCodecInfo
import android.media.MediaCodecList
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.igrvlhlb.codeci.model.CodecType
import io.igrvlhlb.codeci.model.HWAccel
import io.igrvlhlb.codeci.model.MediaType
import io.igrvlhlb.codeci.model.UIState
import io.igrvlhlb.lib.codeci.utils.isSoftwareCodec
import io.igrvlhlb.lib.data.CodecInfo
import io.igrvlhlb.lib.data.extractor.CodecInfoExtractor

class CodecsViewModel : ViewModel() {

    lateinit var _allCodecSet: Set<MediaCodecInfo>
    val allCodecsSet: Set<MediaCodecInfo>
        get() = _allCodecSet

    lateinit var _regularCodecSet: Set<MediaCodecInfo>
    val regularCodecsSet: Set<MediaCodecInfo>
        get() = _regularCodecSet

    private var _state: MutableState<UIState> = mutableStateOf(UIState.default())
    val state: State<UIState>
        get() = _state

    lateinit var selectedCodec: MediaCodecInfo

    val codecInfo: CodecInfo
        get() = CodecInfoExtractor().extractCodecInfo(selectedCodec)

    init {
        getCodecsInfos()
        updateState(UIState.default())
    }

    fun updateState(newState: UIState) {
        val codecsList = allCodecsSet
            .filterByCodecType(newState.codecType)
            .filterByMediaType(newState.mediaType)
            .filterByAcceleration(newState.hwAccel)
        val mimeTypes = codecsList.getMimeTypeList()
        val actualState = newState.copy(
            codecsList = codecsList.filterByMimeType(newState.mimeType),
            mimeTypeList = mimeTypes
        )

        _state.value = actualState
    }

    fun Collection<MediaCodecInfo>.filterByCodecType(codecType: String) =
        when (codecType) {
            CodecType.ALL.value -> toList()
            CodecType.ENCODER.value -> filter { it.isEncoder }.toList()
            CodecType.DECODER.value -> filter { !it.isEncoder }.toList()
            else -> emptyList()
        }

    private fun Collection<MediaCodecInfo>.filterByMediaType(mediaType: String) =
        when (mediaType) {
            MediaType.ALL.value -> toList()
            MediaType.AUDIO.value -> filter { it.supportedTypes.any { it.startsWith("audio/") } }.toList()
            MediaType.VIDEO.value -> filter { it.supportedTypes.any { it.startsWith("video/") } }.toList()
            else -> emptyList()
        }

    private fun Collection<MediaCodecInfo>.filterByAcceleration(hwAccel: String) =
        when (hwAccel) {
            HWAccel.ALL.value -> toList()
            HWAccel.YES.value -> filter { !it.isSoftwareCodec() }.toList()
            HWAccel.NO.value -> filter { it.isSoftwareCodec() }.toList()
            else -> emptyList()
        }

    private fun Collection<MediaCodecInfo>.filterByMimeType(mimeType: String) =
        if (mimeType != "All")
            filter { it.supportedTypes.any { it.endsWith(mimeType) } }.toList()
        else toList()

    private fun Collection<MediaCodecInfo>.getMimeTypeList() =
        flatMap { it.supportedTypes.map { it.split('/', '.').last() } }.distinct()

    private fun getCodecsInfos() {
        val allCodecs = MediaCodecList(MediaCodecList.ALL_CODECS).codecInfos
        val regularCodecs = MediaCodecList(MediaCodecList.REGULAR_CODECS).codecInfos
        val comparator = CodecPairComparator()
        _allCodecSet = sortedSetOf(comparator, *allCodecs)
        _regularCodecSet = sortedSetOf(comparator, *regularCodecs)
    }
}

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