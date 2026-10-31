package com.example.simpleenergyhmi.core.utils

enum class VehicleMode(val mode: String) {
    PARKED("PARKED"),
    READY("READY"),
    RIDING("RIDING"),
    REVERSING("REVERSING"),
    ACCELERATING("ACCELERATING"),
    MOTOR_DISABLED("MOTOR_DISABLED"),
}