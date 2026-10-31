package com.example.simpleenergyhmi.feature.ridedashboard.presentation.components

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
import androidx.compose.material.icons.filled.LteMobiledata
import androidx.compose.material.icons.filled.ShareLocation
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.simpleenergyhmi.R
import com.example.simpleenergyhmi.core.theme.SimpleEnergyHMITheme
import com.example.simpleenergyhmi.core.theme.bleBlueColor

@Preview(
    name = "TopBar",
    showBackground = true,
    backgroundColor = 0xFF000000,
    widthDp = 1200,
)
@Composable
private fun RideDashboardTopBarPreview() {
    SimpleEnergyHMITheme {
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
    }
}

@Composable
fun RideDashboardTopBar(
    modifier: Modifier = Modifier,
    isBlePaired: Boolean,
    blePairedDeviceName : String = "Scooter",
    bleBatteryPercentage: String = "",
    isScooterInIncognitoMode: Boolean = false,
    isScooterInDNDMode: Boolean = false,
    // riderMode : RideMode = RideMode.RIDE,
    mobileNotifications : String = "",
    scooterSystemNotifications : String = "",
    isActiveCall : Boolean = false,
    activeCallTime : String = "",
    isLiveLocationSharing : Boolean = false,
    activeGeoFenceDetails : String = "",
    currentSystemDate : String = "",
    currentSystemTime : String = ""
) {

    val bleDeviceNameFormatted = if (blePairedDeviceName.length > 10){
        blePairedDeviceName.take(10) + "...."
    } else {
        blePairedDeviceName
    } + "(${bleBatteryPercentage})"

    Row(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Bluetooth,
                    tint = bleBlueColor,
                    contentDescription = "Ble icon"
                )

                Text(
                    text = bleDeviceNameFormatted,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(
                modifier = Modifier.padding(10.dp)
            )
            AsyncImage(
                modifier = Modifier
                    .size(24.dp),
                model = R.drawable.ic_incognito_mode,
                contentDescription = "Incognito mode icon",
            )
            Spacer(
                modifier = Modifier.padding(4.dp)
            )
            Icon(
                modifier = Modifier
                    .size(22.dp),
                imageVector = Icons.Default.DoNotDisturb,
                contentDescription = "Ble icon",
                tint = Color.White
            )
        }

        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center

        ){
            Card(
                modifier = Modifier.padding(horizontal = 6.dp),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.Gray
                    ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email icon",
                        tint = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 6.dp),
                        text = mobileNotifications,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Card(
                modifier = Modifier.padding(horizontal = 6.dp),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.Gray
                    ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.BlurCircular,
                        contentDescription = "Email icon",
                        tint = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 6.dp),
                        text = scooterSystemNotifications,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Box {
                Card(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    colors = CardDefaults
                        .cardColors(
                            containerColor = Color.Red.copy(alpha = .5f)
                        ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 3.dp),
                            text = activeCallTime,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Icon(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            imageVector = Icons.Default.CallEnd,
                            contentDescription = "Email icon",
                            tint = Color.White
                        )
                        Spacer(
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(
                            x = 16.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Gray
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.Black),
                    shape = CircleShape
                ) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .padding(6.dp),
                        imageVector = Icons.Default.ChangeHistory,
                        contentDescription = "Email icon",
                        tint = Color.White
                    )
                }
            }
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = bleBlueColor
                ),
                border = BorderStroke(width = 2.dp, color = Color.Black),
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(6.dp),
                    imageVector = Icons.Default.ShareLocation,
                    contentDescription = "Email icon",
                    tint = Color.White
                )
            }
            Spacer(
                modifier = Modifier.padding(2.dp)
            )
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red
                ),
                border = BorderStroke(width = 2.dp, color = Color.Black),
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(6.dp),
                    imageVector = Icons.Default.GpsFixed,
                    contentDescription = "Email icon",
                    tint = Color.White
                )
            }
        }

        Row (
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = currentSystemDate,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = currentSystemTime,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                imageVector = Icons.Default.LteMobiledata,
                contentDescription = "Email icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                imageVector = Icons.Default.SignalCellularAlt,
                contentDescription = "Email icon",
                tint = Color.White
            )
        }
    }
}