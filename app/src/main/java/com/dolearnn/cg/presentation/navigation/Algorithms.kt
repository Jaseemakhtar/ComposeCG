package com.dolearnn.cg.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dolearnn.cg.data.database.algorithm.Algorithm
import com.dolearnn.cg.presentation.algorithms.AlgorithmsScreen

const val AlgorithmsRoute = "algorithms"

fun NavGraphBuilder.algorithms(onClickAlgorithm: (Algorithm) -> Unit) {
    composable(AlgorithmsRoute) {
        AlgorithmsScreen(onClickAlgorithm)
    }
}
