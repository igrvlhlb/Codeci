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

object AVCProfileLevelMapper : ProfileLevelMapper {
    override val profiles = AVCProfileEnum.entries
    override val levels = AVCLevelEnum.entries
}

enum class AVCProfileEnum(override val profile: Int): ProfileEnum {
    AVCProfileBaseline(MediaCodecInfo.CodecProfileLevel.AVCProfileBaseline),
    AVCProfileMain(MediaCodecInfo.CodecProfileLevel.AVCProfileMain),
    AVCProfileExtended(MediaCodecInfo.CodecProfileLevel.AVCProfileExtended),
    AVCProfileHigh(MediaCodecInfo.CodecProfileLevel.AVCProfileHigh),
    AVCProfileHigh10(MediaCodecInfo.CodecProfileLevel.AVCProfileHigh10),
    AVCProfileHigh422(MediaCodecInfo.CodecProfileLevel.AVCProfileHigh422),
    AVCProfileHigh444(MediaCodecInfo.CodecProfileLevel.AVCProfileHigh444),
    AVCProfileConstrainedBaseline(sdkAtLeastProfileLevel(27) { MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedBaseline }),
    AVCProfileConstrainedHigh(sdkAtLeastProfileLevel(27) { MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedHigh }),
}

enum class AVCLevelEnum(override val level: Int): LevelEnum {
    AVCLevel1(MediaCodecInfo.CodecProfileLevel.AVCLevel1),
    AVCLevel11(MediaCodecInfo.CodecProfileLevel.AVCLevel11),
    AVCLevel12(MediaCodecInfo.CodecProfileLevel.AVCLevel12),
    AVCLevel13(MediaCodecInfo.CodecProfileLevel.AVCLevel13),
    AVCLevel1b(MediaCodecInfo.CodecProfileLevel.AVCLevel1b),
    AVCLevel2(MediaCodecInfo.CodecProfileLevel.AVCLevel2),
    AVCLevel21(MediaCodecInfo.CodecProfileLevel.AVCLevel21),
    AVCLevel22(MediaCodecInfo.CodecProfileLevel.AVCLevel22),
    AVCLevel3(MediaCodecInfo.CodecProfileLevel.AVCLevel3),
    AVCLevel31(MediaCodecInfo.CodecProfileLevel.AVCLevel31),
    AVCLevel32(MediaCodecInfo.CodecProfileLevel.AVCLevel32),
    AVCLevel4(MediaCodecInfo.CodecProfileLevel.AVCLevel4),
    AVCLevel41(MediaCodecInfo.CodecProfileLevel.AVCLevel41),
    AVCLevel42(MediaCodecInfo.CodecProfileLevel.AVCLevel42),
    AVCLevel5(MediaCodecInfo.CodecProfileLevel.AVCLevel5),
    AVCLevel51(MediaCodecInfo.CodecProfileLevel.AVCLevel51),
    AVCLevel52(MediaCodecInfo.CodecProfileLevel.AVCLevel52),
    AVCLevel6(sdkAtLeastProfileLevel(29) { MediaCodecInfo.CodecProfileLevel.AVCLevel6 }),
    AVCLevel61(sdkAtLeastProfileLevel(29) { MediaCodecInfo.CodecProfileLevel.AVCLevel61 }),
    AVCLevel62(sdkAtLeastProfileLevel(29) { MediaCodecInfo.CodecProfileLevel.AVCLevel62 }),
}