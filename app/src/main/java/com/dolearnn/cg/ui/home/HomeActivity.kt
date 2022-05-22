package com.dolearnn.cg.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dolearnn.cg.data.database.algorithm.Algorithm
import com.dolearnn.cg.R
import com.dolearnn.cg.ui.theme.DoLearnnCgTheme
import com.dolearnn.cg.ui.theme.Turquoise500

// region screens
const val ALGORITHMS_SCREEN = "algorithmsScreen"
const val SIMULATION_SCREEN = "simulationScreen"
// endRegion

// region navArguments
const val VIEW_ID = "algorithmId"
const val ALGORITHM_NAME = "algorithmName"
const val ALGORITHM_CONTROL_TYPE = "controlType"
// endRegion

class HomeActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private var currentScreen = ALGORITHMS_SCREEN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()

            DoLearnnCgTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Turquoise500)
                ) {

                    // Header Region
                    HeaderComponent(navController)
                    // End Region

                    NavHost(
                        navController = navController,
                        startDestination = ALGORITHMS_SCREEN,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        composable(ALGORITHMS_SCREEN) {
                            currentScreen = ALGORITHMS_SCREEN

                            AlgorithmsScreen { algorithm ->
                                navigateToSimulationScreen(algorithm)
                            }
                        }

                        composable(
                            route = "$SIMULATION_SCREEN/{$VIEW_ID}/{$ALGORITHM_CONTROL_TYPE}/{$ALGORITHM_NAME}",
                            arguments = listOf(
                                navArgument(
                                    name = VIEW_ID,
                                    builder = {
                                        type = NavType.IntType
                                    }
                                ),
                                navArgument(
                                    name = ALGORITHM_CONTROL_TYPE,
                                    builder = {
                                        type = NavType.IntType
                                    }
                                ),
                                navArgument(
                                    name = ALGORITHM_NAME,
                                    builder = {
                                        type = NavType.StringType
                                    }
                                )
                            )
                        ) {
                            currentScreen = SIMULATION_SCREEN

                            val algorithmId = it.arguments?.getInt(VIEW_ID) ?: -1
                            val controlType = it.arguments?.getInt(ALGORITHM_CONTROL_TYPE) ?: 1

                            SimulationScreenContent(
                                algorithmId = algorithmId,
                                showAddRemoveControls = controlType == 0
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (currentScreen == ALGORITHMS_SCREEN) {
            finish()
            return
        }

        super.onBackPressed()
    }

    private fun navigateToSimulationScreen(algorithm: Algorithm) {
        navController
            .navigate("$SIMULATION_SCREEN/${algorithm.viewId}/${algorithm.controlType}/${algorithm.name}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val getItemBg: (Int) -> Int = {
        listOf(R.drawable.ic_bg_gradient_card_one, R.drawable.ic_bg_gradient_card_two)[it % 2]
    }

    DoLearnnCgTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Turquoise500)
        ) {
            //    Header Region
            Header(R.drawable.ic_home, "Home")
            //    End Region

            // Algorithms Region
            AlgorithmsScreenContent(
                algorithms = listOf(
                    Algorithm(
                        name = "Koch Curve",
                        description = "Fractal",
                        order = 1,
                        iconURL = "/storage/icons/cg/ic_koch_curve.png",
                        viewId = 1,
                        controlType = 1
                    ),
                    Algorithm(
                        name = "Sierpinsky Gasket",
                        description = "Fractal",
                        order = 2,
                        iconURL = "/storage/icons/cg/ic_sierpensky_triangle.png",
                        viewId = 2,
                        controlType = 1
                    )
                ),
                getBg = getItemBg,
                onClick = {}
            )
            // End Region
        }
    }
}