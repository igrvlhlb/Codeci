package io.igrvlhlb.lib.data.mapper

import android.media.MediaCodecInfo
import android.media.MediaFormat

object CodecConstantsMapper {
    fun colorFormatToString(format: Int): String {
        return when (format) {
            MediaCodecInfo.CodecCapabilities.COLOR_FormatMonochrome -> "Monochrome"
            MediaCodecInfo.CodecCapabilities.COLOR_Format8bitRGB332 -> "8bitRGB332"
            MediaCodecInfo.CodecCapabilities.COLOR_Format12bitRGB444 -> "12bitRGB444"
            MediaCodecInfo.CodecCapabilities.COLOR_Format16bitARGB4444 -> "16bitARGB4444"
            MediaCodecInfo.CodecCapabilities.COLOR_Format16bitARGB1555 -> "16bitARGB1555"
            MediaCodecInfo.CodecCapabilities.COLOR_Format16bitRGB565 -> "16bitRGB565"
            MediaCodecInfo.CodecCapabilities.COLOR_Format16bitBGR565 -> "16bitBGR565"
            MediaCodecInfo.CodecCapabilities.COLOR_Format18bitRGB666 -> "18bitRGB666"
            MediaCodecInfo.CodecCapabilities.COLOR_Format18bitARGB1665 -> "18bitARGB1665"
            MediaCodecInfo.CodecCapabilities.COLOR_Format19bitARGB1666 -> "19bitARGB1666"
            MediaCodecInfo.CodecCapabilities.COLOR_Format24bitRGB888 -> "24bitRGB888"
            MediaCodecInfo.CodecCapabilities.COLOR_Format24bitBGR888 -> "24bitBGR888"
            MediaCodecInfo.CodecCapabilities.COLOR_Format24bitARGB1887 -> "24bitARGB1887"
            MediaCodecInfo.CodecCapabilities.COLOR_Format25bitARGB1888 -> "25bitARGB1888"
            MediaCodecInfo.CodecCapabilities.COLOR_Format32bitBGRA8888 -> "32bitBGRA8888"
            MediaCodecInfo.CodecCapabilities.COLOR_Format32bitARGB8888 -> "32bitARGB8888"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV411Planar -> "YUV411Planar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV411PackedPlanar -> "YUV411PackedPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Planar -> "YUV420Planar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420PackedPlanar -> "YUV420PackedPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar -> "YUV420SemiPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422Planar -> "YUV422Planar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422PackedPlanar -> "YUV422PackedPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422SemiPlanar -> "YUV422SemiPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYCbYCr -> "YCbYCr"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYCrYCb -> "YCrYCb"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatCbYCrY -> "CbYCrY"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatCrYCbY -> "CrYCbY"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV444Interleaved -> "YUV444Interleaved"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer8bit -> "RawBayer8bit"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer10bit -> "RawBayer10bit"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatRawBayer8bitcompressed -> "RawBayer8bitcompressed"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatL2 -> "L2"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatL4 -> "L4"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatL8 -> "L8"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatL16 -> "L16"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatL24 -> "L24"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatL32 -> "L32"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420PackedSemiPlanar -> "YUV420PackedSemiPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422PackedSemiPlanar -> "YUV422PackedSemiPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_Format18BitBGR666 -> "18BitBGR666"
            MediaCodecInfo.CodecCapabilities.COLOR_Format24BitARGB6666 -> "24BitARGB6666"
            MediaCodecInfo.CodecCapabilities.COLOR_Format24BitABGR6666 -> "24BitABGR6666"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUVP010 -> "YUVP010"
            MediaCodecInfo.CodecCapabilities.COLOR_TI_FormatYUV420PackedSemiPlanar -> "TI_YUV420PackedSemiPlanar"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface -> "Surface"
            MediaCodecInfo.CodecCapabilities.COLOR_Format64bitABGRFloat -> "64bitABGRFloat"
            MediaCodecInfo.CodecCapabilities.COLOR_Format32bitABGR8888 -> "32bitABGR8888"
            MediaCodecInfo.CodecCapabilities.COLOR_Format32bitABGR2101010 -> "32bitABGR2101010"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Flexible -> "YUV420Flexible"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV422Flexible -> "YUV422Flexible"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV444Flexible -> "YUV444Flexible"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatRGBFlexible -> "RGBFlexible"
            MediaCodecInfo.CodecCapabilities.COLOR_FormatRGBAFlexible -> "RGBAFlexible"
            MediaCodecInfo.CodecCapabilities.COLOR_QCOM_FormatYUV420SemiPlanar -> "QCOM_YUV420SemiPlanar"
            else -> "Unknown"
        }
    }

