package com.dhozhenkohealthdatademo.presentation.healthdata

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import com.dhozhenkohealthdatademo.data.util.onError
import com.dhozhenkohealthdatademo.data.util.onSuccess
import com.dhozhenkohealthdatademo.domain.model.DistanceData
import com.dhozhenkohealthdatademo.domain.model.Steps
import com.dhozhenkohealthdatademo.domain.state.HealthDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class HealthDataViewModel @Inject constructor(private val healthConnectManager: HealthConnectManager) :
    ViewModel() {
    private val _healthDataState = MutableStateFlow(
        HealthDataState(
            stepsData = HealthDataState.StepsData(
                loading = false, steps = listOf()
            ), distanceData = DistanceData(loading = false, distances = listOf())
        )
    )
    val healthDataState = _healthDataState.asStateFlow()

    init {
        getStepsHealthData()
        getDistanceHealthData()
    }

    private fun getDistanceHealthData() {
        val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS)
        val today = Instant.now()
        val weekAgo = startOfDay.toInstant().minus(7, ChronoUnit.DAYS)
        healthConnectManager.readDistanceByTimeRange(
            startTime = weekAgo, endTime = today
        ).onStart {
            _healthDataState.update { state ->
                state.copy(distanceData = state.distanceData.copy(loading = true))
            }
        }.onSuccess { distances ->
            _healthDataState.update { state ->
                state.copy(
                    distanceData = state.distanceData.copy(
                        loading = false, distances = distances
                    )
                )
            }
        }.onError {
            Log.d("DISTANCE", it)
        }.launchIn(viewModelScope)
    }

    private fun getStepsHealthData() {
        val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS)
        val today = Instant.now()
        val weekAgo = startOfDay.toInstant().minus(7, ChronoUnit.DAYS)

        healthConnectManager.readStepsByTimeRange(
            startTime = weekAgo, endTime = today
        ).onStart {
            _healthDataState.update { state ->
                state.copy(stepsData = state.stepsData.copy(loading = true))
            }
        }.onSuccess { stepsMap ->
            _healthDataState.update { state ->
                state.copy(
                    stepsData = state.stepsData.copy(
                        loading = false,
                        steps = stepsMap.map { entry ->
                            Steps(
                                count = entry.value, date = entry.key
                            )
                        })
                )
            }
        }.onError {
            Log.d("STEPS", it)
        }.launchIn(viewModelScope)
    }
}