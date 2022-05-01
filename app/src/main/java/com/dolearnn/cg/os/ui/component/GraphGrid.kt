package com.dolearnn.cg.os.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.dolearnn.cg.os.ui.theme.DLGreen
import com.dolearnn.cg.os.utils.dpToPx

private val TILE_SIZE = 40f.dpToPx()
private val GRID_LINE = 1f.dpToPx()

@Composable
fun GraphGrid(modifier: Modifier) {
    val grid = remember {
        Path()
    }

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        var startY = (height % TILE_SIZE) / 2f
        var startX = (width % TILE_SIZE) / 2f

        grid.reset()

        while (true) {
            grid.moveTo(startX, 0f)
            grid.lineTo(startX, height)

            startX += TILE_SIZE

            if (startX >= width) break
        }

        while (true) {
            grid.moveTo(0f, startY)
            grid.lineTo(width, startY)

            startY += TILE_SIZE

            if (startY >= height) break
        }


        drawPath(
            path = grid,
            style = Stroke(GRID_LINE),
            brush = Brush.radialGradient(
                0.0f to DLGreen.copy(alpha = 0.6f),
                0.52f to DLGreen.copy(alpha = 0.4f),
                0.68f to DLGreen.copy(alpha = 0.26f),
                0.76f to DLGreen.copy(alpha = 0.18f),
                1.0f to DLGreen.copy(alpha = 0.03f),
                center = Offset(width / 2f, height / 2f),
                radius = (if (height > width) height else width) / 2f
            ),
            alpha = 0.7f
        )
    }
}

@Preview(backgroundColor = 0x00000000, showBackground = true)
@Composable
fun GridPreview() {
    GraphGrid(modifier = Modifier.fillMaxSize())
}