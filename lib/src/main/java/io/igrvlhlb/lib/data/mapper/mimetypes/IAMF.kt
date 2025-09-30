package io.igrvlhlb.lib.data.mapper.mimetypes

import android.media.MediaCodecInfo
import androidx.annotation.RequiresApi
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object IAMFProfileLevelMapper : ProfileLevelMapper {
    override val profiles = IAMFProfileEnum.entries
    override val levels = IAMFLevelEnum.entries
}


@RequiresApi(36)
enum class IAMFProfileEnum(override val profile: Int): ProfileEnum {
    IAMFProfileSimpleOpus(MediaCodecInfo.CodecProfileLevel.IAMFProfileSimpleOpus),
    IAMFProfileSimpleAac(MediaCodecInfo.CodecProfileLevel.IAMFProfileSimpleAac),
    IAMFProfileSimpleFlac(MediaCodecInfo.CodecProfileLevel.IAMFProfileSimpleFlac),
    IAMFProfileSimplePcm(MediaCodecInfo.CodecProfileLevel.IAMFProfileSimplePcm),
    IAMFProfileBaseOpus(MediaCodecInfo.CodecProfileLevel.IAMFProfileBaseOpus),
    IAMFProfileBaseAac(MediaCodecInfo.CodecProfileLevel.IAMFProfileBaseAac),
    IAMFProfileBaseFlac(MediaCodecInfo.CodecProfileLevel.IAMFProfileBaseFlac),
    IAMFProfileBasePcm(MediaCodecInfo.CodecProfileLevel.IAMFProfileBasePcm),
    IAMFProfileBaseEnhancedOpus(MediaCodecInfo.CodecProfileLevel.IAMFProfileBaseEnhancedOpus),
    IAMFProfileBaseEnhancedAac(MediaCodecInfo.CodecProfileLevel.IAMFProfileBaseEnhancedAac),
    IAMFProfileBaseEnhancedFlac(MediaCodecInfo.CodecProfileLevel.IAMFProfileBaseEnhancedFlac),
    IAMFProfileBaseEnhancedPcm(MediaCodecInfo.CodecProfileLevel.IAMFProfileBaseEnhancedPcm),
}

@RequiresApi(36)
enum class IAMFLevelEnum(override val level: Int): LevelEnum {
}