package com.dhozhenkohealthdatademo.domain.enum

enum class HealthDataType {
    STEPS, CALORIES, DISTANCE, SLEEP;

    fun getTitle(): String {
        return when (this) {
            STEPS -> "My steps"
            CALORIES -> "Total Calories Burned"
            DISTANCE -> "Distance"
            SLEEP -> "My sleep"
        }
    }

    fun getAverageWord(): String {
        return when (this) {
            STEPS -> "steps"
            CALORIES -> "calories"
            DISTANCE -> "distance"
            SLEEP -> "sleep"
        }
    }

    fun getUnit(): String {
        return when (this) {
            STEPS -> ""
            CALORIES -> "kcal"
            DISTANCE -> "km"
            SLEEP -> "hour"
        }
    }
}
