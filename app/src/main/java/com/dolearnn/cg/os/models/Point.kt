package com.dolearnn.cg.os.models

import androidx.compose.ui.geometry.Offset

data class Point(
    val x: Float,
    val y: Float
) {
    fun toOffset() = Offset(x, y)

    // fun offset(dx: Float, dy: Float) {
    //     x += dx
    //     y += dy
    // }
}