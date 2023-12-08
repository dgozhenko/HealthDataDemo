package com.dhozhenkohealthdatademo.domain.state

import com.dhozhenkohealthdatademo.domain.model.DistanceData
import com.dhozhenkohealthdatademo.domain.model.Steps

data class HealthDataState(
    val stepsData: StepsData,
    val distanceData: DistanceData
) {
    data class StepsData(
        val loading: Boolean,
        val steps: List<Steps>
    )
}
