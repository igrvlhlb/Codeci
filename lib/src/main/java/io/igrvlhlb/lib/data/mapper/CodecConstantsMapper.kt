package io.igrvlhlb.lib.data.mapper

import android.media.MediaCodecInfo
import android.media.MediaFormat
import io.igrvlhlb.lib.data.ColorFormat

object CodecConstantsMapper {
    fun colorFormatToString(format: Int): String {
        return ColorFormat.from(format)?.formatName ?: "Unknown"
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
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.VP8ProfileMain -> "VP8ProfileMain"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version0 -> "VP8Level_Version0"
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version1 -> "VP8Level_Version1"
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version2 -> "VP8Level_Version2"
            MediaCodecInfo.CodecProfileLevel.VP8Level_Version3 -> "VP8Level_Version3"
            else -> "?"
        }
        return profile to level
    }

    private fun getVP9ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.VP9Profile0 -> "VP9Profile0"
            MediaCodecInfo.CodecProfileLevel.VP9Profile1 -> "VP9Profile1"
            MediaCodecInfo.CodecProfileLevel.VP9Profile2 -> "VP9Profile2"
            MediaCodecInfo.CodecProfileLevel.VP9Profile3 -> "VP9Profile3"
            MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR -> "VP9Profile2HDR"
            MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR -> "VP9Profile3HDR"
            MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR10Plus -> "VP9Profile2HDR10Plus"
            MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR10Plus -> "VP9Profile3HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.VP9Level1 -> "VP9Level1"
            MediaCodecInfo.CodecProfileLevel.VP9Level11 -> "VP9Level11"
            MediaCodecInfo.CodecProfileLevel.VP9Level2 -> "VP9Level2"
            MediaCodecInfo.CodecProfileLevel.VP9Level21 -> "VP9Level21"
            MediaCodecInfo.CodecProfileLevel.VP9Level3 -> "VP9Level3"
            MediaCodecInfo.CodecProfileLevel.VP9Level31 -> "VP9Level31"
            MediaCodecInfo.CodecProfileLevel.VP9Level4 -> "VP9Level4"
            MediaCodecInfo.CodecProfileLevel.VP9Level41 -> "VP9Level41"
            MediaCodecInfo.CodecProfileLevel.VP9Level5 -> "VP9Level5"
            MediaCodecInfo.CodecProfileLevel.VP9Level51 -> "VP9Level51"
            MediaCodecInfo.CodecProfileLevel.VP9Level52 -> "VP9Level52"
            MediaCodecInfo.CodecProfileLevel.VP9Level6 -> "VP9Level6"
            MediaCodecInfo.CodecProfileLevel.VP9Level61 -> "VP9Level61"
            MediaCodecInfo.CodecProfileLevel.VP9Level62 -> "VP9Level62"
            else -> "?"
        }
        return profile to level
    }

    private fun getAV1ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain8 -> "AV1ProfileMain8"
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10 -> "AV1ProfileMain10"
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10 -> "AV1ProfileMain10HDR10"
            MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10Plus -> "AV1ProfileMain10HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.AV1Level2 -> "AV1Level2"
            MediaCodecInfo.CodecProfileLevel.AV1Level21 -> "AV1Level21"
            MediaCodecInfo.CodecProfileLevel.AV1Level22 -> "AV1Level22"
            MediaCodecInfo.CodecProfileLevel.AV1Level23 -> "AV1Level23"
            MediaCodecInfo.CodecProfileLevel.AV1Level3 -> "AV1Level3"
            MediaCodecInfo.CodecProfileLevel.AV1Level31 -> "AV1Level31"
            MediaCodecInfo.CodecProfileLevel.AV1Level32 -> "AV1Level32"
            MediaCodecInfo.CodecProfileLevel.AV1Level33 -> "AV1Level33"
            MediaCodecInfo.CodecProfileLevel.AV1Level4 -> "AV1Level4"
            MediaCodecInfo.CodecProfileLevel.AV1Level41 -> "AV1Level41"
            MediaCodecInfo.CodecProfileLevel.AV1Level42 -> "AV1Level42"
            MediaCodecInfo.CodecProfileLevel.AV1Level43 -> "AV1Level43"
            MediaCodecInfo.CodecProfileLevel.AV1Level5 -> "AV1Level5"
            MediaCodecInfo.CodecProfileLevel.AV1Level51 -> "AV1Level51"
            MediaCodecInfo.CodecProfileLevel.AV1Level52 -> "AV1Level52"
            MediaCodecInfo.CodecProfileLevel.AV1Level53 -> "AV1Level53"
            MediaCodecInfo.CodecProfileLevel.AV1Level6 -> "AV1Level6"
            MediaCodecInfo.CodecProfileLevel.AV1Level61 -> "AV1Level61"
            MediaCodecInfo.CodecProfileLevel.AV1Level62 -> "AV1Level62"
            MediaCodecInfo.CodecProfileLevel.AV1Level63 -> "AV1Level63"
            MediaCodecInfo.CodecProfileLevel.AV1Level7 -> "AV1Level7"
            MediaCodecInfo.CodecProfileLevel.AV1Level71 -> "AV1Level71"
            MediaCodecInfo.CodecProfileLevel.AV1Level72 -> "AV1Level72"
            MediaCodecInfo.CodecProfileLevel.AV1Level73 -> "AV1Level73"
            else -> "?"
        }
        return profile to level
    }

    private fun getAVCProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AVCProfileBaseline -> "AVCProfileBaseline"
            MediaCodecInfo.CodecProfileLevel.AVCProfileMain -> "AVCProfileMain"
            MediaCodecInfo.CodecProfileLevel.AVCProfileExtended -> "AVCProfileExtended"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh -> "AVCProfileHigh"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh10 -> "AVCProfileHigh10"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh422 -> "AVCProfileHigh422"
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh444 -> "AVCProfileHigh444"
            MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedBaseline -> "AVCProfileConstrainedBaseline"
            MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedHigh -> "AVCProfileConstrainedHigh"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.AVCLevel1 -> "AVCLevel1"
            MediaCodecInfo.CodecProfileLevel.AVCLevel11 -> "AVCLevel11"
            MediaCodecInfo.CodecProfileLevel.AVCLevel12 -> "AVCLevel12"
            MediaCodecInfo.CodecProfileLevel.AVCLevel13 -> "AVCLevel13"
            MediaCodecInfo.CodecProfileLevel.AVCLevel1b -> "AVCLevel1b"
            MediaCodecInfo.CodecProfileLevel.AVCLevel2 -> "AVCLevel2"
            MediaCodecInfo.CodecProfileLevel.AVCLevel21 -> "AVCLevel21"
            MediaCodecInfo.CodecProfileLevel.AVCLevel22 -> "AVCLevel22"
            MediaCodecInfo.CodecProfileLevel.AVCLevel3 -> "AVCLevel3"
            MediaCodecInfo.CodecProfileLevel.AVCLevel31 -> "AVCLevel31"
            MediaCodecInfo.CodecProfileLevel.AVCLevel32 -> "AVCLevel32"
            MediaCodecInfo.CodecProfileLevel.AVCLevel4 -> "AVCLevel4"
            MediaCodecInfo.CodecProfileLevel.AVCLevel41 -> "AVCLevel41"
            MediaCodecInfo.CodecProfileLevel.AVCLevel42 -> "AVCLevel42"
            MediaCodecInfo.CodecProfileLevel.AVCLevel5 -> "AVCLevel5"
            MediaCodecInfo.CodecProfileLevel.AVCLevel51 -> "AVCLevel51"
            MediaCodecInfo.CodecProfileLevel.AVCLevel52 -> "AVCLevel52"
            MediaCodecInfo.CodecProfileLevel.AVCLevel6 -> "AVCLevel6"
            MediaCodecInfo.CodecProfileLevel.AVCLevel61 -> "AVCLevel61"
            MediaCodecInfo.CodecProfileLevel.AVCLevel62 -> "AVCLevel62"
            else -> "?"
        }
        return profile to level
    }

    private fun getHEVCProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain -> "HEVCProfileMain"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10 -> "HEVCProfileMain10"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMainStill -> "HEVCProfileMainStill"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10 -> "HEVCProfileMain10HDR10"
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10Plus -> "HEVCProfileMain10HDR10Plus"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel1 -> "HEVCHighTierLevel1"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel2 -> "HEVCMainTierLevel2"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel2 -> "HEVCHighTierLevel2"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel21 -> "HEVCMainTierLevel21"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel21 -> "HEVCHighTierLevel21"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel3 -> "HEVCMainTierLevel3"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel3 -> "HEVCHighTierLevel3"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel31 -> "HEVCMainTierLevel31"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel31 -> "HEVCHighTierLevel31"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel4 -> "HEVCMainTierLevel4"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel4 -> "HEVCHighTierLevel4"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel41 -> "HEVCMainTierLevel41"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel41 -> "HEVCHighTierLevel41"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel5 -> "HEVCMainTierLevel5"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel5 -> "HEVCHighTierLevel5"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel51 -> "HEVCMainTierLevel51"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel51 -> "HEVCHighTierLevel51"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel52 -> "HEVCMainTierLevel52"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel52 -> "HEVCHighTierLevel52"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel6 -> "HEVCMainTierLevel6"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel6 -> "HEVCHighTierLevel6"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel61 -> "HEVCMainTierLevel61"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel61 -> "HEVCHighTierLevel61"
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel62 -> "HEVCMainTierLevel62"
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel62 -> "HEVCHighTierLevel62"
            else -> "?"
        }
        return profile to level
    }

    private fun getMPEG4ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimple -> "MPEG4ProfileSimple"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleScalable -> "MPEG4ProfileSimpleScalable"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileCore -> "MPEG4ProfileCore"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileMain -> "MPEG4ProfileMain"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileNbit -> "MPEG4ProfileNbit"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileScalableTexture -> "MPEG4ProfileScalableTexture"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleFace -> "MPEG4ProfileSimpleFace"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileSimpleFBA -> "MPEG4ProfileSimpleFBA"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileBasicAnimated -> "MPEG4ProfileBasicAnimated"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileHybrid -> "MPEG4ProfileHybrid"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedRealTime -> "MPEG4ProfileAdvancedRealTime"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileCoreScalable -> "MPEG4ProfileCoreScalable"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedCoding -> "MPEG4ProfileAdvancedCoding"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedCore -> "MPEG4ProfileAdvancedCore"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedScalable -> "MPEG4ProfileAdvancedScalable"
            MediaCodecInfo.CodecProfileLevel.MPEG4ProfileAdvancedSimple -> "MPEG4ProfileAdvancedSimple"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.MPEG4Level0 -> "MPEG4Level0"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level0b -> "MPEG4Level0b"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level1 -> "MPEG4Level1"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level2 -> "MPEG4Level2"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level3 -> "MPEG4Level3"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level3b -> "MPEG4Level3b"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level4 -> "MPEG4Level4"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level4a -> "MPEG4Level4a"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level5 -> "MPEG4Level5"
            MediaCodecInfo.CodecProfileLevel.MPEG4Level6 -> "MPEG4Level6"
            else -> "?"
        }
        return profile to level
    }

    private fun getH263ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.H263ProfileBaseline -> "H263ProfileBaseline"
            MediaCodecInfo.CodecProfileLevel.H263ProfileH320Coding -> "H263ProfileH320Coding"
            MediaCodecInfo.CodecProfileLevel.H263ProfileBackwardCompatible -> "H263ProfileBackwardCompatible"
            MediaCodecInfo.CodecProfileLevel.H263ProfileISWV2 -> "H263ProfileISWV2"
            MediaCodecInfo.CodecProfileLevel.H263ProfileISWV3 -> "H263ProfileISWV3"
            MediaCodecInfo.CodecProfileLevel.H263ProfileHighCompression -> "H263ProfileHighCompression"
            MediaCodecInfo.CodecProfileLevel.H263ProfileInternet -> "H263ProfileInternet"
            MediaCodecInfo.CodecProfileLevel.H263ProfileInterlace -> "H263ProfileInterlace"
            MediaCodecInfo.CodecProfileLevel.H263ProfileHighLatency -> "H263ProfileHighLatency"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.H263Level10 -> "H263Level10"
            MediaCodecInfo.CodecProfileLevel.H263Level20 -> "H263Level20"
            MediaCodecInfo.CodecProfileLevel.H263Level30 -> "H263Level30"
            MediaCodecInfo.CodecProfileLevel.H263Level40 -> "H263Level40"
            MediaCodecInfo.CodecProfileLevel.H263Level45 -> "H263Level45"
            MediaCodecInfo.CodecProfileLevel.H263Level50 -> "H263Level50"
            MediaCodecInfo.CodecProfileLevel.H263Level60 -> "H263Level60"
            MediaCodecInfo.CodecProfileLevel.H263Level70 -> "H263Level70"
            else -> "?"
        }
        return profile to level
    }

    private fun getMPEG2ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSimple -> "MPEG2ProfileSimple"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileMain -> "MPEG2ProfileMain"
            MediaCodecInfo.CodecProfileLevel.MPEG2Profile422 -> "MPEG2Profile422"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSNR -> "MPEG2ProfileSNR"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileSpatial -> "MPEG2ProfileSpatial"
            MediaCodecInfo.CodecProfileLevel.MPEG2ProfileHigh -> "MPEG2ProfileHigh"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelLL -> "MPEG2LevelLL"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelML -> "MPEG2LevelML"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelH14 -> "MPEG2LevelH14"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelHL -> "MPEG2LevelHL"
            MediaCodecInfo.CodecProfileLevel.MPEG2LevelHP -> "MPEG2LevelHP"
            else -> "?"
        }
        return profile to level
    }

    private fun getDOLBY_VISIONProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavPer -> "DolbyVisionProfileDvavPer"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavPen -> "DolbyVisionProfileDvavPen"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDer -> "DolbyVisionProfileDvheDer"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDen -> "DolbyVisionProfileDvheDen"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDtr -> "DolbyVisionProfileDvheDtr"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheStn -> "DolbyVisionProfileDvheStn"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDth -> "DolbyVisionProfileDvheDth"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheDtb -> "DolbyVisionProfileDvheDtb"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvheSt -> "DolbyVisionProfileDvheSt"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvavSe -> "DolbyVisionProfileDvavSe"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionProfileDvav110 -> "DolbyVisionProfileDvav110"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelHd24 -> "DolbyVisionLevelHd24"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelHd30 -> "DolbyVisionLevelHd30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd24 -> "DolbyVisionLevelFhd24"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd30 -> "DolbyVisionLevelFhd30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelFhd60 -> "DolbyVisionLevelFhd60"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd24 -> "DolbyVisionLevelUhd24"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd30 -> "DolbyVisionLevelUhd30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd48 -> "DolbyVisionLevelUhd48"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd60 -> "DolbyVisionLevelUhd60"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevelUhd120 -> "DolbyVisionLevelUhd120"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevel8k30 -> "DolbyVisionLevel8k30"
            MediaCodecInfo.CodecProfileLevel.DolbyVisionLevel8k60 -> "DolbyVisionLevel8k60"
            else -> "?"
        }
        return profile to level
    }

    private fun getAACProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AACObjectMain -> "AACObjectMain"
            MediaCodecInfo.CodecProfileLevel.AACObjectLC -> "AACObjectLC"
            MediaCodecInfo.CodecProfileLevel.AACObjectSSR -> "AACObjectSSR"
            MediaCodecInfo.CodecProfileLevel.AACObjectLTP -> "AACObjectLTP"
            MediaCodecInfo.CodecProfileLevel.AACObjectHE -> "AACObjectHE"
            MediaCodecInfo.CodecProfileLevel.AACObjectScalable -> "AACObjectScalable"
            MediaCodecInfo.CodecProfileLevel.AACObjectERLC -> "AACObjectERLC"
            MediaCodecInfo.CodecProfileLevel.AACObjectERScalable -> "AACObjectERScalable"
            MediaCodecInfo.CodecProfileLevel.AACObjectLD -> "AACObjectLD"
            MediaCodecInfo.CodecProfileLevel.AACObjectHE_PS -> "AACObjectHE_PS"
            MediaCodecInfo.CodecProfileLevel.AACObjectELD -> "AACObjectELD"
            MediaCodecInfo.CodecProfileLevel.AACObjectXHE -> "AACObjectXHE"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "-"
        }
        return profile to level
    }

    private fun getAC4ProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.AC4Profile00 -> "AC4Profile00"
            MediaCodecInfo.CodecProfileLevel.AC4Profile10 -> "AC4Profile10"
            MediaCodecInfo.CodecProfileLevel.AC4Profile11 -> "AC4Profile11"
            MediaCodecInfo.CodecProfileLevel.AC4Profile21 -> "AC4Profile21"
            MediaCodecInfo.CodecProfileLevel.AC4Profile22 -> "AC4Profile22"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            MediaCodecInfo.CodecProfileLevel.AC4Level0 -> "AC4Level0"
            MediaCodecInfo.CodecProfileLevel.AC4Level1 -> "AC4Level1"
            MediaCodecInfo.CodecProfileLevel.AC4Level2 -> "AC4Level2"
            MediaCodecInfo.CodecProfileLevel.AC4Level3 -> "AC4Level3"
            MediaCodecInfo.CodecProfileLevel.AC4Level4 -> "AC4Level4"
            else -> "?"
        }
        return profile to level
    }

    private fun getDTS_HDProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.DTS_HDProfileHRA -> "DTS_HDProfileHRA"
            MediaCodecInfo.CodecProfileLevel.DTS_HDProfileLBR -> "DTS_HDProfileLBR"
            MediaCodecInfo.CodecProfileLevel.DTS_HDProfileMA -> "DTS_HDProfileMA"
            else -> "?"
        }
        val level = when (profileLevel.level) {
            else -> "-"
        }
        return profile to level
    }

    private fun getDTS_UHDProfileLevel(profileLevel: MediaCodecInfo.CodecProfileLevel): Pair<String, String> {
        val profile = when (profileLevel.profile) {
            MediaCodecInfo.CodecProfileLevel.DTS_UHDProfileP1 -> "DTS_UHDProfileP1"
            MediaCodecInfo.CodecProfileLevel.DTS_UHDProfileP2 -> "DTS_UHDProfileP2"
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
