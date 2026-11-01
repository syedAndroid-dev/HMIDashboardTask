package com.example.simpleenergyhmi

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.simpleenergyhmi.core.theme.SimpleEnergyHMITheme
import com.example.simpleenergyhmi.feature.ridedashboard.presentation.SimpleEnergyRideDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RideDashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent {
            SimpleEnergyHMITheme {
                SimpleEnergyRideDashboard()
            }
        }
    }
}