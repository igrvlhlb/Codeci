package io.igrvlhlb.lib.data.mapper.mimetypes
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import android.media.MediaCodecInfo.CodecProfileLevel
import androidx.annotation.RequiresApi
import io.igrvlhlb.lib.data.mapper.ProfileEnum
import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object DTS_UHDProfileLevelMapper : ProfileLevelMapper {
    override val profiles = DTS_UHDProfileEnum.entries
}

@RequiresApi(34)
enum class DTS_UHDProfileEnum(override val profile: Int): ProfileEnum {
    DTS_UHDProfileP1(CodecProfileLevel.DTS_UHDProfileP1),
    DTS_UHDProfileP2(CodecProfileLevel.DTS_UHDProfileP2),
}
