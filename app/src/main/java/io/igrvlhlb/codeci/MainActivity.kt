package io.igrvlhlb.codeci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeci.ui.main.CodecsViewModel
import io.igrvlhlb.codeci.ui.theme.CodeciTheme

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
                    MainUi(innerInsets)
                }
            }
        }
    }
}


@Composable
fun MainUi(innerPadding: PaddingValues = PaddingValues()) {
    Column(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
        FilterMenu()
    }
}

@Composable
fun FilterMenu() {
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            MenuTitle(text = "Codec Type")
            MenuComboBox(values = MediaType.entries.map { it.value })
            Spacer(modifier = Modifier.size(8.dp))
            MenuTitle(text = "Media Type")
            MenuComboBox(values = CodecType.entries.map { it.value })
        }
        Column {
            MenuTitle(text = "Mime Types")
            MenuComboBox(values = listOf("All"))
            Spacer(modifier = Modifier.size(8.dp))
            MenuTitle(text = "HW Accelerated")
            MenuComboBox(values = HWAccel.entries.map { it.value })
        }
    }
}

@Composable
fun MenuTitle(text: String) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 24.sp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuComboBox(values: List<String>, default: String = values.first()) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedValue by rememberSaveable { mutableStateOf(default) }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
        TextField(
            value = selectedValue, onValueChange = {}, readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .size(width = 160.dp, height = Dp.Unspecified)
        )
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            values.forEach {
                DropdownMenuItem(text = {
                    Text(it)
                }, onClick = {
                    selectedValue = it
                    isExpanded = false
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    CodeciTheme {
        MainUi()
    }
}

enum class MediaType(val value: String) {
    ALL("All"),
    AUDIO("Audio"),
    VIDEO("Video"),
}

enum class CodecType(val value: String) {
    ALL("All"),
    ENCODER("Encoder"),
    DECODER("Decoder"),
}

enum class HWAccel(val value: String) {
    ALL("All"),
    YES("Yes"),
    NO("No"),
}