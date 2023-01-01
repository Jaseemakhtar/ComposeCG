package com.dolearnn.cg.presentation.algorithms

import androidx.compose.ui.geometry.Offset
import com.dolearnn.cg.ui.algorithms.AbstractState

class AlgorithmUnavailableState : AbstractState() {
    override fun onTouch(touchOffset: Offset): Int = -1

    override fun onDragStart(offset: Offset) {
    }
}