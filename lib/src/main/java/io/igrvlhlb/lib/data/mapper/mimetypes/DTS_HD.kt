package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import androidx.annotation.RequiresApi
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper


object DTS_HDProfileLevelMapper : ProfileLevelMapper {
    override val profiles = DTS_HDProfileEnum.entries
}

@RequiresApi(34)
enum class DTS_HDProfileEnum(override val profile: Int): ProfileEnum {
    DTS_HDProfileHRA(MediaCodecInfo.CodecProfileLevel.DTS_HDProfileHRA),
    DTS_HDProfileLBR(MediaCodecInfo.CodecProfileLevel.DTS_HDProfileLBR),
    DTS_HDProfileMA(MediaCodecInfo.CodecProfileLevel.DTS_HDProfileMA),
}
