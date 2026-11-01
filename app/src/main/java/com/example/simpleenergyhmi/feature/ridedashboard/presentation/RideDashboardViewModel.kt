package com.example.simpleenergyhmi.feature.ridedashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleenergyhmi.feature.ridedashboard.domain.usecase.ObserveVehicleDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class RideDashboardViewModel @Inject constructor(
    observeVehicleDataUseCase: ObserveVehicleDataUseCase
) : ViewModel(){

    private val clock = flow {
        while (true) {
            emit(System.currentTimeMillis())
            delay(1000.milliseconds)
        }
    }

    val uiState: StateFlow<RideDashboardUiState> =
        combine(
            observeVehicleDataUseCase(),
            clock
        ) { rideData, currentTime ->
                RideDashboardUiState(
                    speed = rideData.speed.checkFreshness(
                        currentTime
                    ),
                    motorStatus = rideData.motorStatus.checkFreshness(
                        currentTime
                    ),
                    killSwitchStatus = rideData.killSwitchStatus.checkFreshness(
                        currentTime
                    ),
                    rideMode = rideData.rideMode.checkFreshness(
                        currentTime
                    )
                )
        }.catch { exception ->

            emit(
                RideDashboardUiState(
                    connectionError =
                        "Unable to receive vehicle data"
                )
            )

        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = RideDashboardUiState()
            )

}