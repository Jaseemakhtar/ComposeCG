package com.dolearnn.cg.os.ui.component.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.dolearnn.cg.os.models.Triangle

fun DrawScope.drawTriangle(triangle: Triangle, color: Color, stroke: Float) {
    drawLine(
        color = color,
        start = triangle.a.toOffset(),
        end = triangle.b.toOffset(),
        strokeWidth = stroke
    )
    drawLine(
        color = color,
        start = triangle.b.toOffset(),
        end = triangle.c.toOffset(),
        strokeWidth = stroke
    )
    drawLine(
        color = color,
        start = triangle.c.toOffset(),
        end = triangle.a.toOffset(),
        strokeWidth = stroke
    )
}