package io.igrvlhlb.lib.data.mapper.mimetypes
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object PlaceholderProfileLevelMapper : ProfileLevelMapper {
    override fun getProfileLevel(profile: Int, level: Int): Pair<String, String> = "-" to "-"
}