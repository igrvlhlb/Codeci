package io.igrvlhlb.lib.data.mapper.mimetypes

import io.igrvlhlb.lib.data.mapper.ProfileLevelMapper

object PlaceholderProfileLevelMapper : ProfileLevelMapper {
    override fun getProfileLevel(profile: Int, level: Int): Pair<String, String> = "-" to "-"
}