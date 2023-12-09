package com.dhozhenkohealthdatademo.presentation.healthdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dhozhenkohealthdatademo.domain.enum.HealthDataType
import com.dhozhenkohealthdatademo.domain.model.HealthDataObject
import com.dhozhenkohealthdatademo.presentation.healthdetail.component.HealthAverageView
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


@ExperimentalMaterial3Api
@Composable
fun HealthDetailScreen(
    navController: NavController, type: HealthDataType, data: List<HealthDataObject>
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = type.getTitle()) }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Navigation Back")
            }
        })
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp)
        ) {
            HealthAverageView(data = data, type = type)
            BarChart(barChartData = BarChartData(bars = data.map {
                val dayOfWeek = LocalDate.parse(it.date).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                BarChartData.Bar(value = it.dataValue.toFloat(), color = Color.Blue, label = dayOfWeek)
            }), modifier = Modifier.height(400.dp), labelDrawer = SimpleValueDrawer(drawLocation = SimpleValueDrawer.DrawLocation.Outside))
        }
    }
}