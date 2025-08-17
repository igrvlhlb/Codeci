package io.igrvlhlb.codeci.model

import android.media.MediaCodecInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class UIState(
    val codecType: String,
    val mediaType: String,
    val mimeType: String,
    val hwAccel: String,
    val mimeTypeList: List<String>,
    val codecsList: List<MediaCodecInfo>
){
    companion object {
        fun default() = UIState(
            codecType = CodecType.ALL.value,
            mediaType = MediaType.ALL.value,
            mimeType = "All",
            hwAccel = HWAccel.ALL.value,
            mimeTypeList = emptyList(),
            codecsList = emptyList(),
        )
    }
}

enum class MediaType(val value: String) {
    ALL("All"),
    AUDIO("Audio"),
    VIDEO("Video"),;
}

enum class CodecType(val value: String) {
    ALL("All"),
    ENCODER("Encoder"),
    DECODER("Decoder"),
}

enum class HWAccel(val value: String) {
    ALL("All"),
    YES("Yes"),
    NO("No"),
}