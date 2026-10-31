package com.example.simpleenergyhmi.feature.ridedashboard.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpleenergyhmi.core.theme.SimpleEnergyHMITheme

@Preview(
    name = "TopBar",
    showBackground = true,
    backgroundColor = 0xFF000000,
    widthDp = 800,
    heightDp = 400
)
@Composable
private fun RideDashboardCenterComponentPreview() {
    SimpleEnergyHMITheme {
        RideDashboardCenterComponent(
            modifier = Modifier,
            rideModeBadge = "",
            rideModePill = "",
            batteryRangeAsPerSOC = "",

            speedoMeterValue = "",
            odoMeterValue = "",

            isRapidBreakerAlert = false,
            isSuperHold = false,
            isOverSpeed = false,
            overSpeedValue = "",
            cruiseControl = ""
        )
    }
}

@Composable
fun RideDashboardCenterComponent(
    modifier: Modifier = Modifier,
    rideModeBadge: String,
    rideModePill: String,
    batteryRangeAsPerSOC: String,
    speedoMeterValue: String,
    odoMeterValue: String,
    isRapidBreakerAlert: Boolean,
    isSuperHold: Boolean,
    isOverSpeed: Boolean,
    overSpeedValue: String,
    cruiseControl: String
) {

}