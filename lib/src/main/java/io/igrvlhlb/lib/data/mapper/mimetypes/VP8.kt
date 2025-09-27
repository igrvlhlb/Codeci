package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper


object VP8ProfileLevelMapper : ProfileLevelMapper {
    override val profiles = VP8ProfileEnum.entries
    override val levels = VP8LevelEnum.entries
}

enum class VP8ProfileEnum(override val profile: Int): ProfileEnum {
    VP8ProfileMain(MediaCodecInfo.CodecProfileLevel.VP8ProfileMain)
}
enum class VP8LevelEnum(override val level: Int): LevelEnum {
    VP8Level_Version0(MediaCodecInfo.CodecProfileLevel.VP8Level_Version0),
    VP8Level_Version1(MediaCodecInfo.CodecProfileLevel.VP8Level_Version1),
    VP8Level_Version2(MediaCodecInfo.CodecProfileLevel.VP8Level_Version2),
    VP8Level_Version3(MediaCodecInfo.CodecProfileLevel.VP8Level_Version3);
}