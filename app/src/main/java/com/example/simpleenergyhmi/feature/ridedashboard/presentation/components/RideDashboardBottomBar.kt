package com.example.simpleenergyhmi.feature.ridedashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricScooter
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.simpleenergyhmi.R
import com.example.simpleenergyhmi.core.theme.SimpleEnergyHMITheme
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.MotorStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashbordSignal

enum class IndicatorStatus{
    LEFT,
    RIGHT
}

@Preview(
    name = "TopBar",
    showBackground = true,
    backgroundColor = 0xFF000000,
    widthDp = 1200,
)
@Composable
private fun RideDashboardBottomBarPreview() {
    SimpleEnergyHMITheme {
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
    }
}

@Composable
fun RideDashboardBottomBar(
    modifier: Modifier = Modifier,
    isHighBeam: Boolean,
    isSideStandOpened: Boolean,
    indicatorStatus: IndicatorStatus,
    isServiceDueEnabled: Boolean,
    isTractionControllEnabled: Boolean,
    batteryPercentage: String,
    motorStatus: RideDashbordSignal<MotorStatus>,
    onSettingsMenuClicked: () -> Unit,
    onMapNavigationClicked: () -> Unit,
    onTpmsClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
                    imageVector = Icons.Default.Widgets,
                    tint = Color.White,
                    contentDescription = "Settings"
                )
            }
            Spacer(
                modifier = Modifier.padding(4.dp)
            )
            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    imageVector = Icons.Default.Navigation,
                    tint = Color.White,
                    contentDescription = "Settings"
                )
            }
        }

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp),
                    model = R.drawable.ic_high_beam,
                    contentDescription = "Incognito mode icon",
                    colorFilter = ColorFilter.tint(Color.Blue)
                )
            }

            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp),
                    model = R.drawable.ic_side_stand,
                    contentDescription = "Incognito mode icon",
                    colorFilter = ColorFilter.tint(Color.Yellow)
                )
            }

            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(44.dp),
                    model = R.drawable.ic_left_indicator,
                    contentDescription = "Incognito mode icon",
                    colorFilter = ColorFilter.tint(Color.Green)
                )
            }

            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    model = R.drawable.ic_service_due,
                    contentDescription = "Incognito mode icon",
                    colorFilter = ColorFilter.tint(Color(0xFFFFA500))               )
            }
        }

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(5.dp),

        ) {
            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    model = R.drawable.ic_traction_controll,
                    contentDescription = "Incognito mode icon",
                    colorFilter = ColorFilter.tint(Color.Green)
                )
            }

            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    model = R.drawable.ic_right_indicator,
                    contentDescription = "Incognito mode icon",
                    colorFilter = ColorFilter.tint(Color.Green)
                )
            }

            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    model = R.drawable.ic_tpms,
                    contentDescription = "Incognito mode icon",
                )
            }

            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = Color.Green.copy(alpha = .2f)
                ),
                shape = CircleShape
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp),
                        model = R.drawable.ic_battery,
                        contentDescription = "Incognito mode icon",
                        colorFilter = ColorFilter.tint(Color.Green)
                    )
                    Spacer(
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = batteryPercentage,
                        color = Color.Green,
                    )
                }
            }

            MotorStatusCard(motorStatus)
        }
    }
}

@Composable
private fun MotorStatusCard(motorStatus: RideDashbordSignal<MotorStatus>) {

    val (motorValue, color) = when (motorStatus) {

        is RideDashbordSignal.Valid -> {
            when (motorStatus.value) {
                MotorStatus.ON -> "ON" to Color.Green
                MotorStatus.OFF -> "OFF" to Color.Red
            }
        }

        is RideDashbordSignal.Invalid ->
            "INVALID" to Color.Yellow

        is RideDashbordSignal.Stale ->
            "STALE" to Color.Yellow

        RideDashbordSignal.Unavailable ->
            "UNAVAILABLE" to Color.Gray
    }

    Card(
        onClick = {},
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = .2f)
        ),
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(10.dp),
                imageVector = Icons.Default.ElectricScooter,
                tint = color,
                contentDescription = "Settings"
            )
            Spacer(
                modifier = Modifier.padding(2.dp)
            )
            Text(
                text = motorValue,
                color = color
            )
        }
    }
}