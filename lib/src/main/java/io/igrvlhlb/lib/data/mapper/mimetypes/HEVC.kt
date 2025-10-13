package io.igrvlhlb.lib.data.mapper.mimetypes
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import android.media.MediaCodecInfo
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.sdkAtLeastProfileLevel

object HEVCProfileLevelMapper : ProfileLevelMapper {
    override val profiles = HEVCProfileEnum.entries
    override val levels = HEVCLevelEnum.entries
}

enum class HEVCProfileEnum(override val profile: Int): ProfileEnum {
    HEVCProfileMain(MediaCodecInfo.CodecProfileLevel.HEVCProfileMain),
    HEVCProfileMain10(MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10),
    HEVCProfileMainStill(sdkAtLeastProfileLevel(28) { MediaCodecInfo.CodecProfileLevel.HEVCProfileMainStill }),
    HEVCProfileMain10HDR10(MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10),
    HEVCProfileMain10HDR10Plus(sdkAtLeastProfileLevel(29) { MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10Plus }),
}

enum class HEVCLevelEnum(override val level: Int): LevelEnum {
    HEVCHighTierLevel1(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel1),
    HEVCMainTierLevel1(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel1),
    HEVCMainTierLevel2(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel2),
    HEVCHighTierLevel2(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel2),
    HEVCMainTierLevel21(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel21),
    HEVCHighTierLevel21(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel21),
    HEVCMainTierLevel3(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel3),
    HEVCHighTierLevel3(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel3),
    HEVCMainTierLevel31(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel31),
    HEVCHighTierLevel31(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel31),
    HEVCMainTierLevel4(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel4),
    HEVCHighTierLevel4(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel4),
    HEVCMainTierLevel41(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel41),
    HEVCHighTierLevel41(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel41),
    HEVCMainTierLevel5(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel5),
    HEVCHighTierLevel5(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel5),
    HEVCMainTierLevel51(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel51),
    HEVCHighTierLevel51(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel51),
    HEVCMainTierLevel52(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel52),
    HEVCHighTierLevel52(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel52),
    HEVCMainTierLevel6(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel6),
    HEVCHighTierLevel6(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel6),
    HEVCMainTierLevel61(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel61),
    HEVCHighTierLevel61(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel61),
    HEVCMainTierLevel62(MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel62),
    HEVCHighTierLevel62(MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel62),
}