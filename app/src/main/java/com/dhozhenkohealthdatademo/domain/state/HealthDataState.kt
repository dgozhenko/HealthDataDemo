package com.dhozhenkohealthdatademo.domain.state

import com.dhozhenkohealthdatademo.domain.model.Calories
import com.dhozhenkohealthdatademo.domain.model.DistanceData
import com.dhozhenkohealthdatademo.domain.model.Sleep
import com.dhozhenkohealthdatademo.domain.model.Steps

data class HealthDataState(
    val stepsData: StepsData,
    val distanceData: DistanceData,
    val caloriesData: CaloriesData,
    val sleepData: SleepData
) {
    data class StepsData(
        val loading: Boolean,
        val steps: List<Steps>
    )

    data class CaloriesData(
        val loading: Boolean,
        val calories: List<Calories>
    )

    data class SleepData(
        val loading: Boolean,
        val sleepDatas: List<Sleep>
    )
}
