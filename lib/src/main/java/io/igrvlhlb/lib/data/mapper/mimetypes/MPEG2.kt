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

object MPEG2ProfileLevelMapper : ProfileLevelMapper {
    override val profiles = MPEG2ProfileEnum.entries
    override val levels = MPEG2LevelEnum.entries
}

enum class MPEG2ProfileEnum(override val profile: Int): ProfileEnum {
    MPEG2ProfileSimple(MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSimple),
    MPEG2ProfileMain(MediaCodecInfo.CodecProfileLevel.MPEG2ProfileMain),
    MPEG2Profile422(MediaCodecInfo.CodecProfileLevel.MPEG2Profile422),
    MPEG2ProfileSNR(MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSNR),
    MPEG2ProfileSpatial(MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSpatial),
    MPEG2ProfileHigh(MediaCodecInfo.CodecProfileLevel.MPEG2ProfileHigh),
}

enum class MPEG2LevelEnum(override val level: Int): LevelEnum {
    MPEG2LevelLL(MediaCodecInfo.CodecProfileLevel.MPEG2LevelLL),
    MPEG2LevelML(MediaCodecInfo.CodecProfileLevel.MPEG2LevelML),
    MPEG2LevelH14(MediaCodecInfo.CodecProfileLevel.MPEG2LevelH14),
    MPEG2LevelHL(MediaCodecInfo.CodecProfileLevel.MPEG2LevelHL),
    MPEG2LevelHP(MediaCodecInfo.CodecProfileLevel.MPEG2LevelHP),
}