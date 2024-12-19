package io.igrvlhlb.codeci.utils

import android.media.MediaCodecInfo
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format12bitRGB444
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format16bitARGB1555
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format16bitARGB4444
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format16bitBGR565
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format16bitRGB565
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format18BitBGR666
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format18bitARGB1665
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format18bitRGB666
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format19bitARGB1666
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format24BitABGR6666
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format24BitARGB6666
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format24bitARGB1887
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format24bitBGR888
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format24bitRGB888
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format25bitARGB1888
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format32bitABGR2101010
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format32bitABGR8888
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format32bitARGB8888
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format32bitBGRA8888
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format64bitABGRFloat
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_Format8bitRGB332
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatCbYCrY
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatCrYCbY
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatL16
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatL2
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatL24
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatL32
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatL4
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatL8
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatMonochrome
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatRGBAFlexible
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatRGBFlexible
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer10bit
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer8bit
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer8bitcompressed
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYCbYCr
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYCrYCb
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV411PackedPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV411Planar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Flexible
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420PackedPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420PackedSemiPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Planar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422Flexible
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422PackedPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422PackedSemiPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422Planar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422SemiPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV444Flexible
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV444Interleaved
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_FormatYUVP010
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_QCOM_FormatYUV420SemiPlanar
import android.media.MediaCodecInfo.CodecCapabilities.COLOR_TI_FormatYUV420PackedSemiPlanar
import android.media.MediaCodecInfo.CodecProfileLevel
import android.media.MediaCodecInfo.CodecProfileLevel.AV1Level2
import android.media.MediaCodecInfo.CodecProfileLevel.AV1Level21
import android.media.MediaCodecInfo.CodecProfileLevel.AV1Level22
import android.media.MediaCodecInfo.CodecProfileLevel.AV1Level23
import android.media.MediaCodecInfo.CodecProfileLevel.AV1Level3
import android.media.MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10
import android.media.MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10
import android.media.MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10Plus
import android.media.MediaCodecInfo.CodecProfileLevel.AV1ProfileMain8
import android.media.MediaCodecInfo.CodecProfileLevel.VP8Level_Version0
import android.media.MediaCodecInfo.CodecProfileLevel.VP8Level_Version1
import android.media.MediaCodecInfo.CodecProfileLevel.VP8Level_Version2
import android.media.MediaCodecInfo.CodecProfileLevel.VP8Level_Version3
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level1
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level11
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level2
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level21
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level3
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level31
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level4
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level41
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level5
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level51
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level52
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level6
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level61
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Level62
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile0
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile1
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile2
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR10Plus
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile3
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR
import android.media.MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR10Plus
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
import android.media.MediaCodecInfo.CodecProfileLevel.*
object CodecConstantsMapper {
    fun colorFormatToString(format: Int): String {
        return when (format) {
            COLOR_FormatMonochrome -> "Monochrome"
            COLOR_Format8bitRGB332 -> "8bitRGB332"
            COLOR_Format12bitRGB444 -> "12bitRGB444"
            COLOR_Format16bitARGB4444 -> "16bitARGB4444"
            COLOR_Format16bitARGB1555 -> "16bitARGB1555"
            COLOR_Format16bitRGB565 -> "16bitRGB565"
            COLOR_Format16bitBGR565 -> "16bitBGR565"
            COLOR_Format18bitRGB666 -> "18bitRGB666"
            COLOR_Format18bitARGB1665 -> "18bitARGB1665"
            COLOR_Format19bitARGB1666 -> "19bitARGB1666"
            COLOR_Format24bitRGB888 -> "24bitRGB888"
            COLOR_Format24bitBGR888 -> "24bitBGR888"
            COLOR_Format24bitARGB1887 -> "24bitARGB1887"
            COLOR_Format25bitARGB1888 -> "25bitARGB1888"
            COLOR_Format32bitBGRA8888 -> "32bitBGRA8888"
            COLOR_Format32bitARGB8888 -> "32bitARGB8888"
            COLOR_FormatYUV411Planar -> "YUV411Planar"
            COLOR_FormatYUV411PackedPlanar -> "YUV411PackedPlanar"
            COLOR_FormatYUV420Planar -> "YUV420Planar"
            COLOR_FormatYUV420PackedPlanar -> "YUV420PackedPlanar"
            COLOR_FormatYUV420SemiPlanar -> "YUV420SemiPlanar"
            COLOR_FormatYUV422Planar -> "YUV422Planar"
            COLOR_FormatYUV422PackedPlanar -> "YUV422PackedPlanar"
            COLOR_FormatYUV422SemiPlanar -> "YUV422SemiPlanar"
            COLOR_FormatYCbYCr -> "YCbYCr"
            COLOR_FormatYCrYCb -> "YCrYCb"
            COLOR_FormatCbYCrY -> "CbYCrY"
            COLOR_FormatCrYCbY -> "CrYCbY"
            COLOR_FormatYUV444Interleaved -> "YUV444Interleaved"
            COLOR_FormatRawBayer8bit -> "RawBayer8bit"
            COLOR_FormatRawBayer10bit -> "RawBayer10bit"
            COLOR_FormatRawBayer8bitcompressed -> "RawBayer8bitcompressed"
            COLOR_FormatL2 -> "L2"
            COLOR_FormatL4 -> "L4"
            COLOR_FormatL8 -> "L8"
            COLOR_FormatL16 -> "L16"
            COLOR_FormatL24 -> "L24"
            COLOR_FormatL32 -> "L32"
            COLOR_FormatYUV420PackedSemiPlanar -> "YUV420PackedSemiPlanar"
            COLOR_FormatYUV422PackedSemiPlanar -> "YUV422PackedSemiPlanar"
            COLOR_Format18BitBGR666 -> "18BitBGR666"
            COLOR_Format24BitARGB6666 -> "24BitARGB6666"
            COLOR_Format24BitABGR6666 -> "24BitABGR6666"
            COLOR_FormatYUVP010 -> "YUVP010"
            COLOR_TI_FormatYUV420PackedSemiPlanar -> "TI_YUV420PackedSemiPlanar"
            COLOR_FormatSurface -> "Surface"
            COLOR_Format64bitABGRFloat -> "64bitABGRFloat"
            COLOR_Format32bitABGR8888 -> "32bitABGR8888"
            COLOR_Format32bitABGR2101010 -> "32bitABGR2101010"
            COLOR_FormatYUV420Flexible -> "YUV420Flexible"
            COLOR_FormatYUV422Flexible -> "YUV422Flexible"
            COLOR_FormatYUV444Flexible -> "YUV444Flexible"
            COLOR_FormatRGBFlexible -> "RGBFlexible"
            COLOR_FormatRGBAFlexible -> "RGBAFlexible"
            COLOR_QCOM_FormatYUV420SemiPlanar -> "QCOM_YUV420SemiPlanar"
            else -> "Unknown"
        }
    }

