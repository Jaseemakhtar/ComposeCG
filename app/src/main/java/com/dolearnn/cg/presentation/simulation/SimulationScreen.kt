package com.dolearnn.cg.presentation.simulation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dolearnn.cg.presentation.algorithms.AlgorithmUnavailableState
import com.dolearnn.cg.ui.algorithms.AbstractState
import com.dolearnn.cg.ui.algorithms.beziercurve.BezierCurve
import com.dolearnn.cg.ui.algorithms.beziercurve.BezierCurveState
import com.dolearnn.cg.ui.algorithms.kochcurve.KochCurve
import com.dolearnn.cg.ui.algorithms.kochcurve.KochCurveState
import com.dolearnn.cg.ui.algorithms.sierpinskygasket.SierpinskyGasket
import com.dolearnn.cg.ui.algorithms.sierpinskygasket.SierpinskyGasketState
import com.dolearnn.cg.presentation.algorithms.AlgorithmUnavailable
import com.dolearnn.cg.ui.components.GraphGrid

@Composable
fun SimulationScreenContent(viewModel: SimulationScreenViewModel = viewModel()) {
    val algorithmState = when (viewModel.algorithmId) {
        1 -> SierpinskyGasketState()
        2 -> KochCurveState()
        3 -> BezierCurveState()
        else -> AlgorithmUnavailableState()
    }

    SimulationScreenContainer(
        showControls = viewModel.algorithmId > 0,
        state = algorithmState,
        showAddRemoveControls = viewModel.showAddRemoveControls
    ) {
        when (viewModel.algorithmId) {
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