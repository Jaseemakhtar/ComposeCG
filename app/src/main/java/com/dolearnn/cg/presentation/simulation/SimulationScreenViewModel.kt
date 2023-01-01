package com.dolearnn.cg.presentation.simulation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dolearnn.cg.presentation.navigation.AlgorithmSimulationArgs

class SimulationScreenViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val args = AlgorithmSimulationArgs(savedStateHandle)

    val algorithmId: Int get() = args.viewId
    val showAddRemoveControls: Boolean get() = args.controlType == 0
}
