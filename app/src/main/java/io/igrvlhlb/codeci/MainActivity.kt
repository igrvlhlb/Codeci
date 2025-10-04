package io.igrvlhlb.codeci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codeci.ui.main.CodecsViewModel
import io.igrvlhlb.codeci.ui.screens.CodecInfoScreen
import io.igrvlhlb.codeci.ui.screens.CodecListScreen
import io.igrvlhlb.codeci.ui.theme.CodeciTheme

class MainActivity : ComponentActivity() {

    val codecsViewModel: CodecsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeciTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
                val context = androidx.compose.ui.platform.LocalContext.current
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Codec(i)")
                            },
                            actions = {
                                if (currentRoute?.startsWith("codecInfo") == true) {
                                    androidx.compose.material3.IconButton(onClick = {
                                        val codec = codecsViewModel.codecInfo
                                        val shareText = codec.serialize(optPrettyPrint = true)
                                        val shareIntent = android.content.Intent().apply {
                                            action = android.content.Intent.ACTION_SEND
                                            putExtra(android.content.Intent.EXTRA_TEXT, shareText)
                                            type = "text/json"
                                        }
                                        context.startActivity(android.content.Intent.createChooser(shareIntent, null))
                                    }) {
                                        Icon(
                                            imageVector = androidx.compose.material.icons.Icons.Filled.Share,
                                            contentDescription = "Share"
                                        )
                                    }
                                }
                            }
                        )
                    },
                ) { innerInsets ->
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            CodecListScreen(codecsViewModel, innerInsets, navController)
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