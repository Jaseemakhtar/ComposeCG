package com.dolearnn.cg.ui.algorithms.beziercurve

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
import com.dolearnn.cg.ui.theme.Blue500
import com.dolearnn.cg.ui.theme.DLGreen
import com.dolearnn.cg.ui.theme.Orange500
import com.dolearnn.cg.ui.theme.Red500
import com.dolearnn.cg.ui.theme.Violet500
import com.dolearnn.cg.ui.theme.Yellow500
import com.dolearnn.cg.ui.extensions.detectPointDrag
import com.dolearnn.cg.extensions.dpToPx

val CONTROL_POINT_RADIUS = 8f.dpToPx()
val CONTROL_POINT_DIAMETER = 16f.dpToPx()
val INTERPOLATED_POINTS_RADIUS = 6f.dpToPx()
val PLOTTED_POINT_RADIUS = 3.2f.dpToPx()
val CONTROL_POINT_LINE_STROKE_WIDTH = 2f.dpToPx()
val INTERPOLATED_LINE_STROKE_WIDTH = 1f.dpToPx()

private val interpolatedLevelColors = listOf(Red500, Blue500, Yellow500, Violet500, Orange500)

@Composable
fun BezierCurve(state: BezierCurveState) {
    val isPlaying: Boolean by state.isPlaying.observeAsState(false)

    val animatable = remember {
        Animatable(0.0f)
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            animatable.animateTo(1.0f, tween(6500, 0, LinearEasing))
        } else {
            animatable.stop()
            animatable.snapTo(0.0f)
            state.onInterpolatedValueChange(-1f)
        }
    }

    if (isPlaying)
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

        for (i in state.controlPoints.indices) {

            drawCircle(
                color = Color.White,
                radius = CONTROL_POINT_RADIUS,
                center = state.controlPoints[i].toOffset()
            )

            // connecting the control points with line
            if (i < state.controlPoints.lastIndex) {

                drawLine(
                    color = Color.White,
                    start = state.controlPoints[i].toOffset(),
                    end = state.controlPoints[i + 1].toOffset(),
                    strokeWidth = CONTROL_POINT_LINE_STROKE_WIDTH
                )
            }
        }


        if (isPlaying) {
            for (level in state.interpolatedPoints.indices) {
                val colorLevelIndex = level % interpolatedLevelColors.size

                for (i in state.interpolatedPoints[level].indices) {
                    drawCircle(
                        color = interpolatedLevelColors[colorLevelIndex],
                        radius = INTERPOLATED_POINTS_RADIUS,
                        center = state.interpolatedPoints[level][i].toOffset()
                    )

                    if (i < state.interpolatedPoints[level].lastIndex) {
                        drawLine(
                            color = interpolatedLevelColors[colorLevelIndex],
                            start = state.interpolatedPoints[level][i].toOffset(),
                            end = state.interpolatedPoints[level][i + 1].toOffset(),
                            strokeWidth = INTERPOLATED_LINE_STROKE_WIDTH
                        )
                    }
                }
            }
        }



        state.plottedPoints.forEach { plottedPoint ->
            drawCircle(
                color = DLGreen,
                radius = PLOTTED_POINT_RADIUS,
                center = plottedPoint.toOffset()
            )
        }
    }
}