    fun profileLevelToString(mimeType: String, profileLevel: CodecProfileLevel): Pair<String, String> {
        val fn: (CodecProfileLevel) -> Pair<String, String> = when (mimeType) {
            MIMETYPE_VIDEO_VP8 -> ::getVP8ProfileLevel
            MIMETYPE_VIDEO_VP9 -> ::getVP9ProfileLevel
            MIMETYPE_VIDEO_AV1 -> ::getAV1ProfileLevel
            MIMETYPE_VIDEO_AVC -> ::getAVCProfileLevel
            MIMETYPE_VIDEO_HEVC -> ::getHEVCProfileLevel
            MIMETYPE_VIDEO_MPEG4 -> ::getMPEG4ProfileLevel
            MIMETYPE_VIDEO_H263 -> ::getH263ProfileLevel
            MIMETYPE_VIDEO_MPEG2 -> ::getMPEG2ProfileLevel
            MIMETYPE_VIDEO_DOLBY_VISION -> ::getDOLBY_VISIONProfileLevel
            MIMETYPE_AUDIO_AAC -> ::getAACProfileLevel
            MIMETYPE_AUDIO_AC4 -> ::getAC4ProfileLevel
            MIMETYPE_AUDIO_DTS_HD -> ::getDTS_HDProfileLevel
            MIMETYPE_AUDIO_DTS_UHD -> ::getDTS_UHDProfileLevel
            MIMETYPE_AUDIO_AAC_ELD,
            MIMETYPE_AUDIO_AAC_HE_V1,
            MIMETYPE_AUDIO_AAC_HE_V2,
            MIMETYPE_AUDIO_AAC_LC,
            MIMETYPE_AUDIO_AAC_XHE,
            MIMETYPE_AUDIO_AC3,
            MIMETYPE_AUDIO_AMR_NB,
            MIMETYPE_AUDIO_AMR_WB,
            MIMETYPE_AUDIO_DOLBY_MAT,
            MIMETYPE_AUDIO_DOLBY_TRUEHD,
            MIMETYPE_AUDIO_DRA,
            MIMETYPE_AUDIO_DTS,
            MIMETYPE_AUDIO_EAC3,
            MIMETYPE_AUDIO_EAC3_JOC,
            MIMETYPE_AUDIO_FLAC,
            MIMETYPE_AUDIO_G711_ALAW,
            MIMETYPE_AUDIO_G711_MLAW,
            MIMETYPE_AUDIO_IEC61937,
            MIMETYPE_AUDIO_MPEG,
            MIMETYPE_AUDIO_MPEGH_BL_L3,
            MIMETYPE_AUDIO_MPEGH_BL_L4,
            MIMETYPE_AUDIO_MPEGH_LC_L3,
            MIMETYPE_AUDIO_MPEGH_LC_L4,
            MIMETYPE_AUDIO_MPEGH_MHA1,
            MIMETYPE_AUDIO_MPEGH_MHM1,
            MIMETYPE_AUDIO_MSGSM,
            MIMETYPE_AUDIO_OPUS,
            MIMETYPE_AUDIO_QCELP,
            MIMETYPE_AUDIO_RAW,
            MIMETYPE_AUDIO_SCRAMBLED,
            MIMETYPE_AUDIO_VORBIS,
            MIMETYPE_IMAGE_ANDROID_HEIC,
            MIMETYPE_IMAGE_AVIF,
            MIMETYPE_TEXT_CEA_608,
            MIMETYPE_TEXT_CEA_708,
            MIMETYPE_TEXT_SUBRIP,
            MIMETYPE_TEXT_VTT,
            MIMETYPE_VIDEO_RAW,
            MIMETYPE_VIDEO_SCRAMBLED -> ::undefinedProfileLevel
            else -> { _ -> "?" to "?" }
        }
        return fn(profileLevel)
    }

