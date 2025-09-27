package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import androidx.annotation.RequiresApi
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object AC4ProfileLevelMapper : ProfileLevelMapper {
    override val profiles = AC4ProfileEnum.entries
    override val levels = AC4LevelEnum.entries
}


@RequiresApi(34)
enum class AC4ProfileEnum(override val profile: Int): ProfileEnum {
    AC4Profile00(MediaCodecInfo.CodecProfileLevel.AC4Profile00),
    AC4Profile10(MediaCodecInfo.CodecProfileLevel.AC4Profile10),
    AC4Profile11(MediaCodecInfo.CodecProfileLevel.AC4Profile11),
    AC4Profile21(MediaCodecInfo.CodecProfileLevel.AC4Profile21),
    AC4Profile22(MediaCodecInfo.CodecProfileLevel.AC4Profile22),
}

@RequiresApi(34)
enum class AC4LevelEnum(override val level: Int): LevelEnum {
    AC4Level0(MediaCodecInfo.CodecProfileLevel.AC4Level0),
    AC4Level1(MediaCodecInfo.CodecProfileLevel.AC4Level1),
    AC4Level2(MediaCodecInfo.CodecProfileLevel.AC4Level2),
    AC4Level3(MediaCodecInfo.CodecProfileLevel.AC4Level3),
    AC4Level4(MediaCodecInfo.CodecProfileLevel.AC4Level4),
}
