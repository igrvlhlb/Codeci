package io.igrvlhlb.codeci.ui.screens

import android.media.MediaCodecList
import android.os.Build
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codeci.ui.main.CodecsViewModel
import io.igrvlhlb.codeci.ui.composables.VerticalLazyListScrollBar
import io.igrvlhlb.lib.codeci.utils.ifZeroThen
import io.igrvlhlb.lib.data.AudioCapabilitiesInfo
import io.igrvlhlb.lib.data.BitrateMode
import io.igrvlhlb.lib.data.CodecCapabilitiesInfo
import io.igrvlhlb.lib.data.EncoderCapabilitiesInfo
import io.igrvlhlb.lib.data.VideoCapabilitiesInfo
import io.igrvlhlb.lib.data.extractor.CodecInfoExtractor
import io.igrvlhlb.lib.data.formatter.CodecInfoFormatter
import my.nanihadesuka.compose.LazyColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings

@Composable
fun CodecInfoScreen(viewModel: CodecsViewModel, innerPadding: PaddingValues) {
    val codec = viewModel.codecInfo
    val scrollState = rememberScrollState()
    println(CodecInfoFormatter.formatCodecInfo(codec))
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
            SafeText("Supported Types", codec.supportedTypes.joinToString())
            Text(
                text = "Is Software Codec: ${codec.isSoftwareCodec}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Is Encoder: ${codec.isEncoder}",
                style = MaterialTheme.typography.bodyMedium
            )
            SafeText("Is Hardware Accelerated", codec.basicInfo.isHardwareAccelerated?.toString())
            SafeText("Canonical Name", codec.basicInfo.canonicalName)
            SafeText("Is Software Only", codec.basicInfo.isSoftwareOnly?.toString())
            SafeText("Is Vendor", codec.basicInfo.isVendor?.toString())

        }

        codec.capabilities.forEach {
            CodecCapabilitiesView(
                it,
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
fun CodecCapabilitiesView(capabilities: CodecCapabilitiesInfo, isEncoder: Boolean) {
    InfoSection("Capabilities (${capabilities.mimeType})") {
        GeneralCodecCapabilitiesView(capabilities)
        val audioCapabilities = capabilities.audioCapabilities
        val videoCapabilities = capabilities.videoCapabilities
        val encoderCapabilities = capabilities.encoderCapabilities
        audioCapabilities?.let{ AudioCodecCapabilitiesView(it) }
        videoCapabilities?.let { VideoCodecCapabilitiesView(it) }
        encoderCapabilities?.let{ EncoderCodecCapabilitiesView(it) }
    }
}

@Composable
fun GeneralCodecCapabilitiesView(capabilities: CodecCapabilitiesInfo) {
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
            val colorFormats = capabilities.colorFormats
            Text(
                text = "Color Formats:${if (colorFormats.isEmpty()) " -" else ""}",
                style = MaterialTheme.typography.bodyMedium
            )
            if (colorFormats.isNotEmpty()) {
                Spacer(Modifier.size(16.dp))
                VerticalLazyListScrollBar (scrollBarModifier = Modifier.sizeIn(maxHeight = 48.dp)) {
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
            val profileLevels = capabilities.profileLevels
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
fun AudioCodecCapabilitiesView(capabilities: AudioCapabilitiesInfo) {
    InfoSection("Audio Capabilities") {
        Text(text = "Bitrate Range: ${capabilities.bitrateRange}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Max Input Channel Count: ${capabilities.maxInputChannelCount}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Supported Sample Rates: ${capabilities.supportedSampleRates?.toList() ?: "-"}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Supported Sample Rate Ranges: ${capabilities.supportedSampleRateRanges ?: "-"}}", style = MaterialTheme.typography.bodyMedium)
        if (Build.VERSION.SDK_INT >= 31) {
            Text(text = "Min Input Channel Count: ${capabilities.minInputChannelCount ?: "-"}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Input Channel Count Ranges: ${capabilities.inputChannelCountRanges ?: "-"}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun VideoCodecCapabilitiesView(capabilities: VideoCapabilitiesInfo) {
    InfoSection("Video Capabilities") {
        Text(text = "Bitrate Range: ${capabilities.bitrateRange}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Frame Rates: ${capabilities.supportedFrameRates ?: "-"}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Width Range: ${capabilities.supportedWidths}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Height Range: ${capabilities.supportedHeights}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Width Alignment: ${capabilities.widthAlignment}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Height Alignment: ${capabilities.heightAlignment}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Supported Performance Points: ${capabilities.supportedPerformancePoints?.toList() ?: "-"}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Max Supported Frame Rates:", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.size(16.dp))
        VerticalLazyListScrollBar(
            scrollBarModifier = Modifier.sizeIn(maxHeight = 48.dp)
        ) {
            items(capabilities.maxSupportedFrameRates) { pp ->
                Text(
                    text = "${pp.width}x${pp.height}: ${pp.frameRates.upper} fps",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Text(text = "Achievable Frame Rates:", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.size(16.dp))
        VerticalLazyListScrollBar(
            scrollBarModifier = Modifier.sizeIn(maxHeight = 48.dp),
        ) {
            items(capabilities.achievableFrameRates.filter { it.frameRates.upper > 0.0 }) { pp ->
                Text(
                    text = pp.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun EncoderCodecCapabilitiesView(capabilities: EncoderCapabilitiesInfo) {
    val modesStrs = mapOf(
        BitrateMode.CBR to "Constant (CBR)",
        BitrateMode.CQ to "Constant Quality (CQ)",
        BitrateMode.VBR to "Variable (VBR)",
        BitrateMode.CBR_FD to "Constant with Frame Drops (CBR_FD)"
    )
    InfoSection("Encoder Capabilities") {
        if (Build.VERSION.SDK_INT >= 29) {
            Text(text = "Quality Range: ${capabilities.qualityRange}", style = MaterialTheme.typography.bodyMedium)
        }
        Text(text = "Complexity Range: ${capabilities.complexityRange}", style = MaterialTheme.typography.bodyMedium)
        Text("Bitrate Modes", style = MaterialTheme.typography.titleSmall)
        capabilities.isBitrateModeSupported.map { (mode, value) ->
            val modeStr = modesStrs.getOrDefault(mode, null)
            modeStr?.let {
                Text(
                    text = "${modeStr}: $value",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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
//    VideoCodecCapabilitiesView(videoCodec!!.getCapabilitiesForType(videoCodec!!.supportedTypes[0]).videoCapabilities)
    videoCodec?.let {
        val codecInfo = CodecInfoExtractor().extractCodecInfo(it)
        VideoCodecCapabilitiesView(codecInfo.capabilities.first { it.videoCapabilities != null }.videoCapabilities!!)
    }
}

@Composable
fun SafeText(name: String, value: String?, typography: TextStyle = MaterialTheme.typography.bodyMedium) {
    if (!value.isNullOrEmpty()) {
        Text(text = "$name: $value", style = MaterialTheme.typography.bodyMedium)
    }
}