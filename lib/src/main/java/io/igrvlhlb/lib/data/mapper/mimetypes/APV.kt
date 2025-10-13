package io.igrvlhlb.lib.data.mapper.mimetypes
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import android.media.MediaCodecInfo
import androidx.annotation.RequiresApi
import io.igrvlhlb.lib.data.mapper.LevelEnum
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object APVProfileLevelMapper : ProfileLevelMapper {
    override val profiles = APVProfileEnum.entries
    override val levels = APVLevelEnum.entries
}


@RequiresApi(36)
enum class APVProfileEnum(override val profile: Int): ProfileEnum {
    APVProfile422_10(MediaCodecInfo.CodecProfileLevel.APVProfile422_10),
    APVProfile422_10HDR10(MediaCodecInfo.CodecProfileLevel.APVProfile422_10HDR10),
    APVProfile422_10HDR10Plus(MediaCodecInfo.CodecProfileLevel.APVProfile422_10HDR10Plus),
}

@RequiresApi(36)
enum class APVLevelEnum(override val level: Int): LevelEnum {
    APVLevel1Band0(MediaCodecInfo.CodecProfileLevel.APVLevel1Band0),
    APVLevel1Band1(MediaCodecInfo.CodecProfileLevel.APVLevel1Band1),
    APVLevel1Band2(MediaCodecInfo.CodecProfileLevel.APVLevel1Band2),
    APVLevel1Band3(MediaCodecInfo.CodecProfileLevel.APVLevel1Band3),
    APVLevel11Band0(MediaCodecInfo.CodecProfileLevel.APVLevel11Band0),
    APVLevel11Band1(MediaCodecInfo.CodecProfileLevel.APVLevel11Band1),
    APVLevel11Band2(MediaCodecInfo.CodecProfileLevel.APVLevel11Band2),
    APVLevel11Band3(MediaCodecInfo.CodecProfileLevel.APVLevel11Band3),
    APVLevel2Band0(MediaCodecInfo.CodecProfileLevel.APVLevel2Band0),
    APVLevel2Band1(MediaCodecInfo.CodecProfileLevel.APVLevel2Band1),
    APVLevel2Band2(MediaCodecInfo.CodecProfileLevel.APVLevel2Band2),
    APVLevel2Band3(MediaCodecInfo.CodecProfileLevel.APVLevel2Band3),
    APVLevel21Band0(MediaCodecInfo.CodecProfileLevel.APVLevel21Band0),
    APVLevel21Band1(MediaCodecInfo.CodecProfileLevel.APVLevel21Band1),
    APVLevel21Band2(MediaCodecInfo.CodecProfileLevel.APVLevel21Band2),
    APVLevel21Band3(MediaCodecInfo.CodecProfileLevel.APVLevel21Band3),
    APVLevel3Band0(MediaCodecInfo.CodecProfileLevel.APVLevel3Band0),
    APVLevel3Band1(MediaCodecInfo.CodecProfileLevel.APVLevel3Band1),
    APVLevel3Band2(MediaCodecInfo.CodecProfileLevel.APVLevel3Band2),
    APVLevel3Band3(MediaCodecInfo.CodecProfileLevel.APVLevel3Band3),
    APVLevel31Band0(MediaCodecInfo.CodecProfileLevel.APVLevel31Band0),
    APVLevel31Band1(MediaCodecInfo.CodecProfileLevel.APVLevel31Band1),
    APVLevel31Band2(MediaCodecInfo.CodecProfileLevel.APVLevel31Band2),
    APVLevel31Band3(MediaCodecInfo.CodecProfileLevel.APVLevel31Band3),
    APVLevel4Band0(MediaCodecInfo.CodecProfileLevel.APVLevel4Band0),
    APVLevel4Band1(MediaCodecInfo.CodecProfileLevel.APVLevel4Band1),
    APVLevel4Band2(MediaCodecInfo.CodecProfileLevel.APVLevel4Band2),
    APVLevel4Band3(MediaCodecInfo.CodecProfileLevel.APVLevel4Band3),
    APVLevel41Band0(MediaCodecInfo.CodecProfileLevel.APVLevel41Band0),
    APVLevel41Band1(MediaCodecInfo.CodecProfileLevel.APVLevel41Band1),
    APVLevel41Band2(MediaCodecInfo.CodecProfileLevel.APVLevel41Band2),
    APVLevel41Band3(MediaCodecInfo.CodecProfileLevel.APVLevel41Band3),
    APVLevel5Band0(MediaCodecInfo.CodecProfileLevel.APVLevel5Band0),
    APVLevel5Band1(MediaCodecInfo.CodecProfileLevel.APVLevel5Band1),
    APVLevel5Band2(MediaCodecInfo.CodecProfileLevel.APVLevel5Band2),
    APVLevel5Band3(MediaCodecInfo.CodecProfileLevel.APVLevel5Band3),
    APVLevel51Band0(MediaCodecInfo.CodecProfileLevel.APVLevel51Band0),
    APVLevel51Band1(MediaCodecInfo.CodecProfileLevel.APVLevel51Band1),
    APVLevel51Band2(MediaCodecInfo.CodecProfileLevel.APVLevel51Band2),
    APVLevel51Band3(MediaCodecInfo.CodecProfileLevel.APVLevel51Band3),
    APVLevel6Band0(MediaCodecInfo.CodecProfileLevel.APVLevel6Band0),
    APVLevel6Band1(MediaCodecInfo.CodecProfileLevel.APVLevel6Band1),
    APVLevel6Band2(MediaCodecInfo.CodecProfileLevel.APVLevel6Band2),
    APVLevel6Band3(MediaCodecInfo.CodecProfileLevel.APVLevel6Band3),
    APVLevel61Band0(MediaCodecInfo.CodecProfileLevel.APVLevel61Band0),
    APVLevel61Band1(MediaCodecInfo.CodecProfileLevel.APVLevel61Band1),
    APVLevel61Band2(MediaCodecInfo.CodecProfileLevel.APVLevel61Band2),
    APVLevel61Band3(MediaCodecInfo.CodecProfileLevel.APVLevel61Band3),
    APVLevel7Band0(MediaCodecInfo.CodecProfileLevel.APVLevel7Band0),
    APVLevel7Band1(MediaCodecInfo.CodecProfileLevel.APVLevel7Band1),
    APVLevel7Band2(MediaCodecInfo.CodecProfileLevel.APVLevel7Band2),
    APVLevel7Band3(MediaCodecInfo.CodecProfileLevel.APVLevel7Band3),
    APVLevel71Band0(MediaCodecInfo.CodecProfileLevel.APVLevel71Band0),
    APVLevel71Band1(MediaCodecInfo.CodecProfileLevel.APVLevel71Band1),
    APVLevel71Band2(MediaCodecInfo.CodecProfileLevel.APVLevel71Band2),
    APVLevel71Band3(MediaCodecInfo.CodecProfileLevel.APVLevel71Band3),
}