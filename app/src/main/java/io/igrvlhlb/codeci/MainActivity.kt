package io.igrvlhlb.codeci

import android.media.MediaCodecInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeci.ui.main.CodecsViewModel
import com.example.codeci.utils.isSoftwareCodec
import io.igrvlhlb.codeci.model.CodecType
import io.igrvlhlb.codeci.model.HWAccel
import io.igrvlhlb.codeci.model.MediaType
import io.igrvlhlb.codeci.ui.theme.CodeciTheme
import io.igrvlhlb.codeci.utils.minAspectRatio

class MainActivity : ComponentActivity() {

    val codecsViewModel: CodecsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeciTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Codec(i)")
                            }
                        )
                    },
                ) { innerInsets ->
//                    MainUi(codecsViewModel, innerInsets)
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainUi(codecsViewModel, innerInsets, navController)
                        }
                        composable("codecInfo/{codecName}") { backStackEntry ->
                            val codecName = backStackEntry.arguments?.getString("codecName")
                            CodecInfoScreen(codecsViewModel, innerInsets)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MainUi(viewModel: CodecsViewModel, innerPadding: PaddingValues = PaddingValues(), navController: NavHostController) {
    Column(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
        FilterMenu(viewModel)
        CodecsList(viewModel, navController, Modifier.padding(top = 8.dp))
    }
}

@Composable
fun FilterMenu(viewModel: CodecsViewModel) {
    val state by viewModel.state
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            FilterMenuItem(
                text = "Codec Type",
                selectedValue = state.codecType,
                values = CodecType.entries.map { it.value }
            ) {
                viewModel.updateState(state.copy(codecType = it))
            }
            Spacer(modifier = Modifier.size(8.dp))
            FilterMenuItem(
                text = "Media Type",
                selectedValue = state.mediaType,
                values = MediaType.entries.map { it.value }
            ) {
                viewModel.updateState(state.copy(mediaType = it))
            }
        }
        Column {
            FilterMenuItem(
                text = "Mime Types",
                selectedValue = state.mimeType,
                values = listOf("All") + state.mimeTypeList
            ) {
                viewModel.updateState(state.copy(mimeType = it))
            }
            Spacer(modifier = Modifier.size(8.dp))
            FilterMenuItem(
                text = "HW Accelerated",
                selectedValue = state.hwAccel,
                values = HWAccel.entries.map { it.value }
            ) {
                viewModel.updateState(state.copy(hwAccel = it))
            }
        }
    }
}

@Composable
fun FilterMenuItem(
    text: String,
    selectedValue: String,
    values: List<String>,
    onValueSelected: (String) -> Unit = {}
) {
    FilterMenuItemTitle(text = text)
    FilterMenuComboBox(selectedValue, values, onValueSelected)
}

@Composable
fun FilterMenuItemTitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.titleMedium)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterMenuComboBox(
    selectedValue: String,
    values: List<String>,
    onValueSelected: (String) -> Unit = {}
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
        MenuItemComboboxField(selectedValue = selectedValue, isExpanded = isExpanded)
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            values.forEach {
                DropdownMenuItem(text = {
                    Text(it)
                }, onClick = {
                    isExpanded = false
                    onValueSelected(it)
                })
            }
        }
    }
}

@Composable
fun CodecsList(
    viewModel: CodecsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            items(state.codecsList) { codecInfo ->
                ClickableCodecCard(
                    viewModel,
                    codecInfo,
                    navController,
                    modifier = Modifier
                        .padding(12.dp)
                        .width(260.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxScope.MenuItemComboboxField(selectedValue: String, isExpanded: Boolean) {
    TextField(
        value = selectedValue,
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(),
        modifier = Modifier
            .menuAnchor()
            .size(width = 160.dp, height = Dp.Unspecified)
    )
}

@Composable
fun ClickableCodecCard(
    viewModel: CodecsViewModel,
    codecInfo: MediaCodecInfo,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val codecName = codecInfo.name
    val supportedTypes = codecInfo.supportedTypes.toList()
    val isSoftwareCodec = codecInfo.isSoftwareCodec()
    CodecCard(
        codecName,
        supportedTypes,
        isSoftwareCodec,
        navController,
        modifier.clickable {
            viewModel.selectedCodec = codecInfo
            navController.navigate("codecInfo/$codecName")
        }
    )
}

@Composable
fun CodecCard(
    codecName: String,
    supportedTypes: List<String>,
    isSoftwareCodec: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val typeSuffix = if (supportedTypes.size > 1) " (+${supportedTypes.size})" else ""
    val gradient = Brush.horizontalGradient(0f to MaterialTheme.colorScheme.surface, 1f to MaterialTheme.colorScheme.primaryContainer)

    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Box(
            Modifier
                .background(gradient)
                .then(modifier)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(text = codecName, style = MaterialTheme.typography.titleMedium/*.copy(lineBreak = customLineBreak)*/, overflow = TextOverflow.Ellipsis)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Type: " + supportedTypes.first() + typeSuffix,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        HardwareCodecIndicator(isSoftwareCodec)
                    }
                }
            }
        }
    }
}

val customLineBreak = LineBreak(
    strategy = LineBreak.Strategy.HighQuality,
    strictness = LineBreak.Strictness.Strict,
    wordBreak = LineBreak.WordBreak.Phrase
)

@Composable
fun HardwareCodecIndicator(isSoftwareCodec: Boolean) {
    val label = if (isSoftwareCodec) "SW" else "HW"
    val backgroundColor = Color(if (!isSoftwareCodec) 0xFFEF5350 else 0xFF42A5F5)
    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = Modifier
            .border(2.dp, Color.White, shape)
            .clip(shape)
            .background(backgroundColor)
            .wrapContentSize()
            .minAspectRatio(1.0),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 8.sp,
        )
    }
}

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

@Preview
@Composable
fun CodecCardPreview() {
    CodecCard(
        codecName = "OMX.google.h264.encoder",
        supportedTypes = listOf("video/avc"),
        isSoftwareCodec = false,
        rememberNavController(),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
@Preview
fun HardwareCodecIndicatorPreview() {
    Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
        HardwareCodecIndicator(isSoftwareCodec = true)
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    CodeciTheme {
        MainUi(viewModel = CodecsViewModel(), innerPadding = PaddingValues(16.dp), navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun CodecInfoScreenPreview() {
    CodecInfoScreen(viewModel = CodecsViewModel(), innerPadding = PaddingValues(16.dp))
}