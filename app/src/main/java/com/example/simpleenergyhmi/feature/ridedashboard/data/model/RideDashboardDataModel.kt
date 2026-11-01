package com.example.simpleenergyhmi.feature.ridedashboard.data.model

data class RideDashboardDataModel(
    val speed: Float?,
    val motorOn: Boolean?,
    val killSwitchActive: Boolean?,
    val rideMode: String?,
    val timestamp: Long
)