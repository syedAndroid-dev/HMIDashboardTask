package com.example.simpleenergyhmi.feature.ridedashboard.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleenergyhmi.core.theme.SimpleEnergyHMITheme
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.KillSwitchStatus
import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashbordSignal

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

            speedoMeterValue = RideDashbordSignal.Valid(80.0f, System.currentTimeMillis()),
            speedMeasureBy = "KP / h",
            odoMeterValue = "1324",

            isRapidBreakerAlert = false,
            isSuperHold = false,
            isOverSpeed = false,
            overSpeedValue = "",
            cruiseControl = "",
            killSwitchStatus = RideDashbordSignal.Valid( KillSwitchStatus.INACTIVE, System.currentTimeMillis())
        )
    }
}

@Composable
fun RideDashboardCenterComponent(
    modifier: Modifier = Modifier,
    rideModeBadge: String,
    rideModePill: String,
    batteryRangeAsPerSOC: String,
    speedoMeterValue: RideDashbordSignal<Float>,
    speedMeasureBy: String,
    odoMeterValue: String,
    isRapidBreakerAlert: Boolean,
    isSuperHold: Boolean,
    isOverSpeed: Boolean,
    overSpeedValue: String,
    cruiseControl: String,
    killSwitchStatus: RideDashbordSignal<KillSwitchStatus>,
    connectionError: String? = null
) {
    val validSpeed = (speedoMeterValue as? RideDashbordSignal.Valid)?.value

    val speedAnimValue by animateFloatAsState(
        targetValue = validSpeed ?: 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "SpeedometerAnimation"
    )

    val displayText = when (speedoMeterValue) {

        is RideDashbordSignal.Valid ->
            speedAnimValue.toInt().toString()

        is RideDashbordSignal.Invalid ->
            "ERR"

        is RideDashbordSignal.Stale ->
            "--"

        RideDashbordSignal.Unavailable ->
            "--"
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "${speedAnimValue.toInt()}",
            fontSize = 70.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = when (speedoMeterValue) {
                is RideDashbordSignal.Valid -> "km/h"
                is RideDashbordSignal.Invalid -> "INVALID"
                is RideDashbordSignal.Stale -> "STALE"
                RideDashbordSignal.Unavailable -> "UNAVAILABLE"
            },
            color = Color.LightGray
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            modifier = Modifier
                .border(width = .5.dp, color = Color.White)
                .padding(6.dp)
        ) {
            Text(
                text = "ODO",
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = "$odoMeterValue km",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        connectionError?.let { error ->

            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        KillSwitchCard(
            status = killSwitchStatus
        )


    }
}

@Composable
private fun KillSwitchCard(status: RideDashbordSignal<KillSwitchStatus>) {

    val (activeStatus, color) = when (status) {

        is RideDashbordSignal.Valid -> {

            when (status.value) {

                KillSwitchStatus.ACTIVE ->
                    "ACTIVE" to Color.Red

                KillSwitchStatus.INACTIVE ->
                    "INACTIVE" to Color.Green
            }
        }

        is RideDashbordSignal.Invalid ->
            "INVALID" to Color.Yellow

        is RideDashbordSignal.Stale ->
            "STALE" to Color.Yellow

        RideDashbordSignal.Unavailable ->
            "UNAVAILABLE" to Color.Gray
    }

    Row(
        modifier = Modifier
            .border(width = .5.dp, color = color, shape = RoundedCornerShape(4.dp))
            .padding(6.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.border(width = 1.dp, color = color, shape = CircleShape)
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                imageVector = Icons.Default.ElectricBolt,
                tint = color,
                contentDescription = "Kill Switch"
            )
        }
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
            thickness = 2.dp,
            color = color
        )
        Column {
            Text(
                text = "KILL SWITCH",
                fontSize = 10.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(1.dp)
            )
            Text(
                text = activeStatus,
                fontSize = 18.sp,
                color = color,
                fontWeight = FontWeight.Bold
            )
        }
    }
}