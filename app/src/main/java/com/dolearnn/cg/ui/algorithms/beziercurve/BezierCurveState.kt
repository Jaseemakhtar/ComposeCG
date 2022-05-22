package com.dolearnn.cg.ui.algorithms.beziercurve

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import com.dolearnn.cg.ui.primitives.Point
import com.dolearnn.cg.ui.algorithms.AbstractState
import com.dolearnn.cg.usecase.LerpUseCase

class BezierCurveState : AbstractState() {

    private val _controlPoints = mutableStateListOf<Point>()
    val controlPoints: List<Point> = _controlPoints

    private var _interpolatedPoints = emptyList<MutableList<Point>>()
    val interpolatedPoints: List<List<Point>>
        get() = _interpolatedPoints

    private var _plottedPoints = mutableListOf<Point>()
    var plottedPoints: List<Point> = _plottedPoints

    val curvePath = Path()

    private fun reInitPlottedPointsListCount() {
        _interpolatedPoints = List(controlPoints.size - 1) { mutableListOf() }
    }

    override fun init(width: Float, height: Float, totalFrames: Float) {
        super.init(width, height, totalFrames)

        _controlPoints.addAll(
            Array(2) {
                getRandomControlPoint()
            }
        )

        reInitPlottedPointsListCount()
    }

    override fun onTouch(touchOffset: Offset): Int {
        if (_isPlaying.value == true) return -1
        if (_plottedPoints.isNotEmpty()) _plottedPoints.clear()


        for (i in controlPoints.indices) {
            val currentPoint = controlPoints[i]

            if (
                touchOffset.x in (currentPoint.x - CONTROL_POINT_RADIUS)..(currentPoint.x + CONTROL_POINT_RADIUS) &&
                touchOffset.y in (currentPoint.y - CONTROL_POINT_RADIUS)..(currentPoint.y + CONTROL_POINT_RADIUS)
            ) {
                draggingControlPoint = i
                break
            }
        }
        return draggingControlPoint
    }

    override fun onDragStart(offset: Offset) {
        if (draggingControlPoint != -1) {
            _controlPoints[draggingControlPoint] = Point(
                _controlPoints[draggingControlPoint].x + offset.x.toInt(),
                _controlPoints[draggingControlPoint].y + offset.y.toInt()
            )
        }
    }

    override fun onClickAdd() {
        super.onClickAdd()

        if (_controlPoints.size < 20) {
            _controlPoints.add(getRandomControlPoint())
            reInitPlottedPointsListCount()
        }
    }

    override fun onClickRemove() {
        super.onClickRemove()

        if (_controlPoints.size > 2) {
            _controlPoints.removeLast()
            reInitPlottedPointsListCount()
        }
    }

    override fun onClickPlayPause() {
        super.onClickPlayPause()
        _plottedPoints.clear()
    }

    override fun onInterpolatedValueChange(t: Float) {
        if (_isPlaying.value != true) return

        for (_interpolatedPoint in _interpolatedPoints) {
            _interpolatedPoint.clear()
        }

        var currentPoints = controlPoints
        var index = 0

        do {

            val nextLevelPoints = mutableListOf<Point>()
            for (i in 0..currentPoints.size - 2) {
                val interpolatedPoint =
                    LerpUseCase.lerp(
                        currentPoints[i],
                        currentPoints[i + 1],
                        t
                    )
                nextLevelPoints.add(interpolatedPoint)
            }

            currentPoints = nextLevelPoints
            _interpolatedPoints[index].addAll(currentPoints)
            if (currentPoints.size == 1) {
                _plottedPoints.addAll(currentPoints)
            }
            index++
        } while (currentPoints.size > 1)



        super.onInterpolatedValueChange(t)
    }
}