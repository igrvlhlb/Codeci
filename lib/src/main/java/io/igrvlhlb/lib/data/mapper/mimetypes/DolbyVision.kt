package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.sdkAtLeastProfileLevel

object DolbyVisionProfileLevelMapper : ProfileLevelMapper {
    override val profiles = DolbyVisionProfileEnum.entries
    override val levels = DolbyVisionLevelEnum.entries
}

enum class DolbyVisionProfileEnum(override val profile: Int): ProfileEnum {
    DolbyVisionProfileDvavPer(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavPer),
    DolbyVisionProfileDvavPen(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavPen),
    DolbyVisionProfileDvheDer(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDer),
    DolbyVisionProfileDvheDen(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDen),
    DolbyVisionProfileDvheDtr(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDtr),
    DolbyVisionProfileDvheStn(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheStn),
    DolbyVisionProfileDvheDth(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDth),
    DolbyVisionProfileDvheDtb(MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDtb),
    DolbyVisionProfileDvheSt(sdkAtLeastProfileLevel(27) { MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheSt }),
    DolbyVisionProfileDvavSe(sdkAtLeastProfileLevel(27) { MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavSe }),
    DolbyVisionProfileDvav110(sdkAtLeastProfileLevel(30) { MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvav110 }),
}

enum class DolbyVisionLevelEnum(override val level: Int): LevelEnum {
    DolbyVisionLevelHd24(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelHd24),
    DolbyVisionLevelHd30(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelHd30),
    DolbyVisionLevelFhd24(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd24),
    DolbyVisionLevelFhd30(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd30),
    DolbyVisionLevelFhd60(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd60),
    DolbyVisionLevelUhd24(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd24),
    DolbyVisionLevelUhd30(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd30),
    DolbyVisionLevelUhd48(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd48),
    DolbyVisionLevelUhd60(MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd60),
    DolbyVisionLevelUhd120(sdkAtLeastProfileLevel(33) { MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd120 }),
    DolbyVisionLevel8k30(sdkAtLeastProfileLevel(33) { MediaCodecInfo.CodecProfileLevel.DolbyVisionLevel8k30 }),
    DolbyVisionLevel8k60(sdkAtLeastProfileLevel(33) { MediaCodecInfo.CodecProfileLevel.DolbyVisionLevel8k60 }),
}