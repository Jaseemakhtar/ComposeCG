package com.dolearnn.cg.ui.algorithms

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dolearnn.cg.ui.algorithms.beziercurve.CONTROL_POINT_DIAMETER
import com.dolearnn.cg.ui.primitives.Point
import com.dolearnn.cg.usecase.RandomNumberUseCase.getRandom

abstract class AbstractState {
    protected var viewWidth: Float = 0f
    protected var viewHeight: Float = 0f

    protected val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    protected var draggingControlPoint = -1

    var iterationCount = 0f
        protected set
    protected var iterationAmount = 0f
    var totalIterations = 1f
        protected set

    abstract fun onTouch(touchOffset: Offset): Int
    abstract fun onDragStart(offset: Offset)

    open fun onInterpolatedValueChange(t: Float) {
        if (t >= totalIterations) _isPlaying.value = false
    }

    open fun onClickAdd() {
        if (_isPlaying.value == true) return
    }

    open fun onClickRemove() {
        if (_isPlaying.value == true) return
    }

    open fun onClickPlayPause() {
        _isPlaying.value = isPlaying.value != true
    }

    open fun onDragEnd() {
        draggingControlPoint = -1
    }

    protected fun getRandomControlPoint() = Point(
        getRandom(
            CONTROL_POINT_DIAMETER,
            viewWidth - CONTROL_POINT_DIAMETER
        ),
        getRandom(
            CONTROL_POINT_DIAMETER,
            viewHeight - CONTROL_POINT_DIAMETER
        )
    )

    open fun init(width: Float, height: Float, totalFrames: Float = 1f) {
        viewWidth = width
        viewHeight = height
        totalIterations = totalFrames
    }
}