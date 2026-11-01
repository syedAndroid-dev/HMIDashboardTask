package com.example.simpleenergyhmi.feature.ridedashboard.domain.repository

import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashboard
import kotlinx.coroutines.flow.Flow

interface RideDashboardRepository {

    fun observeVehicleData(): Flow<RideDashboard>

}