    private fun getVP8ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = "VP8ProfileMain"
        val level = when (profileLevel.level) {
            VP8Level_Version0 -> "0"
            VP8Level_Version1 -> "1"
            VP8Level_Version2 -> "2"
            VP8Level_Version3 -> "3"
            else -> "?"
        }
        return profile to level
    }

    private fun getVP9ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            VP9Profile0 -> "0"
            VP9Profile1 -> "1"
            VP9Profile2 -> "2"
            VP9Profile3 -> "3"
            VP9Profile2HDR -> "2HDR"
            VP9Profile3HDR -> "3HDR"
            VP9Profile2HDR10Plus -> "2HDR10Plus"
            VP9Profile3HDR10Plus -> "3HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            VP9Level1 -> "1"
            VP9Level11 -> "11"
            VP9Level2 -> "2"
            VP9Level21 -> "21"
            VP9Level3 -> "3"
            VP9Level31 -> "31"
            VP9Level4 -> "4"
            VP9Level41 -> "41"
            VP9Level5 -> "5"
            VP9Level51 -> "51"
            VP9Level52 -> "52"
            VP9Level6 -> "6"
            VP9Level61 -> "61"
            VP9Level62 -> "62"
            else -> "?"
        }
        return profile to level
    }

    private fun getAV1ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            AV1ProfileMain8 -> "8"
            AV1ProfileMain10 -> "10"
            AV1ProfileMain10HDR10 -> "10HDR10"
            AV1ProfileMain10HDR10Plus -> "10HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            AV1Level2 -> "2"
            AV1Level21 -> "21"
            AV1Level22 -> "22"
            AV1Level23 -> "23"
            AV1Level3 -> "3"
            AV1Level31 -> "31"
            AV1Level32 -> "32"
            AV1Level33 -> "33"
            AV1Level4 -> "4"
            AV1Level41 -> "41"
            AV1Level42 -> "42"
            AV1Level43 -> "43"
            AV1Level5 -> "5"
            AV1Level51 -> "51"
            AV1Level52 -> "52"
            AV1Level53 -> "53"
            AV1Level6 -> "6"
            AV1Level61 -> "61"
            AV1Level62 -> "62"
            AV1Level63 -> "63"
            AV1Level7 -> "7"
            AV1Level71 -> "71"
            AV1Level72 -> "72"
            AV1Level73 -> "73"
            else -> "?"
        }
        return profile to level
    }

    private fun getAVCProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            AVCProfileBaseline -> "Baseline"
            AVCProfileMain -> "Main"
            AVCProfileExtended -> "Extended"
            AVCProfileHigh -> "High"
            AVCProfileHigh10 -> "High10"
            AVCProfileHigh422 -> "High422"
            AVCProfileHigh444 -> "High444"
            AVCProfileConstrainedBaseline -> "ConstrainedBaseline"
            AVCProfileConstrainedHigh -> "ConstrainedHigh"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            AVCLevel1 -> "1"
            AVCLevel11 -> "11"
            AVCLevel12 -> "12"
            AVCLevel13 -> "13"
            AVCLevel1b -> "1b"
            AVCLevel2 -> "2"
            AVCLevel21 -> "21"
            AVCLevel22 -> "22"
            AVCLevel3 -> "3"
            AVCLevel31 -> "31"
            AVCLevel32 -> "32"
            AVCLevel4 -> "4"
            AVCLevel41 -> "41"
            AVCLevel42 -> "42"
            AVCLevel5 -> "5"
            AVCLevel51 -> "51"
            AVCLevel52 -> "52"
            AVCLevel6 -> "6"
            AVCLevel61 -> "61"
            AVCLevel62 -> "62"
            else -> "?"
        }
        return profile to level
    }

    private fun getHEVCProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            HEVCProfileMain -> "Main"
            HEVCProfileMain10 -> "Main10"
            HEVCProfileMainStill -> "MainStill"
            HEVCProfileMain10HDR10 -> "Main10HDR10"
            HEVCProfileMain10HDR10Plus -> "Main10HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            HEVCHighTierLevel1 -> "HighTierLevel1"
            HEVCMainTierLevel2 -> "HighTierLevel2"
            HEVCHighTierLevel2 -> "HighTierLevel2"
            HEVCMainTierLevel21 -> "HighTierLevel21"
            HEVCHighTierLevel21 -> "HighTierLevel21"
            HEVCMainTierLevel3 -> "HighTierLevel3"
            HEVCHighTierLevel3 -> "HighTierLevel3"
            HEVCMainTierLevel31 -> "HighTierLevel31"
            HEVCHighTierLevel31 -> "HighTierLevel31"
            HEVCMainTierLevel4 -> "HighTierLevel4"
            HEVCHighTierLevel4 -> "HighTierLevel4"
            HEVCMainTierLevel41 -> "HighTierLevel41"
            HEVCHighTierLevel41 -> "HighTierLevel41"
            HEVCMainTierLevel5 -> "HighTierLevel5"
            HEVCHighTierLevel5 -> "HighTierLevel5"
            HEVCMainTierLevel51 -> "HighTierLevel51"
            HEVCHighTierLevel51 -> "HighTierLevel51"
            HEVCMainTierLevel52 -> "HighTierLevel52"
            HEVCHighTierLevel52 -> "HighTierLevel52"
            HEVCMainTierLevel6 -> "HighTierLevel6"
            HEVCHighTierLevel6 -> "HighTierLevel6"
            HEVCMainTierLevel61 -> "HighTierLevel61"
            HEVCHighTierLevel61 -> "HighTierLevel61"
            HEVCMainTierLevel62 -> "HighTierLevel62"
            HEVCHighTierLevel62 -> "HighTierLevel62"
            else -> "?"
        }
        return profile to level
    }

    private fun getMPEG4ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MPEG4ProfileSimple -> "Simple"
            MPEG4ProfileSimpleScalable -> "SimpleScalable"
            MPEG4ProfileCore -> "Core"
            MPEG4ProfileMain -> "Main"
            MPEG4ProfileNbit -> "Nbit"
            MPEG4ProfileScalableTexture -> "ScalableTexture"
            MPEG4ProfileSimpleFace -> "SimpleFace"
            MPEG4ProfileSimpleFBA -> "SimpleFBA"
            MPEG4ProfileBasicAnimated -> "BasicAnimated"
            MPEG4ProfileHybrid -> "Hybrid"
            MPEG4ProfileAdvancedRealTime -> "AdvancedRealTime"
            MPEG4ProfileCoreScalable -> "CoreScalable"
            MPEG4ProfileAdvancedCoding -> "AdvancedCoding"
            MPEG4ProfileAdvancedCore -> "AdvancedCore"
            MPEG4ProfileAdvancedScalable -> "AdvancedScalable"
            MPEG4ProfileAdvancedSimple -> "AdvancedSimple"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MPEG4Level0 -> "0"
            MPEG4Level0b -> "0b"
            MPEG4Level1 -> "1"
            MPEG4Level2 -> "2"
            MPEG4Level3 -> "3"
            MPEG4Level3b -> "3b"
            MPEG4Level4 -> "4"
            MPEG4Level4a -> "4a"
            MPEG4Level5 -> "5"
            MPEG4Level6 -> "6"
            else -> "?"
        }
        return profile to level
    }

    private fun getH263ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            H263ProfileBaseline -> "Baseline"
            H263ProfileH320Coding -> "H320Coding"
            H263ProfileBackwardCompatible -> "BackwardCompatible"
            H263ProfileISWV2 -> "ISWV2"
            H263ProfileISWV3 -> "ISWV3"
            H263ProfileHighCompression -> "HighCompression"
            H263ProfileInternet -> "Internet"
            H263ProfileInterlace -> "Interlace"
            H263ProfileHighLatency -> "HighLatency"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            H263Level10 -> "10"
            H263Level20 -> "20"
            H263Level30 -> "30"
            H263Level40 -> "40"
            H263Level45 -> "45"
            H263Level50 -> "50"
            H263Level60 -> "60"
            H263Level70 -> "70"
            else -> "?"
        }
        return profile to level
    }

    private fun getMPEG2ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MPEG2ProfileSimple -> "Simple"
            MPEG2ProfileMain -> "Main"
            MPEG2Profile422 -> "422"
            MPEG2ProfileSNR -> "SNR"
            MPEG2ProfileSpatial -> "Spatial"
            MPEG2ProfileHigh -> "High"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MPEG2LevelLL -> "LL"
            MPEG2LevelML -> "ML"
            MPEG2LevelH14 -> "H14"
            MPEG2LevelHL -> "HL"
            MPEG2LevelHP -> "HP"
            else -> "?"
        }
        return profile to level
    }

    private fun getDOLBY_VISIONProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            DolbyVisionProfileDvavPer -> "DvavPer"
            DolbyVisionProfileDvavPen -> "DvavPen"
            DolbyVisionProfileDvheDer -> "DvheDer"
            DolbyVisionProfileDvheDen -> "DvheDen"
            DolbyVisionProfileDvheDtr -> "DvheDtr"
            DolbyVisionProfileDvheStn -> "DvheStn"
            DolbyVisionProfileDvheDth -> "DvheDth"
            DolbyVisionProfileDvheDtb -> "DvheDtb"
            DolbyVisionProfileDvheSt -> "DvheSt"
            DolbyVisionProfileDvavSe -> "DvavSe"
            DolbyVisionProfileDvav110 -> "Dvav110"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            DolbyVisionLevelHd24 -> "Hd24"
            DolbyVisionLevelHd30 -> "Hd30"
            DolbyVisionLevelFhd24 -> "Fhd24"
            DolbyVisionLevelFhd30 -> "Fhd30"
            DolbyVisionLevelFhd60 -> "Fhd60"
            DolbyVisionLevelUhd24 -> "Uhd24"
            DolbyVisionLevelUhd30 -> "Uhd30"
            DolbyVisionLevelUhd48 -> "Uhd48"
            DolbyVisionLevelUhd60 -> "Uhd60"
            DolbyVisionLevelUhd120 -> "Uhd120"
            DolbyVisionLevel8k30 -> "8k30"
            DolbyVisionLevel8k60 -> "8k60"
            else -> "?"
        }
        return profile to level
    }

    private fun getAACProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            AACObjectMain -> "Main"
            AACObjectLC -> "LC"
            AACObjectSSR -> "SSR"
            AACObjectLTP -> "LTP"
            AACObjectHE -> "HE"
            AACObjectScalable -> "Scalable"
            AACObjectERLC -> "ERLC"
            AACObjectERScalable -> "ERScalable"
            AACObjectLD -> "LD"
            AACObjectHE_PS -> "HE_PS"
            AACObjectELD -> "ELD"
            AACObjectXHE -> "XHE"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "-"
        }
        return profile to level
    }

    private fun getAC4ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            AC4Profile00 -> "00"
            AC4Profile10 -> "10"
            AC4Profile11 -> "11"
            AC4Profile21 -> "21"
            AC4Profile22 -> "22"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            AC4Level0 -> "0"
            AC4Level1 -> "1"
            AC4Level2 -> "2"
            AC4Level3 -> "3"
            AC4Level4 -> "4"
            else -> "?"
        }
        return profile to level
    }

    private fun getDTS_HDProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            DTS_HDProfileHRA -> "HRA"
            DTS_HDProfileLBR -> "LBR"
            DTS_HDProfileMA -> "MA"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "-"
        }
        return profile to level
    }

    private fun getDTS_UHDProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            DTS_UHDProfileP1 -> "P1"
            DTS_UHDProfileP2 -> "P2"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "-"
        }
        return profile to level
    }

    fun x(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "?"
        }
        return profile to level
    }

    fun undefinedProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel) = "-" to "-"
}

