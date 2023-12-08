package com.dhozhenkohealthdatademo.data.manager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.mutableStateOf
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.DistanceRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.dhozhenkohealthdatademo.data.util.UseCaseResult
import com.dhozhenkohealthdatademo.domain.model.Calories
import com.dhozhenkohealthdatademo.domain.model.DistanceData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

enum class HealthConnectAvailability {
    INSTALLED, NOT_INSTALLED, NOT_SUPPORTED
}

const val MIN_SUPPORTED_SDK = Build.VERSION_CODES.O_MR1

class HealthConnectManager(private val context: Context) {
    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }

    val permissions = setOf(
        HealthPermission.getReadPermission(StepsRecord::class),
        HealthPermission.getReadPermission(TotalCaloriesBurnedRecord::class),
        HealthPermission.getReadPermission(HeartRateRecord::class),
        HealthPermission.getReadPermission(DistanceRecord::class),
        HealthPermission.getReadPermission(SleepSessionRecord::class),
        HealthPermission.getReadPermission(SleepSessionRecord::class),
        HealthPermission.getReadPermission(ActiveCaloriesBurnedRecord::class)
    )

    var availability = mutableStateOf(HealthConnectAvailability.NOT_SUPPORTED)
        private set

    init {
        checkAvailability()
    }

    fun readStepsByTimeRange(
        startTime: Instant, endTime: Instant
    ): Flow<UseCaseResult<MutableMap<LocalDate, Int>>> = flow {
        try {
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    StepsRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )

            // Create a map to store steps by day
            val stepsByDay = mutableMapOf<LocalDate, Int>()

            for (stepRecord in response.records) {
                val startDate = stepRecord.startTime.atZone(ZoneId.systemDefault()).toLocalDate()
                val endDate = stepRecord.endTime.atZone(ZoneId.systemDefault()).toLocalDate()

                // Loop through each day in the range of the step record
                var currentDate = startDate
                while (!currentDate.isAfter(endDate)) {
                    // Aggregate steps for each day within the range
                    stepsByDay[currentDate] =
                        (stepsByDay.getOrDefault(currentDate, 0) + stepRecord.count).toInt()
                    currentDate = currentDate.plusDays(1) // Move to the next day
                }
            }

            // Process combined step counts by day
            for ((date, totalSteps) in stepsByDay) {
                Log.d("STEPS", "Date: $date - Total Steps: $totalSteps")
            }
            emit(UseCaseResult.Success(stepsByDay))
        } catch (e: Exception) {
            Log.d("STEPS", e.toString())
            emit(UseCaseResult.Error(e.toString()))
        }
    }

    fun readDistanceByTimeRange(
        startTime: Instant, endTime: Instant
    ): Flow<UseCaseResult<List<DistanceData.Distance>>> = flow {
        try {
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    DistanceRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )

            // Create a map to store steps by day
            val distancesByDay = mutableMapOf<LocalDate, Double>()

            for (distanceRecord in response.records) {
                val startDate =
                    distanceRecord.startTime.atZone(ZoneId.systemDefault()).toLocalDate()
                val endDate = distanceRecord.endTime.atZone(ZoneId.systemDefault()).toLocalDate()

                // Loop through each day in the range of the step record
                var currentDate = startDate
                while (!currentDate.isAfter(endDate)) {
                    // Aggregate steps for each day within the range
                    distancesByDay[currentDate] = (distancesByDay.getOrDefault(currentDate, 0f)
                        .toDouble() + distanceRecord.distance.inKilometers)
                    currentDate = currentDate.plusDays(1) // Move to the next day
                }
            }

            // Process combined step counts by day
            for ((date, totalSteps) in distancesByDay) {
                Log.d("DISTANCE", "Date: $date - Total Distance: $distancesByDay")
            }
            emit(UseCaseResult.Success(distancesByDay.map { entry ->
                DistanceData.Distance(value = entry.value, date = entry.key)
            }))
        } catch (e: Exception) {
            Log.d("DISTANCE", e.toString())
            emit(UseCaseResult.Error(e.toString()))
        }
    }

    fun readCaloriesBurnedByTimeRange(
        startTime: Instant, endTime: Instant
    ): Flow<UseCaseResult<List<Calories>>> = flow {
        try {
            val response = healthConnectClient.readRecords(
                ReadRecordsRequest(
                    TotalCaloriesBurnedRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )

            // Create a map to store steps by day
            val caloriesByDay = mutableMapOf<LocalDate, Double>()

            for (caloriesRecord in response.records) {
                val startDate =
                    caloriesRecord.startTime.atZone(ZoneId.systemDefault()).toLocalDate()
                val endDate = caloriesRecord.endTime.atZone(ZoneId.systemDefault()).toLocalDate()

                // Loop through each day in the range of the step record
                var currentDate = startDate
                while (!currentDate.isAfter(endDate)) {
                    // Aggregate steps for each day within the range
                    caloriesByDay[currentDate] = (caloriesByDay.getOrDefault(currentDate, 0f)
                        .toDouble() + caloriesRecord.energy.inKilocalories)
                    currentDate = currentDate.plusDays(1) // Move to the next day
                }
            }

            // Process combined step counts by day
            for ((date, totalSteps) in caloriesByDay) {
                Log.d("CALORIES", "Date: $date - Total Calories: $caloriesByDay")
            }
            emit(UseCaseResult.Success(caloriesByDay.map { entry ->
                Calories(value = entry.value, date = entry.key)
            }))
        } catch (e: Exception) {
            Log.d("CALORIES", e.toString())
            emit(UseCaseResult.Error(e.toString()))
        }
    }


    fun checkAvailability() {
        availability.value = when {
            HealthConnectClient.getSdkStatus(context) == HealthConnectClient.SDK_AVAILABLE -> HealthConnectAvailability.INSTALLED
            isSupported() -> HealthConnectAvailability.NOT_INSTALLED
            else -> HealthConnectAvailability.NOT_SUPPORTED
        }
    }

    private fun isSupported() = true

    suspend fun hasAllPermissions(): Boolean {
        return healthConnectClient.permissionController.getGrantedPermissions()
            .containsAll(permissions)
    }

    fun requestPermissionsActivityContract(): ActivityResultContract<Set<String>, Set<String>> {
        return PermissionController.createRequestPermissionResultContract()
    }


}