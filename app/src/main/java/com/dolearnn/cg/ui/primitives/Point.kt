package com.dolearnn.cg.ui.primitives

import androidx.compose.ui.geometry.Offset

data class Point(
    val x: Float,
    val y: Float,
    val z: Float = 0f
) {
    fun toOffset() = Offset(x, y)

    fun toMatrix() = listOf(listOf(x), listOf(y), listOf(z))

    fun scaleToOffset(factor: Float) = Offset(x * factor, y * factor)

    fun scaleToPoint(factor: Float) = Point(x * factor, y * factor)

    // fun offset(dx: Float, dy: Float) {
    //     x += dx
    //     y += dy
    // }
}