package io.igrvlhlb.codeci
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

import android.media.MediaCodecInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeci.ui.main.CodecsViewModel
import io.igrvlhlb.codeci.ui.screens.CodecInfoScreen
import io.igrvlhlb.codeci.ui.screens.CodecListScreen
import io.igrvlhlb.codeci.ui.theme.CodeciTheme
import io.igrvlhlb.lib.data.CodecInfo
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val codecsViewModel: CodecsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeciTheme {
                val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<CodecInfo>()
                val scope = rememberCoroutineScope()

                NavigableListDetailPaneScaffold(
                    navigator = scaffoldNavigator,
                    listPane = {
                        AnimatedPane {
                            CodecListScreen(codecsViewModel) { selectedCodec ->
                                scope.launch {
                                    scaffoldNavigator.navigateTo(
                                        ListDetailPaneScaffoldRole.Detail,
                                        selectedCodec
                                    )
                                }
                            }
                        }
                    },
                    detailPane = {
                        AnimatedPane {
                            scaffoldNavigator.currentDestination?.contentKey?.let {
                                CodecInfoScreen(it)
                            }
                        }
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(actions: @Composable () -> Unit = {}) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Codec(i)")
        },
        actions = {
            actions()
        }
    )
}