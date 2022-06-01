package com.dolearnn.cg.usecase

import com.dolearnn.cg.ui.primitives.Point

object MatrixMultiplicationUseCase {
    fun matrixMultiply(point: Point, matrix: List<List<Float>>): Point {
        val currentPoint = point.toMatrix()
        val result = Array(3) { 0f }

        for (i in matrix.indices) {
            var summation = 0f
            for (j in matrix.first().indices) {
                summation += matrix[i][j] * currentPoint[j][0]
            }
            result[i] = summation
        }

        return Point(result[0], result[1], result[2])
    }
}