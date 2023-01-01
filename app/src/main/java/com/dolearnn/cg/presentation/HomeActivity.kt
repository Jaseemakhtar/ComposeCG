package com.dolearnn.cg.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dolearnn.cg.presentation.navigation.AlgorithmsRoute
import com.dolearnn.cg.presentation.navigation.algorithms
import com.dolearnn.cg.presentation.navigation.navigateToSimulation
import com.dolearnn.cg.presentation.navigation.simulation
import com.dolearnn.cg.ui.theme.DoLearnnCgTheme
import com.dolearnn.cg.ui.theme.Turquoise500

class HomeActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

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
                    TopAppBar(navController)

                    NavHost(
                        navController = navController,
                        startDestination = AlgorithmsRoute,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        algorithms(
                            onClickAlgorithm = {
                                navController.navigateToSimulation(
                                    viewId = it.viewId,
                                    controlType = it.controlType,
                                    algorithmName = it.name
                                )
                            }
                        )

                        simulation()
                    }
                }
            }
        }
    }
}