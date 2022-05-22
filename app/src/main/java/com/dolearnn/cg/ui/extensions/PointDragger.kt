package com.dolearnn.cg.ui.extensions

import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import kotlinx.coroutines.coroutineScope

fun Modifier.detectPointDrag(
    enable: () -> Boolean,
    onTouch: (Offset) -> Int,
    onDrag: (Offset) -> Unit,
    onDragEnd: () -> Unit
) = composed {
    val interactionSource = remember { MutableInteractionSource() }

    pointerInput(interactionSource) {
        forEachGesture {
            coroutineScope {
                awaitPointerEventScope {
                    val touch = awaitFirstDown()

                    val selectedPointIndex = onTouch(touch.position)

                    if (selectedPointIndex != -1 && enable())
                        while (true) {
                            val dragEvent = awaitDragOrCancellation(touch.id)

                            if (dragEvent == null) {
                                onDragEnd()
                                break
                            }

                            onDrag(dragEvent.positionChange())
                        }

                }
            }
        }
    }
}