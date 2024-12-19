package io.igrvlhlb.codeci.ui.screens

import android.media.MediaCodecInfo
import android.media.MediaCodecList
import android.os.Build
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codeci.ui.main.CodecsViewModel
import com.example.codeci.utils.isSoftwareCodec
import io.igrvlhlb.codeci.utils.CodecConstantsMapper.colorFormatToString
import io.igrvlhlb.codeci.utils.CodecConstantsMapper.profileLevelToString

@Composable
fun CodecInfoScreen(viewModel: CodecsViewModel, innerPadding: PaddingValues) {
    val codec = viewModel.selectedCodec
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        InfoSection("Codec Info") {
            Text(text = "Name: ${codec.name}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Supported Types: ${codec.supportedTypes?.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Is Software Codec: ${codec.isSoftwareCodec()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Is Encoder: ${codec.isEncoder}",
                style = MaterialTheme.typography.bodyMedium
            )
            if (Build.VERSION.SDK_INT >= 29) {
                Text(
                    text = "Is Hardware Accelerated: ${codec.isHardwareAccelerated}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Canonical Name: ${codec.canonicalName}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Is Software Only: ${codec.isSoftwareOnly}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Is Vendor: ${codec.isVendor}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        codec.supportedTypes.map {
            CodecCapabilitiesView(
                codec.getCapabilitiesForType(it),
                codec.isEncoder
            )
        }
    }
}

@Composable
fun InfoSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Row {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, style = MaterialTheme.typography.titleMedium)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            content()
        }
    }
}

@Composable
fun CodecCapabilitiesView(capabilities: MediaCodecInfo.CodecCapabilities, isEncoder: Boolean) {
    InfoSection("Capabilities (${capabilities.mimeType})") {
        GeneralCodecCapabilitiesView(capabilities)
        val audioCapabilities = capabilities.audioCapabilities
        val videoCapabilities = capabilities.videoCapabilities
        val encoderCapabilities = capabilities.encoderCapabilities
        if (audioCapabilities != null) {
            AudioCodecCapabilitiesView(capabilities.audioCapabilities)
        }
        if (videoCapabilities != null) {
            VideoCodecCapabilitiesView(capabilities.videoCapabilities)
        }
        if (encoderCapabilities != null) {
            EncoderCodecCapabilitiesView(capabilities.encoderCapabilities)
        }
    }
}

@Composable
fun GeneralCodecCapabilitiesView(capabilities: MediaCodecInfo.CodecCapabilities) {
    InfoSection("General Capabilities") {
        Text(
            text = "Mime Type: ${capabilities.mimeType}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Default Format: ${capabilities.defaultFormat}",
            style = MaterialTheme.typography.bodyMedium
        )
        Row (modifier = Modifier.fillMaxWidth()) {
            val colorFormats = capabilities.colorFormats.map { colorFormatToString(it) }
            Text(
                text = "Color Formats:${if (colorFormats.isEmpty()) " -" else ""}",
                style = MaterialTheme.typography.bodyMedium
            )
            if (colorFormats.isNotEmpty()) {
                Spacer(Modifier.size(16.dp))
                LazyColumn(Modifier.sizeIn(maxHeight = 48.dp)) {
                    items(colorFormats) { colorFormat ->
                        Text(
                            text = colorFormat,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
        Row (modifier = Modifier.fillMaxWidth()) {
            val profileLevels = capabilities.profileLevels.map { profileLevelToString(capabilities.mimeType, it) }
            Text(
                text = "Profile Levels: ${if (profileLevels.isEmpty()) " -" else ""}",
                style = MaterialTheme.typography.bodyMedium
            )
            if (profileLevels.isNotEmpty()) {
                Spacer(Modifier.size(16.dp))
                LazyColumn(Modifier.sizeIn(maxHeight = 48.dp)) {
                    items(profileLevels) { profileLevel ->
                        Text(
                            text = profileLevel.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
        Text(
            text = "Max Supported Instances: ${capabilities.maxSupportedInstances}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AudioCodecCapabilitiesView(capabilities: MediaCodecInfo.AudioCapabilities) {
    InfoSection("Audio Capabilities") {
        Text(text = "Bitrate Range: ${capabilities.bitrateRange}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Max Input Channel Count: ${capabilities.maxInputChannelCount}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Supported Sample Rates: ${capabilities.supportedSampleRates?.toList() ?: "-"}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Supported Sample Rate Ranges: ${capabilities.supportedSampleRateRanges?.map { it.lower to it.upper } ?: "-"}}", style = MaterialTheme.typography.bodyMedium)
        if (Build.VERSION.SDK_INT >= 31) {
            Text(text = "Min Input Channel Count: ${capabilities.minInputChannelCount}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Input Channel Count Ranges: ${capabilities.inputChannelCountRanges.map { it.lower to it.upper }}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun VideoCodecCapabilitiesView(capabilities: MediaCodecInfo.VideoCapabilities) {
    InfoSection("Video Capabilities") {
        Text(text = "Bitrate Range: ${capabilities.bitrateRange}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Frame Rates: ${capabilities.supportedFrameRates}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Width Range: ${capabilities.supportedWidths}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Height Range: ${capabilities.supportedHeights}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Width Alignment: ${capabilities.widthAlignment}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Height Alignment: ${capabilities.heightAlignment}", style = MaterialTheme.typography.bodyMedium)
        if (Build.VERSION.SDK_INT >= 29) {
            Text(text = "Supported Performance Points: ${capabilities.supportedPerformancePoints?.toList() ?: "-"}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun EncoderCodecCapabilitiesView(capabilities: MediaCodecInfo.EncoderCapabilities) {
    val modes = mutableMapOf("Constant (CBR)" to MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR,
        "Constant Quality (CQ)" to MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CQ,
        "Variable (VBR)" to MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_VBR
    )
    if (Build.VERSION.SDK_INT >= 31) {
        modes["Constant with Frame Drops (CBR_FD)"] = MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR_FD
    }

    InfoSection("Encoder Capabilities") {
        if (Build.VERSION.SDK_INT >= 29) {
            Text(text = "Quality Range: ${capabilities.qualityRange}", style = MaterialTheme.typography.bodyMedium)
        }
        Text(text = "Complexity Range: ${capabilities.complexityRange}", style = MaterialTheme.typography.bodyMedium)
        Text("Bitrate Modes", style = MaterialTheme.typography.titleSmall)
        modes.forEach { (label, mode) ->
            Text(
                text = "$label: ${capabilities.isBitrateModeSupported(mode)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CodecInfoScreenPreview() {
    val viewModel = CodecsViewModel()
    viewModel.selectedCodec = viewModel.allCodecsSet.first()
    CodecInfoScreen(viewModel = viewModel, innerPadding = PaddingValues(16.dp))
}

@Preview(showBackground = true)
@Composable
fun VideoCapabilities() {
    val codecs = MediaCodecList(MediaCodecList.ALL_CODECS).codecInfos
    val videoCodec = codecs.find { codec -> codec.supportedTypes.any { type -> type.startsWith("video/") } }
    VideoCodecCapabilitiesView(videoCodec!!.getCapabilitiesForType(videoCodec!!.supportedTypes[0]).videoCapabilities)
}