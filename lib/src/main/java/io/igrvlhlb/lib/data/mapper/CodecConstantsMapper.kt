package io.igrvlhlb.lib.data.mapper

import android.media.MediaCodecInfo
import android.media.MediaFormat.MIMETYPE_AUDIO_AAC
import android.media.MediaFormat.MIMETYPE_AUDIO_AAC_ELD
import android.media.MediaFormat.MIMETYPE_AUDIO_AAC_HE_V1
import android.media.MediaFormat.MIMETYPE_AUDIO_AAC_HE_V2
import android.media.MediaFormat.MIMETYPE_AUDIO_AAC_LC
import android.media.MediaFormat.MIMETYPE_AUDIO_AAC_XHE
import android.media.MediaFormat.MIMETYPE_AUDIO_AC3
import android.media.MediaFormat.MIMETYPE_AUDIO_AC4
import android.media.MediaFormat.MIMETYPE_AUDIO_AMR_NB
import android.media.MediaFormat.MIMETYPE_AUDIO_AMR_WB
import android.media.MediaFormat.MIMETYPE_AUDIO_DOLBY_MAT
import android.media.MediaFormat.MIMETYPE_AUDIO_DOLBY_TRUEHD
import android.media.MediaFormat.MIMETYPE_AUDIO_DRA
import android.media.MediaFormat.MIMETYPE_AUDIO_DTS
import android.media.MediaFormat.MIMETYPE_AUDIO_DTS_HD
import android.media.MediaFormat.MIMETYPE_AUDIO_DTS_UHD
import android.media.MediaFormat.MIMETYPE_AUDIO_EAC3
import android.media.MediaFormat.MIMETYPE_AUDIO_EAC3_JOC
import android.media.MediaFormat.MIMETYPE_AUDIO_FLAC
import android.media.MediaFormat.MIMETYPE_AUDIO_G711_ALAW
import android.media.MediaFormat.MIMETYPE_AUDIO_G711_MLAW
import android.media.MediaFormat.MIMETYPE_AUDIO_IEC61937
import android.media.MediaFormat.MIMETYPE_AUDIO_MPEG
import android.media.MediaFormat.MIMETYPE_AUDIO_MPEGH_BL_L3
import android.media.MediaFormat.MIMETYPE_AUDIO_MPEGH_BL_L4
import android.media.MediaFormat.MIMETYPE_AUDIO_MPEGH_LC_L3
import android.media.MediaFormat.MIMETYPE_AUDIO_MPEGH_LC_L4
import android.media.MediaFormat.MIMETYPE_AUDIO_MPEGH_MHA1
import android.media.MediaFormat.MIMETYPE_AUDIO_MPEGH_MHM1
import android.media.MediaFormat.MIMETYPE_AUDIO_MSGSM
import android.media.MediaFormat.MIMETYPE_AUDIO_OPUS
import android.media.MediaFormat.MIMETYPE_AUDIO_QCELP
import android.media.MediaFormat.MIMETYPE_AUDIO_RAW
import android.media.MediaFormat.MIMETYPE_AUDIO_SCRAMBLED
import android.media.MediaFormat.MIMETYPE_AUDIO_VORBIS
import android.media.MediaFormat.MIMETYPE_IMAGE_ANDROID_HEIC
import android.media.MediaFormat.MIMETYPE_IMAGE_AVIF
import android.media.MediaFormat.MIMETYPE_TEXT_CEA_608
import android.media.MediaFormat.MIMETYPE_TEXT_CEA_708
import android.media.MediaFormat.MIMETYPE_TEXT_SUBRIP
import android.media.MediaFormat.MIMETYPE_TEXT_VTT
import android.media.MediaFormat.MIMETYPE_VIDEO_AV1
import android.media.MediaFormat.MIMETYPE_VIDEO_AVC
import android.media.MediaFormat.MIMETYPE_VIDEO_DOLBY_VISION
import android.media.MediaFormat.MIMETYPE_VIDEO_H263
import android.media.MediaFormat.MIMETYPE_VIDEO_HEVC
import android.media.MediaFormat.MIMETYPE_VIDEO_MPEG2
import android.media.MediaFormat.MIMETYPE_VIDEO_MPEG4
import android.media.MediaFormat.MIMETYPE_VIDEO_RAW
import android.media.MediaFormat.MIMETYPE_VIDEO_SCRAMBLED
import android.media.MediaFormat.MIMETYPE_VIDEO_VP8
import android.media.MediaFormat.MIMETYPE_VIDEO_VP9
import androidx.annotation.ChecksSdkIntAtLeast
import io.igrvlhlb.lib.data.ColorFormat
import io.igrvlhlb.lib.data.mapper.mimetypes.AACProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.AC4ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.AV1ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.AVCProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.DTS_HDProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.DTS_UHDProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.DolbyVisionProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.H263ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.HEVCProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.MPEG2ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.MPEG4ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.PlaceholderProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.VP8ProfileLevelMapper
import io.igrvlhlb.lib.data.mapper.mimetypes.VP9ProfileLevelMapper
import io.igrvlhlb.lib.utils.sdkAtLeast

