package com.example.simpleenergyhmi

import com.example.simpleenergyhmi.feature.ridedashboard.data.datasource.VehicleSimulationDataSource
import com.example.simpleenergyhmi.feature.ridedashboard.data.model.RideDashboardDataModel
import com.example.simpleenergyhmi.feature.ridedashboard.data.repository.RideDashboardRepositoryImpl
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.MotorStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashbordSignal
import com.example.simpleenergyhmi.feature.ridedashboard.domain.repository.RideDashboardRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class VehicleRepositoryImplTest {

    private class FakeVehicleDataSource(
        private val data: RideDashboardDataModel
    ) : VehicleSimulationDataSource() {

        override fun observeVehicleData(): Flow<RideDashboardDataModel> {
            return flowOf(data)
        }
    }

    private fun createRepository(
        speed: Float?,
        motorOn: Boolean?,
        killSwitchActive: Boolean?,
        rideMode: String?
    ): RideDashboardRepository {

        val dataSource = FakeVehicleDataSource(
            RideDashboardDataModel(
                speed = speed,
                motorOn = motorOn,
                killSwitchActive = killSwitchActive,
                timestamp = System.currentTimeMillis(),
                rideMode = rideMode
            )
        )

        return RideDashboardRepositoryImpl(dataSource)
    }

    // TC01: Positive scenario
    @Test
    fun `valid speed should return valid signal`() = runTest {

        val repository = createRepository(
            speed = 25.0f,
            motorOn = true,
            killSwitchActive = false,
            rideMode = "Riding",
        )

        val result = repository.observeVehicleData().first()

        assertEquals(
            RideDashbordSignal.Valid(
                value = 25.0f,
                timestamp = (result.speed as RideDashbordSignal.Valid).timestamp
            ),
            result.speed
        )

        assertEquals(
            RideDashbordSignal.Valid(
                MotorStatus.ON,
                (result.motorStatus as RideDashbordSignal.Valid).timestamp
            ),
            result.motorStatus
        )
    }

    // TC02: Edge case - Zero speed
    @Test
    fun `zero speed should be considered valid`() = runTest {

        val repository = createRepository(
            speed = 0.0f,
            motorOn = false,
            killSwitchActive = false,
            rideMode = "Parked"
        )

        val result = repository.observeVehicleData().first()

        assertEquals(
            0.0f,
            (result.speed as RideDashbordSignal.Valid).value
        )
    }

    // TC03: Edge case - Unavailable speed
    @Test
    fun `null speed should return unavailable signal`() = runTest {

        val repository = createRepository(
            speed = null,
            motorOn = true,
            killSwitchActive = false,
            rideMode = "Riding"
        )

        val result = repository.observeVehicleData().first()

        assertEquals(
            RideDashbordSignal.Unavailable,
            result.speed
        )
    }

    // TC04: Negative scenario
    @Test
    fun `negative speed should return invalid signal`() = runTest {

        val repository = createRepository(
            speed = -10.0f,
            motorOn = true,
            killSwitchActive = false,
            rideMode = "Riding"
        )

        val result = repository.observeVehicleData().first()

        val speedSignal = result.speed

        assertTrue(
            speedSignal is RideDashbordSignal.Invalid
        )

        assertEquals(
            "Speed out of supported range",
            (speedSignal as RideDashbordSignal.Invalid).reason
        )
    }

    // TC05: Edge case - Speed above supported limit
    @Test
    fun `speed above maximum should return invalid signal`() = runTest {

        val repository = createRepository(
            speed = 201.0f,
            motorOn = true,
            killSwitchActive = false,
            rideMode = "Riding"
        )

        val result = repository.observeVehicleData().first()

        assertTrue(
            result.speed is RideDashbordSignal.Invalid
        )
    }

    // TC06: Edge case - Motor unavailable
    @Test
    fun `null motor status should return unavailable`() = runTest {

        val repository = createRepository(
            speed = 25.0f,
            motorOn = null,
            killSwitchActive = false,
            rideMode = "Riding"
        )

        val result = repository.observeVehicleData().first()

        assertEquals(
            RideDashbordSignal.Unavailable,
            result.motorStatus
        )

        // Other signals should remain valid.
        assertTrue(result.speed is RideDashbordSignal.Valid)
        assertTrue(result.killSwitchStatus is RideDashbordSignal.Valid)
    }
}