package com.dolearnn.cg.os.ui.home

import androidx.compose.ui.geometry.Offset
import com.dolearnn.cg.os.algorithms.AbstractState

class AlgorithmUnavailableState : AbstractState() {
    override fun onTouch(touchOffset: Offset): Int = -1

    override fun onDragStart(offset: Offset) {
    }
}