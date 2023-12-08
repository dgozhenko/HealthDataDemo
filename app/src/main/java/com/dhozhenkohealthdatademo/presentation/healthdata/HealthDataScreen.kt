package com.dhozhenkohealthdatademo.presentation.healthdata

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dhozhenkohealthdatademo.R
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import com.dhozhenkohealthdatademo.presentation.healthdata.component.HealthDataGridTile
import com.dhozhenkohealthdatademo.util.formatDoubleToString

@ExperimentalMaterial3Api
@Composable
fun HealthDataScreen(
    navController: NavController,
    healthConnectManager: HealthConnectManager,
    viewModel: HealthDataViewModel = hiltViewModel()
) {

    val state by viewModel.healthDataState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { scaffoldPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(horizontal = 24.dp)
        ) {
            item {
                val lastData = state.stepsData.steps.maxByOrNull { it.date }
                HealthDataGridTile(
                    title = "My Steps",
                    value = if (lastData?.count != null) formatDoubleToString(lastData.count.toDouble()) else null,
                    units = "",
                    icon = R.drawable.step_icon,
                    loading = state.stepsData.loading
                )
            }
            item {
                val lastData = state.distanceData.distances.maxByOrNull { it.date }
                HealthDataGridTile(
                    title = "Distance",
                    value = if (lastData?.value != null) formatDoubleToString(lastData.value.toDouble()) else null,
                    units = if (lastData?.value != null) "km" else "",
                    icon = R.drawable.ruler_icon,
                    loading = state.distanceData.loading
                )
            }
            item {
                val lastData = state.caloriesData.calories.maxByOrNull { it.date }
                HealthDataGridTile(
                    title = "Calories",
                    value = if (lastData?.value != null) formatDoubleToString(lastData.value.toDouble()) else null,
                    units = if (lastData?.value != null) "kcal" else "",
                    icon = R.drawable.fire_icon,
                    loading = state.caloriesData.loading
                )
            }
            item {
                val lastData = state.sleepData.sleepDatas.maxByOrNull { it.date }
                HealthDataGridTile(
                    title = "Bed Time",
                    value = lastData?.hours,
                    units = "",
                    icon = R.drawable.sleep_icon,
                    loading = state.stepsData.loading
                )
            }
        }
    }
}