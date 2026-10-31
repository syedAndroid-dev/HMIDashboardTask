package com.example.simpleenergyhmi.feature.ridedashboard.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BlurCircular
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.ChangeHistory
import androidx.compose.material.icons.filled.DoNotDisturb
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.GpsOff
import androidx.compose.material.icons.filled.LteMobiledata
import androidx.compose.material.icons.filled.ShareLocation
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.simpleenergyhmi.R
import com.example.simpleenergyhmi.core.theme.SimpleEnergyHMITheme
import com.example.simpleenergyhmi.core.theme.bleBlueColor
import com.example.simpleenergyhmi.feature.ridedashboard.presentation.components.RideDashboardTopBar

@Preview(
    name = "Simple Energy HMI RiderDashboard",
    showBackground = true,
    backgroundColor = 0xFF000000,
    widthDp = 1000,
    heightDp = 480
)
@Composable
fun ScooterHMIPreview() {
    SimpleEnergyHMITheme {
        SimpleEnergyRideDashboard()
    }
}

@Composable
fun SimpleEnergyRideDashboard(modifier: Modifier = Modifier) {
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
        bottomBar = {}
    ) { scaffoldPadding ->

    }
}