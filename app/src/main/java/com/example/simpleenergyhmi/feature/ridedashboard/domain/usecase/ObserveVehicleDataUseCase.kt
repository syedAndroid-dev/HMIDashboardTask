package com.example.simpleenergyhmi.feature.ridedashboard.domain.usecase

import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashboard
import com.example.simpleenergyhmi.feature.ridedashboard.domain.repository.RideDashboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveVehicleDataUseCase @Inject constructor(
    private val repository: RideDashboardRepository
) {

    operator fun invoke(): Flow<RideDashboard> {
        return repository.observeVehicleData()
    }


}