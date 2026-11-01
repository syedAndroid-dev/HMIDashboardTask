package com.example.simpleenergyhmi.feature.ridedashboard.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.simpleenergyhmi.core.theme.SimpleEnergyHMITheme
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.KillSwitchStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.MotorStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashbordSignal
import com.example.simpleenergyhmi.feature.ridedashboard.presentation.components.IndicatorStatus
import com.example.simpleenergyhmi.feature.ridedashboard.presentation.components.RideDashboardBottomBar
import com.example.simpleenergyhmi.feature.ridedashboard.presentation.components.RideDashboardCenterComponent
import com.example.simpleenergyhmi.feature.ridedashboard.presentation.components.RideDashboardTopBar

@Preview(
    name = "Simple Energy HMI RiderDashboard",
    showBackground = true,
    backgroundColor = 0x000000,
    widthDp = 1000,
    heightDp = 480
)
@Composable
fun ScooterHMIPreview() {
    SimpleEnergyHMITheme {
        Scaffold(
            topBar = {
                RideDashboardTopBar(
                    modifier = Modifier,
                    isBlePaired = true,
                    blePairedDeviceName = "Pixel 9 Pro",
                    bleBatteryPercentage = "18%",
                    isScooterInIncognitoMode = true,
                    isScooterInDNDMode = true,
                    mobileNotifications = "5",
                    scooterSystemNotifications = "2",
                    isActiveCall = true,
                    activeCallTime = "01:48",
                    isLiveLocationSharing = true,
                    activeGeoFenceDetails = "",
                    currentSystemDate = "Fri 24 Oct",
                    currentSystemTime = "10:22 PM"
                )
            },
            bottomBar = {
                RideDashboardBottomBar(
                    modifier = Modifier,
                    isHighBeam = false,
                    isSideStandOpened = false,
                    indicatorStatus = IndicatorStatus.LEFT,
                    isServiceDueEnabled = false,
                    isTractionControllEnabled = false,
                    batteryPercentage = "100%",
                    motorStatus = RideDashbordSignal.Valid(MotorStatus.ON, System.currentTimeMillis()),
                    onSettingsMenuClicked = {},
                    onMapNavigationClicked = {},
                    onTpmsClicked = {}
                )
            },
            containerColor = Color.Black
        ) { scaffoldPadding ->
            RideDashboardCenterComponent(
                modifier = Modifier
                    .padding(
                        top = scaffoldPadding.calculateTopPadding(),
                        bottom = scaffoldPadding.calculateBottomPadding()
                    ),
                rideModeBadge = "",
                rideModePill = "",
                batteryRangeAsPerSOC = "",

                speedoMeterValue = RideDashbordSignal.Valid(80.0f, System.currentTimeMillis()),
                speedMeasureBy = "KP / h",
                odoMeterValue = "1324",
                killSwitchStatus = RideDashbordSignal.Valid(KillSwitchStatus.ACTIVE, System.currentTimeMillis()),

                isRapidBreakerAlert = false,
                isSuperHold = false,
                isOverSpeed = false,
                overSpeedValue = "",
                cruiseControl = ""
            )
        }
    }
}

@Composable
fun SimpleEnergyRideDashboard(
    rideDashboardViewModel: RideDashboardViewModel = hiltViewModel()
) {

    val rideDashboardUiState = rideDashboardViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            RideDashboardTopBar(
                modifier = Modifier,
                isBlePaired = true,
                blePairedDeviceName = "Pixel 9 Pro",
                bleBatteryPercentage = "18%",
                isScooterInIncognitoMode = true,
                isScooterInDNDMode = true,
                mobileNotifications = "5",
                scooterSystemNotifications = "2",
                isActiveCall = true,
                activeCallTime = "01:48",
                isLiveLocationSharing = true,
                activeGeoFenceDetails = "",
                currentSystemDate = "Fri 24 Oct",
                currentSystemTime = "10:22 PM"
            )
        },
        bottomBar = {
            RideDashboardBottomBar(
                modifier = Modifier,
                isHighBeam = false,
                isSideStandOpened = false,
                indicatorStatus = IndicatorStatus.LEFT,
                isServiceDueEnabled = false,
                isTractionControllEnabled = false,
                batteryPercentage = "100%",
                motorStatus = rideDashboardUiState.value.motorStatus,
                onSettingsMenuClicked = {},
                onMapNavigationClicked = {},
                onTpmsClicked = {}
            )
        },
        containerColor = Color.Black
    ) { scaffoldPadding ->
        RideDashboardCenterComponent(
            modifier = Modifier
                .padding(
                    top = scaffoldPadding.calculateTopPadding(),
                    bottom = scaffoldPadding.calculateBottomPadding()
                ),
            rideModeBadge = "",
            rideModePill = "",
            batteryRangeAsPerSOC = "",

            speedoMeterValue = rideDashboardUiState.value.speed,
            speedMeasureBy = "KP / h",
            odoMeterValue = "1324",

            killSwitchStatus = rideDashboardUiState.value.killSwitchStatus,

            isRapidBreakerAlert = false,
            isSuperHold = false,
            isOverSpeed = false,
            overSpeedValue = "",
            cruiseControl = "",
            connectionError = rideDashboardUiState.value.connectionError
        )
    }
}