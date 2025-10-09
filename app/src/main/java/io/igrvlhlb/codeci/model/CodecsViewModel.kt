package com.example.codeci.ui.main

import android.media.MediaCodecInfo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.igrvlhlb.codeci.model.CodecType
import io.igrvlhlb.codeci.model.HWAccel
import io.igrvlhlb.codeci.model.MediaType
import io.igrvlhlb.codeci.model.UIState
import io.igrvlhlb.lib.CodecInfoRepository
import io.igrvlhlb.lib.data.CodecInfo

class CodecsViewModel : ViewModel() {

    private val codecInfoRepository = CodecInfoRepository()

    val allCodecsSet: Set<MediaCodecInfo>
        get() = codecInfoRepository.allCodecsInfoInternal


    private var _state: MutableState<UIState> = mutableStateOf(UIState.default())
    val state: State<UIState>
        get() = _state

    lateinit var selectedCodec: MediaCodecInfo

    val codecInfo: CodecInfo
        get() = codecInfoRepository.getCodecInfo(selectedCodec)

    init {
        updateState(UIState.default())
    }

    fun updateState(newState: UIState) {
        val codecTypeState = codecTypeStateToRepository(newState.codecType)
        val mediaTypeState = mediaTypeStateToRepository(newState.mediaType)
        val hwAccelState = hwAccelStateToRepository(newState.hwAccel)

        val mimeTypes = codecInfoRepository.getAvailableMimeTypes(
            codecType = codecTypeState,
            mediaType = mediaTypeState,
            hwAccel = hwAccelState
        )

        val codecsList = codecInfoRepository.getFilteredCodecs(
            codecType = codecTypeState,
            mediaType = mediaTypeState,
            hwAccel = hwAccelState,
            mimeType = newState.mimeType.takeIf { it != "All" } ?: ""
        )

        val actualState = newState.copy(
            codecsList = codecsList.toList(),
            mimeTypeList = mimeTypes.toList()
        )

        _state.value = actualState
    }

    fun getCodecsInfoJson() = codecInfoRepository.serializeToJson()

    private fun codecTypeStateToRepository(value: String): CodecInfoRepository.CodecType {
        return when(value) {
            CodecType.ALL.value -> CodecInfoRepository.CodecType.ALL
            CodecType.ENCODER.value -> CodecInfoRepository.CodecType.ENCODER
            CodecType.DECODER.value -> CodecInfoRepository.CodecType.DECODER
            else -> CodecInfoRepository.CodecType.ALL
        }
    }

    private fun mediaTypeStateToRepository(value: String): CodecInfoRepository.MediaType {
        return when(value) {
            MediaType.ALL.value -> CodecInfoRepository.MediaType.ALL
            MediaType.AUDIO.value -> CodecInfoRepository.MediaType.AUDIO
            MediaType.VIDEO.value -> CodecInfoRepository.MediaType.VIDEO
            else -> CodecInfoRepository.MediaType.ALL
        }
    }

    private fun hwAccelStateToRepository(value: String): CodecInfoRepository.CodecImplementation {
        return when(value) {
            HWAccel.ALL.value -> CodecInfoRepository.CodecImplementation.ALL
            HWAccel.YES.value -> CodecInfoRepository.CodecImplementation.HARDWARE
            HWAccel.NO.value -> CodecInfoRepository.CodecImplementation.SOFTWARE
            else -> CodecInfoRepository.CodecImplementation.ALL
        }
    }


}