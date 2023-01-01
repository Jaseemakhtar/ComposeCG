package com.dolearnn.cg.utils

import kotlin.math.cos
import kotlin.math.sin

object RotationMatrixUtils {
    fun getXRotationMatrix(radians: Float) = listOf(
        listOf(1.0f, 0.0f, 0.0f),
        listOf(0.0f, cos(radians), -sin(radians)),
        listOf(0.0f, sin(radians), cos(radians)),
    )

    fun getYRotationMatrix(radians: Float) = listOf(
        listOf(cos(radians), 0.0f, sin(radians)),
        listOf(0.0f, 1.0f, 0.0f),
        listOf(-sin(radians), 0.0f, cos(radians)),
    )

    fun getZRotationMatrix(radians: Float) = listOf(
        listOf(cos(radians), -sin(radians), 0.0f),
        listOf(sin(radians), cos(radians), 0.0f),
        listOf(0.0f, 0.0f, 1.0f),
    )
}