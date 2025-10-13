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

object H263ProfileLevelMapper : ProfileLevelMapper {
    override val profiles = H263ProfileEnum.entries
    override val levels = H263LevelEnum.entries
}

enum class H263ProfileEnum(override val profile: Int): ProfileEnum {
    H263ProfileBaseline(MediaCodecInfo.CodecProfileLevel.H263ProfileBaseline),
    H263ProfileH320Coding(MediaCodecInfo.CodecProfileLevel.H263ProfileH320Coding),
    H263ProfileBackwardCompatible(MediaCodecInfo.CodecProfileLevel.H263ProfileBackwardCompatible),
    H263ProfileISWV2(MediaCodecInfo.CodecProfileLevel.H263ProfileISWV2),
    H263ProfileISWV3(MediaCodecInfo.CodecProfileLevel.H263ProfileISWV3),
    H263ProfileHighCompression(MediaCodecInfo.CodecProfileLevel.H263ProfileHighCompression),
    H263ProfileInternet(MediaCodecInfo.CodecProfileLevel.H263ProfileInternet),
    H263ProfileInterlace(MediaCodecInfo.CodecProfileLevel.H263ProfileInterlace),
    H263ProfileHighLatency(MediaCodecInfo.CodecProfileLevel.H263ProfileHighLatency),
}

enum class H263LevelEnum(override val level: Int): LevelEnum {
    H263Level10(MediaCodecInfo.CodecProfileLevel.H263Level10),
    H263Level20(MediaCodecInfo.CodecProfileLevel.H263Level20),
    H263Level30(MediaCodecInfo.CodecProfileLevel.H263Level30),
    H263Level40(MediaCodecInfo.CodecProfileLevel.H263Level40),
    H263Level45(MediaCodecInfo.CodecProfileLevel.H263Level45),
    H263Level50(MediaCodecInfo.CodecProfileLevel.H263Level50),
    H263Level60(MediaCodecInfo.CodecProfileLevel.H263Level60),
    H263Level70(MediaCodecInfo.CodecProfileLevel.H263Level70),
}