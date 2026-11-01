package com.example.simpleenergyhmi.feature.ridedashboard.data.repository

import com.example.simpleenergyhmi.feature.ridedashboard.data.datasource.VehicleSimulationDataSource
import com.example.simpleenergyhmi.feature.ridedashboard.data.model.RideDashboardDataModel
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.KillSwitchStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.MotorStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideMode
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashboard
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashbordSignal
import com.example.simpleenergyhmi.feature.ridedashboard.domain.repository.RideDashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RideDashboardRepositoryImpl @Inject constructor(
    private val dataSource: VehicleSimulationDataSource
) : RideDashboardRepository {

    override fun observeVehicleData(): Flow<RideDashboard> {
        return dataSource.observeVehicleData()
            .map { data ->
                RideDashboard(
                    speed = validateSpeed(
                        data.speed,
                        data.timestamp
                    ),

                    motorStatus = validateMotorStatus(
                        data.motorOn,
                        data.timestamp
                    ),

                    killSwitchStatus = validateKillSwitch(
                        data.killSwitchActive,
                        data.timestamp
                    ),
                    rideMode = validateRideMode(
                        data.rideMode,
                        data.timestamp
                    )
                )
            }
    }

    private fun validateSpeed(
        speed: Float?,
        timestamp: Long
    ): RideDashbordSignal<Float> {

        return when {

            speed == null ->
                RideDashbordSignal.Unavailable

            speed < 0 || speed > 200 ->
                RideDashbordSignal.Invalid(
                    reason = "Speed out of supported range",
                    timestamp = timestamp
                )

            else ->
                RideDashbordSignal.Valid(
                    value = speed,
                    timestamp = timestamp
                )
        }
    }

    private fun validateMotorStatus(
        motorOn: Boolean?,
        timestamp: Long
    ): RideDashbordSignal<MotorStatus> {

        return when (motorOn) {

            null -> RideDashbordSignal.Unavailable

            true -> RideDashbordSignal.Valid(
                MotorStatus.ON,
                timestamp
            )

            false -> RideDashbordSignal.Valid(
                MotorStatus.OFF,
                timestamp
            )
        }
    }

    private fun validateKillSwitch(
        active: Boolean?,
        timestamp: Long
    ): RideDashbordSignal<KillSwitchStatus> {

        return when (active) {

            null -> RideDashbordSignal.Unavailable

            true -> RideDashbordSignal.Valid(
                KillSwitchStatus.ACTIVE,
                timestamp
            )

            false -> RideDashbordSignal.Valid(
                KillSwitchStatus.INACTIVE,
                timestamp
            )
        }
    }

    private fun validateRideMode(
        rideMode: String?,
        timestamp: Long
    ): RideDashbordSignal<RideMode> {
        return when(rideMode){

            null -> RideDashbordSignal.Unavailable

            "Parked" -> RideDashbordSignal.Valid(
                RideMode.PARKED,
                timestamp
            )

            "Ready" -> RideDashbordSignal.Valid(
                RideMode.READY,
                timestamp
            )

            "Riding" -> RideDashbordSignal.Valid(
                RideMode.RIDING,
                timestamp
            )

            "Accelerating" -> RideDashbordSignal.Valid(
                RideMode.ACCELERATING,
                timestamp
            )

            "MOTOR_DISABLED" -> RideDashbordSignal.Valid(
                RideMode.MOTOR_DISABLED,
                timestamp
            )

            else -> RideDashbordSignal.Invalid(
                reason = "Invalid Ride Mode",
                timestamp = timestamp
            )
        }
    }
}