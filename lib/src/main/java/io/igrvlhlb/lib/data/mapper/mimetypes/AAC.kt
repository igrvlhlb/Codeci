package io.igrvlhlb.lib.data.mapper.mimetypes
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import android.media.MediaCodecInfo
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.sdkAtLeastProfileLevel

object AACProfileLevelMapper : ProfileLevelMapper {
    override val profiles = AACProfileEnum.entries
}

enum class AACProfileEnum(override val profile: Int): ProfileEnum {
    AACObjectMain(MediaCodecInfo.CodecProfileLevel.AACObjectMain),
    AACObjectLC(MediaCodecInfo.CodecProfileLevel.AACObjectLC),
    AACObjectSSR(MediaCodecInfo.CodecProfileLevel.AACObjectSSR),
    AACObjectLTP(MediaCodecInfo.CodecProfileLevel.AACObjectLTP),
    AACObjectHE(MediaCodecInfo.CodecProfileLevel.AACObjectHE),
    AACObjectScalable(MediaCodecInfo.CodecProfileLevel.AACObjectScalable),
    AACObjectERLC(MediaCodecInfo.CodecProfileLevel.AACObjectERLC),
    AACObjectERScalable(sdkAtLeastProfileLevel(26) { MediaCodecInfo.CodecProfileLevel.AACObjectERScalable }),
    AACObjectLD(MediaCodecInfo.CodecProfileLevel.AACObjectLD),
    AACObjectHE_PS(MediaCodecInfo.CodecProfileLevel.AACObjectHE_PS),
    AACObjectELD(MediaCodecInfo.CodecProfileLevel.AACObjectELD),
    AACObjectXHE(sdkAtLeastProfileLevel(28) { MediaCodecInfo.CodecProfileLevel.AACObjectXHE }),
}