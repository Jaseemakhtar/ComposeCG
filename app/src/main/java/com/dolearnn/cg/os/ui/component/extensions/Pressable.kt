package com.dolearnn.cg.os.ui.component.extensions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.pressable(
    onClick: (() -> Unit)? = null
): Modifier = composed {

    val springSpec = remember {
        SpringSpec<Float>(
            stiffness = 1500f,
            dampingRatio = 0.29f
        )
    }

    val animatable = remember {
        Animatable(1f)
    }

    graphicsLayer {
        scaleX = animatable.value
        scaleY = animatable.value
    }.pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    try {
                        animatable.animateTo(0.8f)
                        awaitRelease()
                    } finally {
                        animatable.stop()
                        animatable.snapTo(0.8f)
                        animatable.animateTo(1f, springSpec)
                        onClick?.invoke()
                    }
                }
            )
        }
}