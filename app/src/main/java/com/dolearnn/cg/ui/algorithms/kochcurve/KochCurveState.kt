package com.dolearnn.cg.ui.algorithms.kochcurve

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import com.dolearnn.cg.ui.primitives.Line
import com.dolearnn.cg.ui.primitives.Point
import com.dolearnn.cg.ui.algorithms.AbstractState
import com.dolearnn.cg.ui.algorithms.beziercurve.CONTROL_POINT_RADIUS
import kotlin.math.cos
import kotlin.math.sin

class KochCurveState : AbstractState() {

    private var lineLength: Float = -1f
    private var _plottedLines = mutableStateListOf<Line>()
    val plottedLines: List<Line>
        get() = _plottedLines
    private lateinit var initialLine: Line

    override fun init(width: Float, height: Float, totalFrames: Float) {
        super.init(width, height, totalFrames)

        lineLength = (if (viewWidth < viewHeight) viewWidth else viewHeight).times(0.9f)
        iterationAmount = 1f / 6f
        iterationCount = iterationAmount

        initialLine = Line(start = getRandomControlPoint(), end = getRandomControlPoint())

        _plottedLines.add(
            initialLine
        )
    }

    override fun onClickPlayPause() {
        super.onClickPlayPause()
        if (_isPlaying.value == true && _plottedLines.isNotEmpty()) {
            iterationCount = iterationAmount
            _plottedLines.clear()
            _plottedLines.add(initialLine)
        }
    }

    override fun onTouch(touchOffset: Offset): Int {
        if (_isPlaying.value == true) return -1

        if (_plottedLines.size > 1) {
            _plottedLines.clear()
            _plottedLines.add(initialLine)
        }

        if (
            touchOffset.x in (_plottedLines[0].start.x - CONTROL_POINT_RADIUS)..(_plottedLines[0].start.x + CONTROL_POINT_RADIUS) &&
            touchOffset.y in (_plottedLines[0].start.y - CONTROL_POINT_RADIUS)..(_plottedLines[0].start.y + CONTROL_POINT_RADIUS)
        ) {
            draggingControlPoint = 0
        }

        if (
            touchOffset.x in (_plottedLines[0].end.x - CONTROL_POINT_RADIUS)..(_plottedLines[0].end.x + CONTROL_POINT_RADIUS) &&
            touchOffset.y in (_plottedLines[0].end.y - CONTROL_POINT_RADIUS)..(_plottedLines[0].end.y + CONTROL_POINT_RADIUS)
        ) {
            draggingControlPoint = 1
        }

        return draggingControlPoint
    }

    override fun onDragStart(offset: Offset) {
        when (draggingControlPoint) {
            0 -> {
                initialLine = initialLine.copy(
                    start = Point(
                        initialLine.start.x + offset.x,
                        initialLine.start.y + offset.y
                    )
                )
            }

            1 -> {
                initialLine = initialLine.copy(
                    end = Point(
                        initialLine.end.x + offset.x,
                        initialLine.end.y + offset.y
                    )
                )
            }
        }

        if (draggingControlPoint == 0 || draggingControlPoint == 1) {
            _plottedLines.clear()
            _plottedLines.add(initialLine)
        }
    }

    override fun onInterpolatedValueChange(t: Float) {
        if (_isPlaying.value != true) return

        val nextLines = mutableListOf<Line>()

        if (t >= iterationCount) {

            iterationCount += iterationAmount

            _plottedLines.forEach { line ->
                val x3 = (2 * line.start.x + line.end.x) / 3
                val y3 = (2 * line.start.y + line.end.y) / 3

                val x4 = (2 * line.end.x + line.start.x) / 3
                val y4 = (2 * line.end.y + line.start.y) / 3

                val dx = x4 - x3
                val dy = y4 - y3

                val x5 = x3 + dx * cos(SIXTY_DEGREE_IN_RADIANS) + dy * sin(SIXTY_DEGREE_IN_RADIANS)
                val y5 = y3 - dx * sin(SIXTY_DEGREE_IN_RADIANS) + dy * cos(SIXTY_DEGREE_IN_RADIANS)

                val pointB = Point(x3, y3)
                val pointC = Point(x4, y4)
                val pointD = Point(x5.toFloat(), y5.toFloat())

                nextLines.add(Line(start = line.start, end = pointB))
                nextLines.add(Line(start = pointB, end = pointD))
                nextLines.add(Line(start = pointD, end = pointC))
                nextLines.add(Line(start = pointC, end = line.end))

            }
            _plottedLines.clear()
            _plottedLines.addAll(nextLines)
        }

        super.onInterpolatedValueChange(t)
    }

    companion object {
        private const val SIXTY_DEGREE_IN_RADIANS = 1.04
    }
}
