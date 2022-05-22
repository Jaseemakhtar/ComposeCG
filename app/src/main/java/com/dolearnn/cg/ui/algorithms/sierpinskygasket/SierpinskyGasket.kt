package com.dolearnn.cg.ui.algorithms.sierpinskygasket

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import com.dolearnn.cg.ui.extensions.drawTriangle
import com.dolearnn.cg.extensions.dpToPx

private val LINE_STROKE_WIDTH = 1f.dpToPx()

@Composable
fun SierpinskyGasket(state: SierpinskyGasketState) {
    val isPlaying: Boolean by state.isPlaying.observeAsState(false)
    val animatable = remember {
        Animatable(0.0f)
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            animatable.animateTo(state.totalIterations, tween(6500, 0, LinearEasing))
        } else {
            animatable.stop()
            animatable.snapTo(0.0f)
        }
    }

    state.onInterpolatedValueChange(animatable.value)

    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(size.width / 2f, size.height / 2f) {
            for (i in state.plottedPoints.indices) {
                drawTriangle(
                    triangle = state.plottedPoints[i],
                    color = Color.White,
                    LINE_STROKE_WIDTH
                )
            }
        }
    }
}