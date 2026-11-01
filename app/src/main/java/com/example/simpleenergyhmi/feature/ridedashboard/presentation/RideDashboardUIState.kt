package com.example.simpleenergyhmi.feature.ridedashboard.presentation

import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.KillSwitchStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.MotorStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashbordSignal
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideMode

data class RideDashboardUiState(
    val speed: RideDashbordSignal<Float> = RideDashbordSignal.Unavailable,
    val motorStatus: RideDashbordSignal<MotorStatus> = RideDashbordSignal.Unavailable,
    val killSwitchStatus: RideDashbordSignal<KillSwitchStatus> = RideDashbordSignal.Unavailable,
    val rideMode: RideDashbordSignal<RideMode> = RideDashbordSignal.Unavailable,
    val connectionError: String? = null
)