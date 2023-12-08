package com.dhozhenkohealthdatademo.presentation.healthdata

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import com.dhozhenkohealthdatademo.domain.model.Steps
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Composable
fun HealthDataScreen(
    navController: NavController,
    healthConnectManager: HealthConnectManager,
    viewModel: HealthDataViewModel = hiltViewModel()) {

    val state by viewModel.healthDataState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {
            if (state.stepsData.loading) {
                CircularProgressIndicator()
            } else {
                Text(text = state.stepsData.steps.toString())
            }
        }
    }
}