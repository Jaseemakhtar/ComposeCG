package com.dolearnn.cg.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import com.dolearnn.cg.extensions.dpToPx
import com.dolearnn.cg.ui.primitives.Point
import com.dolearnn.cg.utils.MatrixMultiplicationUtil.matrixMultiply
import com.dolearnn.cg.utils.RotationMatrixUtils.getXRotationMatrix
import com.dolearnn.cg.utils.RotationMatrixUtils.getYRotationMatrix
import com.dolearnn.cg.utils.RotationMatrixUtils.getZRotationMatrix
import kotlin.math.min

private val POINT_RADIUS = 1.6f.dpToPx()
private val LINE_STROKE = 1f.dpToPx()

@Composable
fun RotatingCube(modifier: Modifier) {
    val transition = rememberInfiniteTransition()

    val radians: Float by transition.animateFloat(
        initialValue = 0.0f,
        targetValue = 0.08f,
        animationSpec = infiniteRepeatable(tween(1200, 0, LinearEasing), RepeatMode.Reverse)
    )

    val points: MutableList<Point> = remember {
        mutableListOf(
            Point(-0.2f, 0.2f, -0.2f),
            Point(0.2f, 0.2f, -0.2f),
            Point(0.2f, -0.2f, -0.2f),
            Point(-0.2f, -0.2f, -0.2f),

            Point(-0.2f, 0.2f, 0.2f),
            Point(0.2f, 0.2f, 0.2f),
            Point(0.2f, -0.2f, 0.2f),
            Point(-0.2f, -0.2f, 0.2f),
        )
    }

    Canvas(modifier = modifier) {

        val width = size.width
        val height = size.height

        val boxEdge = min(width, height)

        translate(width / 2f, height / 2f) {
            for (i in points.indices) {
                points[i] = matrixMultiply(points[i], getYRotationMatrix(radians))
                points[i] = matrixMultiply(points[i], getZRotationMatrix(radians))
                points[i] = matrixMultiply(points[i], getXRotationMatrix(radians))

                drawCircle(Color.White, POINT_RADIUS, center = points[i].scaleToOffset(boxEdge))
            }

            for (i in 0..3) {
                val nextNeighbour = (i + 1) % 4

                drawLine(
                    color = Color.White,
                    start = points[i].scaleToOffset(boxEdge),
                    end = points[nextNeighbour].scaleToOffset(boxEdge),
                    strokeWidth = LINE_STROKE
                )

                drawLine(
                    color = Color.White,
                    start = points[i].scaleToOffset(boxEdge),
                    end = points[i + 4].scaleToOffset(boxEdge),
                    strokeWidth = LINE_STROKE
                )

                drawLine(
                    color = Color.White,
                    start = points[i + 4].scaleToOffset(boxEdge),
                    end = points[nextNeighbour + 4].scaleToOffset(boxEdge),
                    strokeWidth = LINE_STROKE
                )
            }
        }
    }
}