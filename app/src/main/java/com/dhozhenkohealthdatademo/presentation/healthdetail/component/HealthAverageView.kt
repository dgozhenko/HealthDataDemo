package com.dhozhenkohealthdatademo.presentation.healthdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhozhenkohealthdatademo.R
import com.dhozhenkohealthdatademo.domain.enum.HealthDataType
import com.dhozhenkohealthdatademo.domain.model.HealthDataObject
import com.dhozhenkohealthdatademo.ui.theme.MainColor
import com.dhozhenkohealthdatademo.util.formatDoubleToString

@Composable
fun HealthAverageView(
    type: HealthDataType,
    data: List<HealthDataObject>
) {
    var averageCount = 0.0
    data.forEach {
        averageCount += it.dataValue
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MainColor
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.avg_time_icon),
                contentDescription = "Average Icon"
            )
            Text(
                text = "Average ${type.getAverageWord()}",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Text(text = "${formatDoubleToString((averageCount / data.count()))} ${type.getUnit()}")
        }
    }
}