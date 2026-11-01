package com.example.simpleenergyhmi.feature.ridedashboard.data.datasource

import com.example.simpleenergyhmi.feature.ridedashboard.data.model.RideDashboardDataModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

open class VehicleSimulationDataSource @Inject constructor() {

    val simulatedStates = listOf(
        RideDashboardDataModel(
            speed = 0.0f,
            motorOn = false,
            killSwitchActive = false,
            rideMode = "PARKED",
            timestamp = 0L
        ),

        RideDashboardDataModel(
            speed = 0.0f,
            motorOn = true,
            killSwitchActive = false,
            rideMode = "READY",
            timestamp = 0L
        ),

        RideDashboardDataModel(
            speed = 25.4f,
            motorOn = true,
            killSwitchActive = false,
            rideMode = "RIDING",
            timestamp = 0L
        ),

        RideDashboardDataModel(
            speed = 50.0f,
            motorOn = true,
            killSwitchActive = false,
            rideMode = "ACCELERATING",
            timestamp = 0L
        ),

        RideDashboardDataModel(
            speed = 0.0f,
            motorOn = false,
            killSwitchActive = true,
            rideMode = "MOTOR_DISABLED",
            timestamp = 0L
        ),

        //Edge Case
//        RideDashboardDataModel(
//            speed = -10.0f,
//            motorOn = true,
//            killSwitchActive = false,
//            rideMode = "ACCELERATING",
//            timestamp = 0L
//        ),
//
//        RideDashboardDataModel(
//            speed = null,
//            motorOn = true,
//            killSwitchActive = false,
//            rideMode = "ACCELERATING",
//            timestamp = 0L
//        ),
//
//        RideDashboardDataModel(
//            speed = 0.0f,
//            motorOn = null,
//            killSwitchActive = true,
//            rideMode = "MOTOR_DISABLED",
//            timestamp = 0L
//        )
    )

    open fun observeVehicleData(): Flow<RideDashboardDataModel> = flow {

        while (currentCoroutineContext().isActive) {

            simulatedStates.forEach { vehicleData ->

                emit(
                    vehicleData.copy(
                        timestamp = System.currentTimeMillis()
                    )
                )

                delay(1.seconds)

                //Edge Case
              //  throw RuntimeException("Unable to get Data From Vehicle..")
            }
        }
    }
}