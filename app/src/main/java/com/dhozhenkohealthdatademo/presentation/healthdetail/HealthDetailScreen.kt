package com.dhozhenkohealthdatademo.presentation.healthdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dhozhenkohealthdatademo.domain.model.HealthDataObject

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
}

@ExperimentalMaterial3Api
@Composable
fun HealthDetailScreen(
    type: HealthDataType, data: List<HealthDataObject>
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = type.getTitle()) })
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Text(text = data.toString())
        }
    }
}