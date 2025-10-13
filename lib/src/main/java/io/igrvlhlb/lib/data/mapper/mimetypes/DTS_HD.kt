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
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper


object DTS_HDProfileLevelMapper : ProfileLevelMapper {
    override val profiles = DTS_HDProfileEnum.entries
}

@RequiresApi(34)
enum class DTS_HDProfileEnum(override val profile: Int): ProfileEnum {
    DTS_HDProfileHRA(MediaCodecInfo.CodecProfileLevel.DTS_HDProfileHRA),
    DTS_HDProfileLBR(MediaCodecInfo.CodecProfileLevel.DTS_HDProfileLBR),
    DTS_HDProfileMA(MediaCodecInfo.CodecProfileLevel.DTS_HDProfileMA),
}
