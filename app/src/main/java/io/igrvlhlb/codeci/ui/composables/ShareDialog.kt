package io.igrvlhlb.codeci.ui.composables

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import java.io.File

@Composable
fun ShareDialog(
    onDismiss: () -> Unit,
    onShareAsText: () -> Unit,
    onShareAsJsonFile: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Share as...") },
        text = { Text("Choose the format to share the codecs information.") },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onShareAsText) {
                    Text("Text")
                }
                Button(onClick = onShareAsJsonFile) {
                    Text("JSON File")
                }
            }
        }
    )
}

fun shareAsJsonFile(context: Context, jsonString: String) {
    val file = File(context.cacheDir, "codecs.json")
    file.writeText(jsonString)
    val fileUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, fileUri)
        type = "application/json"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share JSON file"))
}

fun shareAsJsonText(context: Context, jsonString: String) {
    val shareIntent = android.content.Intent().apply {
        action = android.content.Intent.ACTION_SEND
        putExtra(android.content.Intent.EXTRA_TEXT, jsonString)
        type = "text/json"
    }
    context.startActivity(android.content.Intent.createChooser(shareIntent, null))
}

