package io.igrvlhlb.codeci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    MainUi(codecsViewModel, innerInsets)
                }
            }
        }
    }
}


@Composable
fun MainUi(viewModel: CodecsViewModel, innerPadding: PaddingValues = PaddingValues()) {
    Column(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
        FilterMenu(viewModel)
        CodecsList(viewModel, Modifier.padding(top = 8.dp))
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
fun CodecsList(viewModel: CodecsViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.state
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentSize()
        ) {
            items(state.codecsList) { codecInfo ->
                CodecCard(
                    codecInfo.name,
                    codecInfo.supportedTypes.toList(),
                    codecInfo.isSoftwareCodec(),
                    modifier = Modifier.padding(12.dp)
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
fun CodecCard(
    codecName: String,
    supportedTypes: List<String>,
    isSoftwareCodec: Boolean,
    modifier: Modifier = Modifier
) {
    val supportedTypes = supportedTypes
    val typeSuffix = if (supportedTypes.size > 1) " (+${supportedTypes.size})" else ""
    Card {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Column {
                Text(text = codecName, style = MaterialTheme.typography.titleSmall)
                Text(text = "Type: " + supportedTypes.first() + typeSuffix)
            }
            HardwareCodecIndicator(isSoftwareCodec)
        }
    }
}

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

@Preview
@Composable
fun CodecCardPreview() {
    CodecCard(
        codecName = "OMX.google.h264.encoder",
        supportedTypes = listOf("video/avc"),
        isSoftwareCodec = false,
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
        MainUi(viewModel = CodecsViewModel())
    }
}