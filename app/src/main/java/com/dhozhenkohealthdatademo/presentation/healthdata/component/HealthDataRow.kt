package com.dhozhenkohealthdatademo.presentation.healthdata.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
@Composable
fun HealthDataRow(
    title: String,
    value: String?,
    units: String?,
    loading: Boolean,
    date: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Card(onClick = { onClick() }, modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            IconWithProgressIndicator(icon = icon, title = title, isLoading = loading)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                if (value != null) {
                    Row {
                        Text(text = value, style = TextStyle(fontSize = 16.sp))
                        Text(text = " $units")
                    }
                }
                if (date.isNotEmpty()) {
                    Text(
                        text = "Last updated - $date",
                        style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Light)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow forward Icon",
                modifier = Modifier
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}