package com.example.simpleenergyhmi.di

import com.example.simpleenergyhmi.feature.ridedashboard.data.repository.RideDashboardRepositoryImpl
import com.example.simpleenergyhmi.feature.ridedashboard.domain.repository.RideDashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindVehicleRepository(
        vehicleRepositoryImpl: RideDashboardRepositoryImpl,
    ): RideDashboardRepository
}
