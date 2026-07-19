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
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDragHandle
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldDefaults
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneExpansionAnchor
import androidx.compose.material3.adaptive.layout.PaneExpansionState
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.layout.rememberPaneExpansionState
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeci.ui.main.CodecsViewModel
import io.igrvlhlb.codeci.ui.screens.CodecDetailsScreen
import io.igrvlhlb.codeci.ui.screens.CodecInfoScreen
import io.igrvlhlb.codeci.ui.screens.CodecListScreen
import io.igrvlhlb.codeci.ui.theme.CodeciTheme
import io.igrvlhlb.lib.data.CodecInfo
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

/** The divider cannot be dragged closer than this to the left (start) edge. */
private val DividerMinDistanceFromStart = 300.dp

/** The divider cannot be dragged closer than this to the right (end) edge. */
private val DividerMinDistanceFromEnd = 300.dp

class MainActivity : ComponentActivity() {

    val codecsViewModel: CodecsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeciTheme {
                val windowAdaptiveInfo = currentWindowAdaptiveInfo()
                // On compact-height windows (e.g. phones in landscape) a split pane is too
                // cramped: keep a single pane and open the details full-screen instead.
                val useSinglePane =
                    windowAdaptiveInfo.windowSizeClass.windowHeightSizeClass ==
                        WindowHeightSizeClass.COMPACT
                val directive = calculatePaneScaffoldDirective(windowAdaptiveInfo)
                val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<CodecInfo>(
                    scaffoldDirective =
                        if (useSinglePane) directive.copy(maxHorizontalPartitions = 1) else directive
                )
                val scope = rememberCoroutineScope()
                val paneAnchors = remember {
                    listOf(
                        PaneExpansionAnchor.Offset.fromStart(320.dp),
                        PaneExpansionAnchor.Proportion(0.6f)
                    )
                }
                val paneExpansionState = rememberPaneExpansionState(anchors = paneAnchors)
                var scaffoldBounds by remember { mutableStateOf<Rect?>(null) }

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
                            scaffoldNavigator.currentDestination?.contentKey.let {
                                CodecDetailsScreen(it)
                            }
                        }
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .onGloballyPositioned { scaffoldBounds = it.boundsInRoot() },
                    paneExpansionDragHandle = { state ->
                        LimitedDragHandle(
                            state = state,
                            anchors = paneAnchors,
                            minDistanceFromStart = DividerMinDistanceFromStart,
                            minDistanceFromEnd = DividerMinDistanceFromEnd,
                            scaffoldBounds = { scaffoldBounds },
                        )
                    },
                    paneExpansionState = paneExpansionState
                )
            }
        }
    }
}

/**
 * Drag handle for the pane divider that hard-limits how far it can be dragged: the divider never
 * gets closer than [minDistanceFromStart]/[minDistanceFromEnd] to the scaffold edges, even
 * mid-gesture. On release it still snaps to the nearest of [anchors] (which should therefore lie
 * within the limits).
 *
 * The library's [Modifier.paneExpansionDraggable] can't be used for this because its drag pipeline
 * is unclamped and internal, so this drives the public [PaneExpansionState.setFirstPaneWidth]
 * from its own [draggable] instead.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun LimitedDragHandle(
    state: PaneExpansionState,
    anchors: List<PaneExpansionAnchor>,
    minDistanceFromStart: Dp,
    minDistanceFromEnd: Dp,
    scaffoldBounds: () -> Rect?,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    // The divider (and this handle) sits centered on the spacer between the panes, half a spacer
    // past the first pane's edge that setFirstPaneWidth controls.
    val halfSpacerPx = with(density) {
        calculatePaneScaffoldDirective(currentWindowAdaptiveInfo())
            .horizontalPartitionSpacerSize.toPx() / 2f
    }
    val interactionSource = remember { MutableInteractionSource() }
    var handleCenterX by remember { mutableFloatStateOf(Float.NaN) }
    // Divider center, as a distance from the scaffold's start edge, while a drag is in progress.
    var dividerPosition by remember { mutableFloatStateOf(Float.NaN) }

    VerticalDragHandle(
        interactionSource = interactionSource,
        modifier = modifier
            .onGloballyPositioned { handleCenterX = it.boundsInRoot().center.x }
            .draggable(
                state = rememberDraggableState { delta ->
                    val bounds = scaffoldBounds()
                    if (bounds == null || dividerPosition.isNaN()) return@rememberDraggableState
                    val towardsEnd = if (isRtl) -delta else delta
                    dividerPosition = (dividerPosition + towardsEnd).coerceIn(
                        with(density) { minDistanceFromStart.toPx() },
                        bounds.width - with(density) { minDistanceFromEnd.toPx() },
                    )
                    state.setFirstPaneWidth((dividerPosition - halfSpacerPx).roundToInt())
                },
                orientation = Orientation.Horizontal,
                interactionSource = interactionSource,
                onDragStarted = { _ ->
                    dividerPosition = scaffoldBounds()?.let { bounds ->
                        if (isRtl) bounds.right - handleCenterX else handleCenterX - bounds.left
                    } ?: Float.NaN
                },
                onDragStopped = { velocity ->
                    val bounds = scaffoldBounds()
                    if (bounds != null && !dividerPosition.isNaN()) {
                        anchors
                            .minByOrNull { abs(it.positionPx(bounds.width, density) - dividerPosition) }
                            ?.let { state.animateTo(it, if (isRtl) -velocity else velocity) }
                    }
                    dividerPosition = Float.NaN
                },
            ),
    )
}

/** Where [this] anchor lands, as a distance in px from the scaffold's start edge. */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun PaneExpansionAnchor.positionPx(totalWidth: Float, density: Density): Float =
    when (this) {
        is PaneExpansionAnchor.Proportion -> totalWidth * proportion
        is PaneExpansionAnchor.Offset ->
            if (direction == PaneExpansionAnchor.Offset.Direction.FromStart) {
                with(density) { offset.toPx() }
            } else {
                totalWidth - with(density) { offset.toPx() }
            }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String = "Codec(i)", actions: @Composable () -> Unit = {}) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(title)
        },
        actions = {
            actions()
        }
    )
}
