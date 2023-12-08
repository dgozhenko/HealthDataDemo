package com.dhozhenkohealthdatademo.presentation.healthdata.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhozhenkohealthdatademo.util.formatDoubleToString

@ExperimentalMaterial3Api
@Composable
fun HealthDataGridTile(
    title: String,
    value: Double?,
    units: String?,
    loading: Boolean,
    @DrawableRes icon: Int,
) {
    Card(onClick = { }, modifier = Modifier
        .padding(4.dp)
        .wrapContentSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            IconWithProgressIndicator(icon = icon, title = title, isLoading = loading)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = title, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            if (value != null) {
                Row {
                    Text(text = formatDoubleToString(value), style = TextStyle(fontSize = 16.sp))
                    Text(text = " $units" ?: "")
                }
            }
        }
    }
}