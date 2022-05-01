package com.dolearnn.cg.os.ui.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.dolearnn.cg.os.algorithms.AbstractState
import com.dolearnn.cg.os.algorithms.beziercurve.BezierCurve
import com.dolearnn.cg.os.algorithms.beziercurve.BezierCurveState
import com.dolearnn.cg.os.algorithms.kockcurve.KochCurve
import com.dolearnn.cg.os.algorithms.kockcurve.KochCurveState
import com.dolearnn.cg.os.algorithms.sierpinskygasket.SierpinskyGasket
import com.dolearnn.cg.os.algorithms.sierpinskygasket.SierpinskyGasketState
import com.dolearnn.cg.os.ui.component.AlgorithmUnavailable
import com.dolearnn.cg.os.ui.component.GraphGrid
import com.dolearnn.cg.os.ui.component.PlayerController

@Composable
fun SimulationScreenContent(algorithmId: Int, showAddRemoveControls: Boolean) {

    val algorithmState = when (algorithmId) {
        1 -> SierpinskyGasketState()
        2 -> KochCurveState()
        3 -> BezierCurveState()
        else -> AlgorithmUnavailableState()
    }

    SimulationScreenContainer(showControls = algorithmId > 0, state = algorithmState, showAddRemoveControls = showAddRemoveControls) {
        when (algorithmId) {
            1 -> SierpinskyGasket(algorithmState as SierpinskyGasketState)
            2 -> KochCurve(algorithmState as KochCurveState)
            3 -> BezierCurve(algorithmState as BezierCurveState)
            else -> AlgorithmUnavailable()
        }
    }
}

@Composable
fun SimulationScreenContainer(
    showControls: Boolean,
    showAddRemoveControls: Boolean,
    state: AbstractState,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {

            LaunchedEffect(Unit) {
                state.init(
                    width = with(density) {
                        maxWidth.toPx()
                    },
                    height = with(density) {
                        maxHeight.toPx()
                    }
                )
            }

            GraphGrid(modifier = Modifier.fillMaxSize())

            content()
        }

        if (showControls) {
            PlayerController(
                state.isPlaying,
                showAddRemoveControls,
                state::onClickPlayPause,
                state::onClickAdd,
                state::onClickRemove
            )
        }
    }
}