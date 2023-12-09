package com.dhozhenkohealthdatademo.presentation.healthdata

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dhozhenkohealthdatademo.R
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import com.dhozhenkohealthdatademo.presentation.healthdata.component.HealthDataRow
import com.dhozhenkohealthdatademo.util.formatDoubleToString
import com.dhozhenkohealthdatademo.util.formatLocalDate

@ExperimentalMaterial3Api
@Composable
fun HealthDataScreen(
    navController: NavController,
    viewModel: HealthDataViewModel = hiltViewModel()
) {

    val state by viewModel.healthDataState.collectAsState()

    Scaffold(
        topBar = {
                 CenterAlignedTopAppBar(title = { Text(text = "Health & Activity Insights") })
        },
        modifier = Modifier.fillMaxSize()
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(horizontal = 24.dp)
        ) {
            val lastStepsData = state.stepsData.steps.maxByOrNull { it.date }
            val lastDistanceData = state.distanceData.distances.maxByOrNull { it.date }
            val lastCaloriesData = state.caloriesData.calories.maxByOrNull { it.date }
            val lastSleepData = state.sleepData.sleepDatas.maxByOrNull { it.date }

            HealthDataRow(
                title = "My Steps",
                value = if (lastStepsData?.count != null) formatDoubleToString(lastStepsData.count.toDouble()) else null,
                units = "",
                icon = R.drawable.step_icon,
                loading = state.stepsData.loading,
                date = if (lastStepsData?.date != null) formatLocalDate(lastStepsData.date) else ""
            )
            Spacer(modifier = Modifier.height(16.dp))

            HealthDataRow(
                title = "Distance",
                value = if (lastDistanceData?.value != null) formatDoubleToString(lastDistanceData.value.toDouble()) else null,
                units = if (lastDistanceData?.value != null) "km" else "",
                icon = R.drawable.ruler_icon,
                loading = state.distanceData.loading,
                date = if (lastDistanceData?.date != null) formatLocalDate(lastDistanceData.date) else ""
            )
            Spacer(modifier = Modifier.height(16.dp))

            HealthDataRow(
                title = "Calories",
                value = if (lastCaloriesData?.value != null) formatDoubleToString(lastCaloriesData.value.toDouble()) else null,
                units = if (lastCaloriesData?.value != null) "kcal" else "",
                icon = R.drawable.fire_icon,
                loading = state.caloriesData.loading,
                date = if (lastCaloriesData?.date != null) formatLocalDate(lastCaloriesData.date) else ""
            )
            Spacer(modifier = Modifier.height(16.dp))

            HealthDataRow(
                title = "Bed Time",
                value = lastSleepData?.hours,
                units = "",
                icon = R.drawable.sleep_icon,
                loading = state.stepsData.loading,
                date = if (lastSleepData?.date != null) formatLocalDate(lastSleepData.date) else ""
            )
        }
    }
}