    fun profileLevelToString(mimeType: String, profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val fn: (MediaCodecInfo.CodecProfileLevel) -> Pair<String, String> = when (mimeType.getCodecSuffix()) {
            MediaFormat.MIMETYPE_VIDEO_VP8.getCodecSuffix() -> ::getVP8ProfileLevel
            MediaFormat.MIMETYPE_VIDEO_VP9.getCodecSuffix() -> ::getVP9ProfileLevel
            MediaFormat.MIMETYPE_VIDEO_AV1.getCodecSuffix() -> ::getAV1ProfileLevel
            MediaFormat.MIMETYPE_VIDEO_AVC.getCodecSuffix() -> ::getAVCProfileLevel
            MediaFormat.MIMETYPE_VIDEO_HEVC.getCodecSuffix() -> ::getHEVCProfileLevel
            MediaFormat.MIMETYPE_VIDEO_MPEG4.getCodecSuffix() -> ::getMPEG4ProfileLevel
            MediaFormat.MIMETYPE_VIDEO_H263.getCodecSuffix() -> ::getH263ProfileLevel
            MediaFormat.MIMETYPE_VIDEO_MPEG2.getCodecSuffix() -> ::getMPEG2ProfileLevel
            MediaFormat.MIMETYPE_VIDEO_DOLBY_VISION.getCodecSuffix() -> ::getDOLBY_VISIONProfileLevel
            MediaFormat.MIMETYPE_AUDIO_AAC.getCodecSuffix() -> ::getAACProfileLevel
            MediaFormat.MIMETYPE_AUDIO_AC4.getCodecSuffix() -> ::getAC4ProfileLevel
            MediaFormat.MIMETYPE_AUDIO_DTS_HD.getCodecSuffix() -> ::getDTS_HDProfileLevel
            MediaFormat.MIMETYPE_AUDIO_DTS_UHD.getCodecSuffix() -> ::getDTS_UHDProfileLevel
            MediaFormat.MIMETYPE_AUDIO_AAC_ELD.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_AAC_HE_V1.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_AAC_HE_V2.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_AAC_LC.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_AAC_XHE.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_AC3.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_AMR_NB.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_AMR_WB.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_DOLBY_MAT.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_DOLBY_TRUEHD.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_DRA.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_DTS.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_EAC3.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_EAC3_JOC.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_FLAC.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_G711_ALAW.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_G711_MLAW.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_IEC61937.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MPEG.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MPEGH_BL_L3.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MPEGH_BL_L4.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MPEGH_LC_L3.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MPEGH_LC_L4.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MPEGH_MHA1.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MPEGH_MHM1.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_MSGSM.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_OPUS.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_QCELP.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_RAW.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_SCRAMBLED.getCodecSuffix(),
            MediaFormat.MIMETYPE_AUDIO_VORBIS.getCodecSuffix(),
            MediaFormat.MIMETYPE_IMAGE_ANDROID_HEIC.getCodecSuffix(),
            MediaFormat.MIMETYPE_IMAGE_AVIF.getCodecSuffix(),
            MediaFormat.MIMETYPE_TEXT_CEA_608.getCodecSuffix(),
            MediaFormat.MIMETYPE_TEXT_CEA_708.getCodecSuffix(),
            MediaFormat.MIMETYPE_TEXT_SUBRIP.getCodecSuffix(),
            MediaFormat.MIMETYPE_TEXT_VTT.getCodecSuffix(),
            MediaFormat.MIMETYPE_VIDEO_RAW.getCodecSuffix(),
            MediaFormat.MIMETYPE_VIDEO_SCRAMBLED.getCodecSuffix() -> ::undefinedProfileLevel
            else -> { _ -> "?" to "?" }
        }
        return fn(profileLevel)
    }

