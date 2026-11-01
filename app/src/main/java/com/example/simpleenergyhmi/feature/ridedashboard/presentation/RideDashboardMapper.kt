package com.example.simpleenergyhmi.feature.ridedashboard.presentation

import com.example.simpleenergyhmi.feature.ridedashboard.domain.model.RideDashbordSignal

fun <T> RideDashbordSignal<T>.checkFreshness(
    currentTime: Long,
    timeout: Long = 3000L
): RideDashbordSignal<T> {

    return when (this) {

        is RideDashbordSignal.Valid -> {

            if (currentTime - timestamp > timeout) {

                RideDashbordSignal.Stale(
                    lastKnownValue = value,
                    timestamp = timestamp
                )

            } else {
                this
            }
        }

        else -> this
    }
}