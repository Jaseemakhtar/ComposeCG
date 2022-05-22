package com.dolearnn.cg.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.dolearnn.cg.R
import com.dolearnn.cg.ui.theme.Blue500
import com.dolearnn.cg.ui.theme.Grey500
import com.dolearnn.cg.ui.extensions.pressable
import com.dolearnn.cg.extensions.dpToPx
import com.dolearnn.cg.usecase.LerpUseCase

private val PLAYER_PROGRESS_LINE = 4f.dpToPx()
private val PLAYER_PROGRESS_POINT_RADIUS = 4f.dpToPx()

@Composable
fun PlayerController(
    isPlayingLiveData: LiveData<Boolean>,
    showAddRemove: Boolean,
    onClickToggle: () -> Unit,
    onClickAdd: () -> Unit,
    onClickRemove: () -> Unit
) {
    val isPlaying by isPlayingLiveData.observeAsState(false)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = if (isPlaying) R.drawable.ic_stop else R.drawable.ic_play),
            contentDescription = if (isPlaying) "Stop" else "Play",
            modifier = Modifier
                .size(34.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(
                        radius = 22.dp,
                        color = Color.White,
                        bounded = false
                    )
                ) {
                    onClickToggle()
                }
        )

        Spacer(modifier = Modifier.width(12.dp))

        PlayerProgressBar(
            isPlaying,
            Modifier
                .weight(1f)
                .height(2.dp)
        )

        if (showAddRemove) {
            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add Control Point",
                modifier = Modifier
                    .size(34.dp)
                    .pressable {
                        onClickAdd()
                    }
                    .border(BorderStroke(2.dp, color = Color(0xFF5FCFFF)), RoundedCornerShape(4.dp))
                    .padding(2.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_remove),
                contentDescription = "Remove Control Point",
                modifier = Modifier
                    .size(34.dp)
                    .pressable {
                        onClickRemove()
                    }
                    .border(BorderStroke(2.dp, color = Color(0xFF5FCFFF)), RoundedCornerShape(4.dp))
                    .padding(2.dp)
            )
        }
    }
}

@Composable
fun PlayerProgressBar(isPlaying: Boolean, modifier: Modifier) {
    val animatable = remember {
        Animatable(0f)
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying)
            animatable.animateTo(1f, tween(6500, 0, LinearEasing))
        else {
            animatable.stop()
            animatable.snapTo(0f)
        }
    }

    Canvas(
        modifier = modifier
    ) {
        val width = size.width
        val height = size.height
        val centerY = height / 2f

        val interpolatedValue = LerpUseCase.lerp(0f, width, animatable.value)

        drawLine(
            color = Blue500,
            start = Offset(0f, centerY),
            end = Offset(interpolatedValue, centerY),
            strokeWidth = PLAYER_PROGRESS_LINE,
            cap = StrokeCap.Round
        )

        drawLine(
            color = Grey500,
            start = Offset(interpolatedValue, centerY),
            end = Offset(width, centerY),
            strokeWidth = PLAYER_PROGRESS_LINE,
            cap = StrokeCap.Round
        )

        drawCircle(
            color = Color.White,
            radius = PLAYER_PROGRESS_POINT_RADIUS,
            center = Offset(interpolatedValue, centerY)
        )
    }
}

