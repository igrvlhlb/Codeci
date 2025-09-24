package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import androidx.annotation.RequiresApi
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object AV1ProfileLevelMapper : ProfileLevelMapper {
    override val profiles = AV1ProfileEnum.entries
    override val levels = AV1LevelEnum.entries
}

@RequiresApi(29)
enum class AV1ProfileEnum(override val profile: Int): ProfileEnum {
    AV1ProfileMain8(MediaCodecInfo.CodecProfileLevel.AV1ProfileMain8),
    AV1ProfileMain10(MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10),
    AV1ProfileMain10HDR10(MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10),
    AV1ProfileMain10HDR10Plus(MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10Plus),
}

@RequiresApi(29)
enum class AV1LevelEnum(override val level: Int): LevelEnum {
    AV1Level2(MediaCodecInfo.CodecProfileLevel.AV1Level2),
    AV1Level21(MediaCodecInfo.CodecProfileLevel.AV1Level21),
    AV1Level22(MediaCodecInfo.CodecProfileLevel.AV1Level22),
    AV1Level23(MediaCodecInfo.CodecProfileLevel.AV1Level23),
    AV1Level3(MediaCodecInfo.CodecProfileLevel.AV1Level3),
    AV1Level31(MediaCodecInfo.CodecProfileLevel.AV1Level31),
    AV1Level32(MediaCodecInfo.CodecProfileLevel.AV1Level32),
    AV1Level33(MediaCodecInfo.CodecProfileLevel.AV1Level33),
    AV1Level4(MediaCodecInfo.CodecProfileLevel.AV1Level4),
    AV1Level41(MediaCodecInfo.CodecProfileLevel.AV1Level41),
    AV1Level42(MediaCodecInfo.CodecProfileLevel.AV1Level42),
    AV1Level43(MediaCodecInfo.CodecProfileLevel.AV1Level43),
    AV1Level5(MediaCodecInfo.CodecProfileLevel.AV1Level5),
    AV1Level51(MediaCodecInfo.CodecProfileLevel.AV1Level51),
    AV1Level52(MediaCodecInfo.CodecProfileLevel.AV1Level52),
    AV1Level53(MediaCodecInfo.CodecProfileLevel.AV1Level53),
    AV1Level6(MediaCodecInfo.CodecProfileLevel.AV1Level6),
    AV1Level61(MediaCodecInfo.CodecProfileLevel.AV1Level61),
    AV1Level62(MediaCodecInfo.CodecProfileLevel.AV1Level62),
    AV1Level63(MediaCodecInfo.CodecProfileLevel.AV1Level63),
    AV1Level7(MediaCodecInfo.CodecProfileLevel.AV1Level7),
    AV1Level71(MediaCodecInfo.CodecProfileLevel.AV1Level71),
    AV1Level72(MediaCodecInfo.CodecProfileLevel.AV1Level72),
    AV1Level73(MediaCodecInfo.CodecProfileLevel.AV1Level73),
}