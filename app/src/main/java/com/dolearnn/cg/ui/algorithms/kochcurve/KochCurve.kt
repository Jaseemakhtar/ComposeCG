package com.dolearnn.cg.ui.algorithms.kochcurve

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
import androidx.compose.ui.graphics.drawscope.Stroke
import com.dolearnn.cg.ui.algorithms.beziercurve.CONTROL_POINT_RADIUS
import com.dolearnn.cg.ui.theme.DLGreen
import com.dolearnn.cg.ui.extensions.detectPointDrag
import com.dolearnn.cg.extensions.dpToPx

private val LINE_STROKE = 1f.dpToPx()

@Composable
fun KochCurve(state: KochCurveState) {
    val isPlaying: Boolean by state.isPlaying.observeAsState(false)
    val animatable = remember {
        Animatable(0.0f)
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            animatable.animateTo(1.0f, tween(6500, 0, LinearEasing))
        } else {
            animatable.snapTo(0.0f)
        }
    }

    state.onInterpolatedValueChange(animatable.value)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .detectPointDrag(
                enable = { !isPlaying },
                onTouch = state::onTouch,
                onDrag = state::onDragStart,
                onDragEnd = state::onDragEnd
            )
    ) {

        with(state.plottedLines) {
            if (size == 1) {
                drawCircle(
                    color = DLGreen,
                    radius = CONTROL_POINT_RADIUS,
                    center = first().start.toOffset(),
                    style = Stroke(LINE_STROKE)
                )
                drawCircle(
                    color = DLGreen,
                    radius = CONTROL_POINT_RADIUS,
                    center = first().end.toOffset(),
                    style = Stroke(LINE_STROKE)
                )
            }

            forEachIndexed { _, line ->
                drawLine(
                    color = Color.White,
                    start = line.start.toOffset(),
                    end = line.end.toOffset(),
                    strokeWidth = LINE_STROKE
                )
            }

        }

    }
}