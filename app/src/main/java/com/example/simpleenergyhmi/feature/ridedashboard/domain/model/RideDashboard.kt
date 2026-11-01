package com.example.simpleenergyhmi.feature.ridedashboard.domain.model

data class RideDashboard(
    val speed: RideDashbordSignal<Float>,
    val motorStatus: RideDashbordSignal<MotorStatus>,
    val killSwitchStatus: RideDashbordSignal<KillSwitchStatus>,
    val rideMode: RideDashbordSignal<RideMode>
)

enum class MotorStatus {
    ON, OFF
}

enum class KillSwitchStatus {
    ACTIVE, INACTIVE
}

enum class RideMode {
    PARKED,
    READY,
    RIDING,
    ACCELERATING,
    MOTOR_DISABLED
}