    private fun getVP8ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = "VP8ProfileMain"
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version0 -> "0"
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version1 -> "1"
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version2 -> "2"
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version3 -> "3"
            else -> "?"
        }
        return profile to level
    }

    private fun getVP9ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.VP9Profile0 -> "0"
            MediaCodecInfo.CodecProfileLevel.VP9Profile1 -> "1"
            MediaCodecInfo.CodecProfileLevel.VP9Profile2 -> "2"
            MediaCodecInfo.CodecProfileLevel.VP9Profile3 -> "3"
            MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR -> "2HDR"
            MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR -> "3HDR"
            MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR10Plus -> "2HDR10Plus"
            MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR10Plus -> "3HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.VP9Level1 -> "1"
            MediaCodecInfo.CodecProfileLevel.VP9Level11 -> "11"
            MediaCodecInfo.CodecProfileLevel.VP9Level2 -> "2"
            MediaCodecInfo.CodecProfileLevel.VP9Level21 -> "21"
            MediaCodecInfo.CodecProfileLevel.VP9Level3 -> "3"
            MediaCodecInfo.CodecProfileLevel.VP9Level31 -> "31"
            MediaCodecInfo.CodecProfileLevel.VP9Level4 -> "4"
            MediaCodecInfo.CodecProfileLevel.VP9Level41 -> "41"
            MediaCodecInfo.CodecProfileLevel.VP9Level5 -> "5"
            MediaCodecInfo.CodecProfileLevel.VP9Level51 -> "51"
            MediaCodecInfo.CodecProfileLevel.VP9Level52 -> "52"
            MediaCodecInfo.CodecProfileLevel.VP9Level6 -> "6"
            MediaCodecInfo.CodecProfileLevel.VP9Level61 -> "61"
            MediaCodecInfo.CodecProfileLevel.VP9Level62 -> "62"
            else -> "?"
        }
        return profile to level
    }

    private fun getAV1ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain8 -> "8"
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10 -> "10"
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10 -> "10HDR10"
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10Plus -> "10HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.AV1Level2 -> "2"
            MediaCodecInfo.CodecProfileLevel.AV1Level21 -> "21"
            MediaCodecInfo.CodecProfileLevel.AV1Level22 -> "22"
            MediaCodecInfo.CodecProfileLevel.AV1Level23 -> "23"
            MediaCodecInfo.CodecProfileLevel.AV1Level3 -> "3"
            MediaCodecInfo.CodecProfileLevel.AV1Level31 -> "31"
            MediaCodecInfo.CodecProfileLevel.AV1Level32 -> "32"
            MediaCodecInfo.CodecProfileLevel.AV1Level33 -> "33"
            MediaCodecInfo.CodecProfileLevel.AV1Level4 -> "4"
            MediaCodecInfo.CodecProfileLevel.AV1Level41 -> "41"
            MediaCodecInfo.CodecProfileLevel.AV1Level42 -> "42"
            MediaCodecInfo.CodecProfileLevel.AV1Level43 -> "43"
            MediaCodecInfo.CodecProfileLevel.AV1Level5 -> "5"
            MediaCodecInfo.CodecProfileLevel.AV1Level51 -> "51"
            MediaCodecInfo.CodecProfileLevel.AV1Level52 -> "52"
            MediaCodecInfo.CodecProfileLevel.AV1Level53 -> "53"
            MediaCodecInfo.CodecProfileLevel.AV1Level6 -> "6"
            MediaCodecInfo.CodecProfileLevel.AV1Level61 -> "61"
            MediaCodecInfo.CodecProfileLevel.AV1Level62 -> "62"
            MediaCodecInfo.CodecProfileLevel.AV1Level63 -> "63"
            MediaCodecInfo.CodecProfileLevel.AV1Level7 -> "7"
            MediaCodecInfo.CodecProfileLevel.AV1Level71 -> "71"
            MediaCodecInfo.CodecProfileLevel.AV1Level72 -> "72"
            MediaCodecInfo.CodecProfileLevel.AV1Level73 -> "73"
            else -> "?"
        }
        return profile to level
    }

    private fun getAVCProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AVCProfileBaseline -> "Baseline"
            MediaCodecInfo.CodecProfileLevel.AVCProfileMain -> "Main"
            MediaCodecInfo.CodecProfileLevel.AVCProfileExtended -> "Extended"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh -> "High"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh10 -> "High10"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh422 -> "High422"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh444 -> "High444"
            MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedBaseline -> "ConstrainedBaseline"
            MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedHigh -> "ConstrainedHigh"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.AVCLevel1 -> "1"
            MediaCodecInfo.CodecProfileLevel.AVCLevel11 -> "11"
            MediaCodecInfo.CodecProfileLevel.AVCLevel12 -> "12"
            MediaCodecInfo.CodecProfileLevel.AVCLevel13 -> "13"
            MediaCodecInfo.CodecProfileLevel.AVCLevel1b -> "1b"
            MediaCodecInfo.CodecProfileLevel.AVCLevel2 -> "2"
            MediaCodecInfo.CodecProfileLevel.AVCLevel21 -> "21"
            MediaCodecInfo.CodecProfileLevel.AVCLevel22 -> "22"
            MediaCodecInfo.CodecProfileLevel.AVCLevel3 -> "3"
            MediaCodecInfo.CodecProfileLevel.AVCLevel31 -> "31"
            MediaCodecInfo.CodecProfileLevel.AVCLevel32 -> "32"
            MediaCodecInfo.CodecProfileLevel.AVCLevel4 -> "4"
            MediaCodecInfo.CodecProfileLevel.AVCLevel41 -> "41"
            MediaCodecInfo.CodecProfileLevel.AVCLevel42 -> "42"
            MediaCodecInfo.CodecProfileLevel.AVCLevel5 -> "5"
            MediaCodecInfo.CodecProfileLevel.AVCLevel51 -> "51"
            MediaCodecInfo.CodecProfileLevel.AVCLevel52 -> "52"
            MediaCodecInfo.CodecProfileLevel.AVCLevel6 -> "6"
            MediaCodecInfo.CodecProfileLevel.AVCLevel61 -> "61"
            MediaCodecInfo.CodecProfileLevel.AVCLevel62 -> "62"
            else -> "?"
        }
        return profile to level
    }

    private fun getHEVCProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain -> "Main"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10 -> "Main10"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMainStill -> "MainStill"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10 -> "Main10HDR10"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10Plus -> "Main10HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel1 -> "HighTierLevel1"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel2 -> "HighTierLevel2"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel2 -> "HighTierLevel2"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel21 -> "HighTierLevel21"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel21 -> "HighTierLevel21"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel3 -> "HighTierLevel3"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel3 -> "HighTierLevel3"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel31 -> "HighTierLevel31"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel31 -> "HighTierLevel31"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel4 -> "HighTierLevel4"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel4 -> "HighTierLevel4"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel41 -> "HighTierLevel41"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel41 -> "HighTierLevel41"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel5 -> "HighTierLevel5"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel5 -> "HighTierLevel5"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel51 -> "HighTierLevel51"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel51 -> "HighTierLevel51"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel52 -> "HighTierLevel52"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel52 -> "HighTierLevel52"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel6 -> "HighTierLevel6"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel6 -> "HighTierLevel6"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel61 -> "HighTierLevel61"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel61 -> "HighTierLevel61"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel62 -> "HighTierLevel62"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel62 -> "HighTierLevel62"
            else -> "?"
        }
        return profile to level
    }

    private fun getMPEG4ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimple -> "Simple"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleScalable -> "SimpleScalable"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileCore -> "Core"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileMain -> "Main"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileNbit -> "Nbit"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileScalableTexture -> "ScalableTexture"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleFace -> "SimpleFace"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleFBA -> "SimpleFBA"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileBasicAnimated -> "BasicAnimated"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileHybrid -> "Hybrid"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedRealTime -> "AdvancedRealTime"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileCoreScalable -> "CoreScalable"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedCoding -> "AdvancedCoding"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedCore -> "AdvancedCore"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedScalable -> "AdvancedScalable"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedSimple -> "AdvancedSimple"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.MPEG4Level0 -> "0"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level0b -> "0b"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level1 -> "1"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level2 -> "2"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level3 -> "3"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level3b -> "3b"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level4 -> "4"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level4a -> "4a"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level5 -> "5"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level6 -> "6"
            else -> "?"
        }
        return profile to level
    }

    private fun getH263ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.H263ProfileBaseline -> "Baseline"
            MediaCodecInfo.CodecProfileLevel.H263ProfileH320Coding -> "H320Coding"
            MediaCodecInfo.CodecProfileLevel.H263ProfileBackwardCompatible -> "BackwardCompatible"
            MediaCodecInfo.CodecProfileLevel.H263ProfileISWV2 -> "ISWV2"
            MediaCodecInfo.CodecProfileLevel.H263ProfileISWV3 -> "ISWV3"
            MediaCodecInfo.CodecProfileLevel.H263ProfileHighCompression -> "HighCompression"
            MediaCodecInfo.CodecProfileLevel.H263ProfileInternet -> "Internet"
            MediaCodecInfo.CodecProfileLevel.H263ProfileInterlace -> "Interlace"
            MediaCodecInfo.CodecProfileLevel.H263ProfileHighLatency -> "HighLatency"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.H263Level10 -> "10"
            MediaCodecInfo.CodecProfileLevel.H263Level20 -> "20"
            MediaCodecInfo.CodecProfileLevel.H263Level30 -> "30"
            MediaCodecInfo.CodecProfileLevel.H263Level40 -> "40"
            MediaCodecInfo.CodecProfileLevel.H263Level45 -> "45"
            MediaCodecInfo.CodecProfileLevel.H263Level50 -> "50"
            MediaCodecInfo.CodecProfileLevel.H263Level60 -> "60"
            MediaCodecInfo.CodecProfileLevel.H263Level70 -> "70"
            else -> "?"
        }
        return profile to level
    }

    private fun getMPEG2ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSimple -> "Simple"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileMain -> "Main"
            MediaCodecInfo.CodecProfileLevel.MPEG2Profile422 -> "422"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSNR -> "SNR"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSpatial -> "Spatial"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileHigh -> "High"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelLL -> "LL"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelML -> "ML"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelH14 -> "H14"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelHL -> "HL"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelHP -> "HP"
            else -> "?"
        }
        return profile to level
    }

    private fun getDOLBY_VISIONProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavPer -> "DvavPer"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavPen -> "DvavPen"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDer -> "DvheDer"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDen -> "DvheDen"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDtr -> "DvheDtr"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheStn -> "DvheStn"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDth -> "DvheDth"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDtb -> "DvheDtb"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheSt -> "DvheSt"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavSe -> "DvavSe"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvav110 -> "Dvav110"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelHd24 -> "Hd24"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelHd30 -> "Hd30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd24 -> "Fhd24"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd30 -> "Fhd30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd60 -> "Fhd60"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd24 -> "Uhd24"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd30 -> "Uhd30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd48 -> "Uhd48"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd60 -> "Uhd60"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd120 -> "Uhd120"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevel8k30 -> "8k30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevel8k60 -> "8k60"
            else -> "?"
        }
        return profile to level
    }

    private fun getAACProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AACObjectMain -> "Main"
            MediaCodecInfo.CodecProfileLevel.AACObjectLC -> "LC"
            MediaCodecInfo.CodecProfileLevel.AACObjectSSR -> "SSR"
            MediaCodecInfo.CodecProfileLevel.AACObjectLTP -> "LTP"
            MediaCodecInfo.CodecProfileLevel.AACObjectHE -> "HE"
            MediaCodecInfo.CodecProfileLevel.AACObjectScalable -> "Scalable"
            MediaCodecInfo.CodecProfileLevel.AACObjectERLC -> "ERLC"
            MediaCodecInfo.CodecProfileLevel.AACObjectERScalable -> "ERScalable"
            MediaCodecInfo.CodecProfileLevel.AACObjectLD -> "LD"
            MediaCodecInfo.CodecProfileLevel.AACObjectHE_PS -> "HE_PS"
            MediaCodecInfo.CodecProfileLevel.AACObjectELD -> "ELD"
            MediaCodecInfo.CodecProfileLevel.AACObjectXHE -> "XHE"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "-"
        }
        return profile to level
    }

    private fun getAC4ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AC4Profile00 -> "00"
            MediaCodecInfo.CodecProfileLevel.AC4Profile10 -> "10"
            MediaCodecInfo.CodecProfileLevel.AC4Profile11 -> "11"
            MediaCodecInfo.CodecProfileLevel.AC4Profile21 -> "21"
            MediaCodecInfo.CodecProfileLevel.AC4Profile22 -> "22"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.AC4Level0 -> "0"
            MediaCodecInfo.CodecProfileLevel.AC4Level1 -> "1"
            MediaCodecInfo.CodecProfileLevel.AC4Level2 -> "2"
            MediaCodecInfo.CodecProfileLevel.AC4Level3 -> "3"
            MediaCodecInfo.CodecProfileLevel.AC4Level4 -> "4"
            else -> "?"
        }
        return profile to level
    }

    private fun getDTS_HDProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.DTS_HDProfileHRA -> "HRA"
            MediaCodecInfo.CodecProfileLevel.DTS_HDProfileLBR -> "LBR"
            MediaCodecInfo.CodecProfileLevel.DTS_HDProfileMA -> "MA"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "-"
        }
        return profile to level
    }

    private fun getDTS_UHDProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.DTS_UHDProfileP1 -> "P1"
            MediaCodecInfo.CodecProfileLevel.DTS_UHDProfileP2 -> "P2"
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

    private fun String.getCodecSuffix() = split('/').last()
}