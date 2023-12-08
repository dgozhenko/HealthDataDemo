package com.dhozhenkohealthdatademo.domain.state

import com.dhozhenkohealthdatademo.domain.model.Steps

data class HealthDataState(
    val stepsData: StepsData
) {
    data class StepsData(
        val loading: Boolean,
        val steps: List<Steps>
    )
}
