package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object MPEG4ProfileLevelMapper : ProfileLevelMapper {
    override val profiles = MPEG4ProfileEnum.entries
    override val levels = MPEG4LevelEnum.entries
}

enum class MPEG4ProfileEnum(override val profile: Int): ProfileEnum {
    MPEG4ProfileSimple(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimple),
    MPEG4ProfileSimpleScalable(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleScalable),
    MPEG4ProfileCore(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileCore),
    MPEG4ProfileMain(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileMain),
    MPEG4ProfileNbit(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileNbit),
    MPEG4ProfileScalableTexture(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileScalableTexture),
    MPEG4ProfileSimpleFace(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleFace),
    MPEG4ProfileSimpleFBA(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleFBA),
    MPEG4ProfileBasicAnimated(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileBasicAnimated),
    MPEG4ProfileHybrid(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileHybrid),
    MPEG4ProfileAdvancedRealTime(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedRealTime),
    MPEG4ProfileCoreScalable(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileCoreScalable),
    MPEG4ProfileAdvancedCoding(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedCoding),
    MPEG4ProfileAdvancedCore(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedCore),
    MPEG4ProfileAdvancedScalable(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedScalable),
    MPEG4ProfileAdvancedSimple(MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedSimple),
}

enum class MPEG4LevelEnum(override val level: Int): LevelEnum {
    MPEG4Level0(MediaCodecInfo.CodecProfileLevel.MPEG4Level0),
    MPEG4Level0b(MediaCodecInfo.CodecProfileLevel.MPEG4Level0b),
    MPEG4Level1(MediaCodecInfo.CodecProfileLevel.MPEG4Level1),
    MPEG4Level2(MediaCodecInfo.CodecProfileLevel.MPEG4Level2),
    MPEG4Level3(MediaCodecInfo.CodecProfileLevel.MPEG4Level3),
    MPEG4Level3b(MediaCodecInfo.CodecProfileLevel.MPEG4Level3b),
    MPEG4Level4(MediaCodecInfo.CodecProfileLevel.MPEG4Level4),
    MPEG4Level4a(MediaCodecInfo.CodecProfileLevel.MPEG4Level4a),
    MPEG4Level5(MediaCodecInfo.CodecProfileLevel.MPEG4Level5),
    MPEG4Level6(MediaCodecInfo.CodecProfileLevel.MPEG4Level6),
}