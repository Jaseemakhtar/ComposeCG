package com.dolearnn.cg.presentation.algorithms

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dolearnn.cg.R
import com.dolearnn.cg.ui.extensions.ColumnSpacer

@Composable
fun AlgorithmUnavailable() {
    val transition = rememberInfiniteTransition()
    val rotationDegrees: Float by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(62.dp, 56.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_nut),
                contentDescription = "Small Rotating nut",
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.TopEnd)
                    .graphicsLayer {
                        rotationZ = rotationDegrees
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_nut),
                contentDescription = "Small Rotating nut",
                modifier = Modifier
                    .size(42.dp)
                    .align(Alignment.BottomStart)
                    .graphicsLayer {
                        rotationZ = -rotationDegrees
                    }
            )
        }

        ColumnSpacer(space = 16.dp)

        Text(
            text = stringResource(id = R.string.text_algorithm_unavailable),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}