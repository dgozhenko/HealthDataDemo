package com.dhozhenkohealthdatademo.domain.model

import java.time.LocalDate

data class DistanceData(
    val loading: Boolean,
    val distances: List<Distance>
) {
    data class Distance(
        val value: Double,
        val date: LocalDate
    )
}
