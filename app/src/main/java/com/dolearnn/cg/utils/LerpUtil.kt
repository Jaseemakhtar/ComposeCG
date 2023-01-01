package com.dolearnn.cg.utils

import com.dolearnn.cg.ui.primitives.Point

object LerpUtil {
    fun lerp(v0: Float, v1: Float, t: Float): Float {
        return (1 - t) * v0 + t * v1
    }

    fun lerp(pointA: Point, pointB: Point, t: Float): Point {
        val x = lerp(pointA.x, pointB.x, t)
        val y = lerp(pointA.y, pointB.y, t)
        return Point(x, y)
    }
}