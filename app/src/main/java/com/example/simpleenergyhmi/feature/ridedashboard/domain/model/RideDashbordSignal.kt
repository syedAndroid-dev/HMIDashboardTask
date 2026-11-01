package com.example.simpleenergyhmi.feature.ridedashboard.domain.model

sealed interface RideDashbordSignal<out T> {

    data class Valid<T>(
        val value: T,
        val timestamp: Long
    ) : RideDashbordSignal<T>

    data class Invalid(
        val reason: String,
        val timestamp: Long
    ) : RideDashbordSignal<Nothing>

    data object Unavailable : RideDashbordSignal<Nothing>

    data class Stale<T>(
        val lastKnownValue: T?,
        val timestamp: Long
    ) : RideDashbordSignal<T>
}