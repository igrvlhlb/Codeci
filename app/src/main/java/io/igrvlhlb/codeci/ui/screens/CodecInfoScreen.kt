package io.igrvlhlb.codeci.ui.screens

import android.media.MediaCodecInfo
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun CodecInfoScreen(viewModel: CodecsViewModel, innerPadding: PaddingValues) {
    val codec = viewModel.selectedCodec
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = "Codec Info", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Codec Name: ${codec.name}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Supported Types: ${codec.supportedTypes?.joinToString()}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Is Software Codec: ${codec.isSoftwareCodec()}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Is Encoder: ${codec.isEncoder}", style = MaterialTheme.typography.bodyMedium)
        if (Build.VERSION.SDK_INT >= 29) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Is Hardware Accelerated: ${codec.isHardwareAccelerated}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Canonical Name: ${codec.canonicalName}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Is Software Only: ${codec.isSoftwareOnly}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Is Vendor: ${codec.isVendor}", style = MaterialTheme.typography.bodyMedium)
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
fun CodecCapabilitiesView(capabilities: MediaCodecInfo.CodecCapabilities, isEncoder: Boolean) {
    GeneralCodecCapabilitiesView(capabilities)
    if (capabilities.mimeType.startsWith("audio/")) AudioCodecCapabilitiesView(capabilities.audioCapabilities)
    else if (capabilities.mimeType.startsWith("video/")) VideoCodecCapabilitiesView(capabilities.videoCapabilities)
    if (isEncoder) {
        EncoderCodecCapabilitiesView(capabilities.encoderCapabilities)
    }
}

@Composable
fun GeneralCodecCapabilitiesView(capabilities: MediaCodecInfo.CodecCapabilities) {
    Text(text = "General Capabilities", style = MaterialTheme.typography.titleSmall)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Default Format: ${capabilities.defaultFormat}", style = MaterialTheme.typography.bodyMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Mime Type: ${capabilities.mimeType}", style = MaterialTheme.typography.bodyMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Color Formats: ${capabilities.colorFormats}", style = MaterialTheme.typography.bodyMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Max Supported Instances: ${capabilities.maxSupportedInstances}", style = MaterialTheme.typography.bodyMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Profile Levels: ${capabilities.profileLevels}", style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun AudioCodecCapabilitiesView(capabilities: MediaCodecInfo.AudioCapabilities) {
    Column {
        Text(text = "Audio Capabilities", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Bitrate Range: ${capabilities.bitrateRange}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Max Input Channel Count: ${capabilities.maxInputChannelCount}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Supported Sample Rates: ${capabilities.supportedSampleRates}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Supported Sample Rate Ranges: ${capabilities.supportedSampleRateRanges}", style = MaterialTheme.typography.bodyMedium)
        if (Build.VERSION.SDK_INT >= 31) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Min Input Channel Count: ${capabilities.minInputChannelCount}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Input Channel Count Ranges: ${capabilities.inputChannelCountRanges}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun VideoCodecCapabilitiesView(capabilities: MediaCodecInfo.VideoCapabilities) {
    Column {
        Text(text = "Video Capabilities", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Bitrate Range: ${capabilities.bitrateRange}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Frame Rates: ${capabilities.supportedFrameRates}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Width Range: ${capabilities.supportedWidths}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Height Range: ${capabilities.supportedHeights}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Width Alignment: ${capabilities.widthAlignment}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Height Alignment: ${capabilities.heightAlignment}", style = MaterialTheme.typography.bodyMedium)
        if (Build.VERSION.SDK_INT >= 29) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Supported Performance Points: ${capabilities.supportedPerformancePoints}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun EncoderCodecCapabilitiesView(capabilities: MediaCodecInfo.EncoderCapabilities) {
//        capabilities.isBitrateModeSupported(MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR)

    // Constant quality mode  BITRATE_MODE_CQ = 0
    // Variable bitrate mode BITRATE_MODE_VBR = 1
    // Constant bitrate mode BITRATE_MODE_CBR = 2
    // Constant bitrate mode with frame drops BITRATE_MODE_CBR_FD = 3

    val modes = mapOf("Constant (CBR)" to MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR,
        "Constant with Frame Drops (CBR_FD)" to MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CBR_FD,
        "Constant Quality (CQ)" to MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_CQ,
        "Variable (VBR)" to MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_VBR
    )

    Column {
        Text(text = "Encoder Capabilities", style = MaterialTheme.typography.titleSmall)
        if (Build.VERSION.SDK_INT >= 29) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Quality Range: ${capabilities.qualityRange}", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Complexity Range: ${capabilities.complexityRange}", style = MaterialTheme.typography.bodyMedium)
        Text("Bitrate Modes", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        modes.forEach { (label, mode) ->
            Spacer(modifier = Modifier.height(8.dp))
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
    CodecInfoScreen(viewModel = CodecsViewModel(), innerPadding = PaddingValues(16.dp))
}