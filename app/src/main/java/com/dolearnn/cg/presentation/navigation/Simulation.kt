package com.dolearnn.cg.presentation.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dolearnn.cg.presentation.simulation.SimulationScreenContent

const val AlgorithmSimulationRoute = "simulationRoute"

const val viewIdArg = "viewId"
const val controlTypeArg = "controlType"
const val algorithmNameArg = "algoName"

internal class AlgorithmSimulationArgs(
    val viewId: Int,
    val controlType: Int,
    val algorithmName: String
) {
    constructor(savedStateHandle: SavedStateHandle): this(
        checkNotNull(savedStateHandle[viewIdArg]) as Int,
        checkNotNull(savedStateHandle[controlTypeArg]) as Int,
        checkNotNull(savedStateHandle[algorithmNameArg]) as String,
    )
}

fun NavGraphBuilder.simulation() {
    composable(
        route = "$AlgorithmSimulationRoute/{$viewIdArg}/{$controlTypeArg}/{$algorithmNameArg}",
        arguments = listOf(
            navArgument(
                name = viewIdArg,
                builder = {
                    type = NavType.IntType
                }
            ),
            navArgument(
                name = controlTypeArg,
                builder = {
                    type = NavType.IntType
                }
            ),
            navArgument(
                name = algorithmNameArg,
                builder = {
                    type = NavType.StringType
                }
            )
        )
    ) {
        SimulationScreenContent()
    }
}

fun NavHostController.navigateToSimulation(viewId: Int, controlType: Int, algorithmName: String) {
    val encodedAlgoName = Uri.encode(algorithmName)

    navigate("$AlgorithmSimulationRoute/$viewId/$controlType/$encodedAlgoName")
}