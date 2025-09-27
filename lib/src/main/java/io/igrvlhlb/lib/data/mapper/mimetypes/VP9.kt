package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.sdkAtLeastProfileLevel

object VP9ProfileLevelMapper : ProfileLevelMapper {
    override val profiles = VP9ProfileEnum.entries
    override val levels = VP9LevelEnum.entries
}

enum class VP9ProfileEnum(override val profile: Int): ProfileEnum {
    VP9Profile0(MediaCodecInfo.CodecProfileLevel.VP9Profile0),
    VP9Profile1(MediaCodecInfo.CodecProfileLevel.VP9Profile1),
    VP9Profile2(MediaCodecInfo.CodecProfileLevel.VP9Profile2),
    VP9Profile3(MediaCodecInfo.CodecProfileLevel.VP9Profile3),
    VP9Profile2HDR(MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR),
    VP9Profile3HDR(MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR),
    VP9Profile2HDR10Plus(sdkAtLeastProfileLevel(29) { MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR10Plus }),
    VP9Profile3HDR10Plus(sdkAtLeastProfileLevel(29) { MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR10Plus }),
}

enum class VP9LevelEnum(override val level: Int): LevelEnum {
    VP9Level1(MediaCodecInfo.CodecProfileLevel.VP9Level1),
    VP9Level11(MediaCodecInfo.CodecProfileLevel.VP9Level11),
    VP9Level2(MediaCodecInfo.CodecProfileLevel.VP9Level2),
    VP9Level21(MediaCodecInfo.CodecProfileLevel.VP9Level21),
    VP9Level3(MediaCodecInfo.CodecProfileLevel.VP9Level3),
    VP9Level31(MediaCodecInfo.CodecProfileLevel.VP9Level31),
    VP9Level4(MediaCodecInfo.CodecProfileLevel.VP9Level4),
    VP9Level41(MediaCodecInfo.CodecProfileLevel.VP9Level41),
    VP9Level5(MediaCodecInfo.CodecProfileLevel.VP9Level5),
    VP9Level51(MediaCodecInfo.CodecProfileLevel.VP9Level51),
    VP9Level52(MediaCodecInfo.CodecProfileLevel.VP9Level52),
    VP9Level6(MediaCodecInfo.CodecProfileLevel.VP9Level6),
    VP9Level61(MediaCodecInfo.CodecProfileLevel.VP9Level61),
    VP9Level62(MediaCodecInfo.CodecProfileLevel.VP9Level62),
}