object CodecConstantsMapper {
    fun colorFormatToString(format: Int): String {
        return ColorFormat.from(format)?.formatName ?: "Unknown"
    }

    fun profileLevelToString(mimeType: String, profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profileLevelString = CodecProfileLevel.entries.find { it.mimeType == mimeType }?.profileLevel?.getProfileLevel(profileLevel)
        return profileLevelString ?: ("?" to "?")
    }
}

enum class CodecProfileLevel(val mimeType: String, val profileLevel: ProfileLevelMapper) {
    VP8(MIMETYPE_VIDEO_VP8, VP8ProfileLevelMapper),
    VP9(MIMETYPE_VIDEO_VP9, VP9ProfileLevelMapper),
    AV1(sdkAtLeastMimeType(29) { MIMETYPE_VIDEO_AV1 }, AV1ProfileLevelMapper),
    AVC(MIMETYPE_VIDEO_AVC, AVCProfileLevelMapper),
    HEVC(MIMETYPE_VIDEO_HEVC, HEVCProfileLevelMapper),
    MPEG4(MIMETYPE_VIDEO_MPEG4, MPEG4ProfileLevelMapper),
    H263(MIMETYPE_VIDEO_H263, H263ProfileLevelMapper),
    MPEG2(MIMETYPE_VIDEO_MPEG2, MPEG2ProfileLevelMapper),
    DOLBY_VISION(MIMETYPE_VIDEO_DOLBY_VISION, DolbyVisionProfileLevelMapper),
    AAC(MIMETYPE_AUDIO_AAC, AACProfileLevelMapper),
    AC4(sdkAtLeastMimeType(29) { MIMETYPE_AUDIO_AC4 }, AC4ProfileLevelMapper),
    DTS_HD(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_DTS_HD }, DTS_HDProfileLevelMapper),
    DTS_UHD(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_DTS_UHD }, DTS_UHDProfileLevelMapper),
    AAC_ELD(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_AAC_ELD }, PlaceholderProfileLevelMapper),
    AAC_HE_V1(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_AAC_HE_V1 }, PlaceholderProfileLevelMapper),
    AAC_HE_V2(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_AAC_HE_V2 }, PlaceholderProfileLevelMapper),
    AAC_LC(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_AAC_LC }, PlaceholderProfileLevelMapper),
    AAC_XHE(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_AAC_XHE }, PlaceholderProfileLevelMapper),
    AC3(MIMETYPE_AUDIO_AC3, PlaceholderProfileLevelMapper),
    AMR_NB(MIMETYPE_AUDIO_AMR_NB, PlaceholderProfileLevelMapper),
    AMR_WB(MIMETYPE_AUDIO_AMR_WB, PlaceholderProfileLevelMapper),
    DOLBY_MAT(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_DOLBY_MAT }, PlaceholderProfileLevelMapper),
    DOLBY_TRUEHD(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_DOLBY_TRUEHD }, PlaceholderProfileLevelMapper),
    DRA(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_DRA }, PlaceholderProfileLevelMapper),
    DTS(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_DTS }, PlaceholderProfileLevelMapper),
    EAC3(MIMETYPE_AUDIO_EAC3, PlaceholderProfileLevelMapper),
    EAC3_JOC(sdkAtLeastMimeType(29) { MIMETYPE_AUDIO_EAC3_JOC }, PlaceholderProfileLevelMapper),
    FLAC(MIMETYPE_AUDIO_FLAC, PlaceholderProfileLevelMapper),
    G711_ALAW(MIMETYPE_AUDIO_G711_ALAW, PlaceholderProfileLevelMapper),
    G711_MLAW(MIMETYPE_AUDIO_G711_MLAW, PlaceholderProfileLevelMapper),
    IEC61937(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_IEC61937 }, PlaceholderProfileLevelMapper),
    MPEG(MIMETYPE_AUDIO_MPEG, PlaceholderProfileLevelMapper),
    MPEGH_BL_L3(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_MPEGH_BL_L3 }, PlaceholderProfileLevelMapper),
    MPEGH_BL_L4(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_MPEGH_BL_L4 }, PlaceholderProfileLevelMapper),
    MPEGH_LC_L3(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_MPEGH_LC_L3 }, PlaceholderProfileLevelMapper),
    MPEGH_LC_L4(sdkAtLeastMimeType(33) { MIMETYPE_AUDIO_MPEGH_LC_L4 }, PlaceholderProfileLevelMapper),
    MPEGH_MHA1(sdkAtLeastMimeType(31) { MIMETYPE_AUDIO_MPEGH_MHA1 }, PlaceholderProfileLevelMapper),
    MPEGH_MHM1(sdkAtLeastMimeType(31) { MIMETYPE_AUDIO_MPEGH_MHM1 }, PlaceholderProfileLevelMapper),
    MSGSM(MIMETYPE_AUDIO_MSGSM, PlaceholderProfileLevelMapper),
    OPUS(MIMETYPE_AUDIO_OPUS, PlaceholderProfileLevelMapper),
    QCELP(MIMETYPE_AUDIO_QCELP, PlaceholderProfileLevelMapper),
    AUDIO_RAW(MIMETYPE_AUDIO_RAW, PlaceholderProfileLevelMapper),
    AUDIO_SCRAMBLED(sdkAtLeastMimeType(26) { MIMETYPE_AUDIO_SCRAMBLED }, PlaceholderProfileLevelMapper),
    VORBIS(MIMETYPE_AUDIO_VORBIS, PlaceholderProfileLevelMapper),
    ANDROID_HEIC(sdkAtLeastMimeType(28) { MIMETYPE_IMAGE_ANDROID_HEIC }, PlaceholderProfileLevelMapper),
    AVIF(sdkAtLeastMimeType(34) { MIMETYPE_IMAGE_AVIF }, PlaceholderProfileLevelMapper),
    CEA_608(MIMETYPE_TEXT_CEA_608, PlaceholderProfileLevelMapper),
    CEA_708(sdkAtLeastMimeType(28) { MIMETYPE_TEXT_CEA_708 }, PlaceholderProfileLevelMapper),
    SUBRIP(sdkAtLeastMimeType(28) { MIMETYPE_TEXT_SUBRIP }, PlaceholderProfileLevelMapper),
    VTT(MIMETYPE_TEXT_VTT, PlaceholderProfileLevelMapper),
    VIDEO_RAW(MIMETYPE_VIDEO_RAW, PlaceholderProfileLevelMapper),
    VIDEO_SCRAMBLED(sdkAtLeastMimeType(26) { MIMETYPE_VIDEO_SCRAMBLED }, PlaceholderProfileLevelMapper),
}


interface ProfileEnum {
    val profile: Int
    val name: String
}

interface LevelEnum {
    val level: Int
    val name: String
}

interface ProfileLevelMapper {
    val profiles: Collection<ProfileEnum> get() = emptyList()
    val levels: Collection<LevelEnum> get() = emptyList()
    fun getProfileLevel(profile: Int, level: Int): Pair<String, String> {
        val profileStr = profiles.find { it.profile == profile }?.name ?: "?"
        val levelStr = levels.find { it.level == level }?.name ?: "?"
        return profileStr to levelStr
    }
    fun getProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        return getProfileLevel(profileLevel.profile, profileLevel.level)
    }
}

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
fun <T: Any>sdkAtLeastOrElse(sdkInt: Int, otherwise: T, value: () -> T): T =
    if (sdkAtLeast(sdkInt)) value() else otherwise

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
fun sdkAtLeastProfileLevel(sdkInt: Int, value: () -> Int): Int =
    sdkAtLeastOrElse(sdkInt, -1, value)

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
fun sdkAtLeastMimeType(sdkInt: Int, value: () -> String): String =
    sdkAtLeastOrElse(sdkInt, "", value)
