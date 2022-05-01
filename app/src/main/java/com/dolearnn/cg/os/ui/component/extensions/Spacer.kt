package com.dolearnn.cg.os.ui.component.extensions

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnSpacer(space: Dp) {
    Spacer(modifier = Modifier.height(space))
}

@Composable
fun RowSpacer(space: Dp) {
    Spacer(modifier = Modifier.width(space))
}

@Composable
fun ColumnScope.ColumnSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun RowScope.RowSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}