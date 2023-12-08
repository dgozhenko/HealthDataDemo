package com.dhozhenkohealthdatademo.presentation

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dhozhenkohealthdatademo.HEALTH_CONNECT_SETTINGS_ACTION
import com.dhozhenkohealthdatademo.R
import com.dhozhenkohealthdatademo.data.HealthConnectManager

@Composable
fun WelcomeScreen(
    navController: NavController, healthConnectManager: HealthConnectManager
) {

    val activity = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { scaffoldPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.device_connection_icon),
                contentDescription = "Device Connection Image"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.welcome_title), style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.welcome_detail), style = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.welcome_features_list),
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(12.dp))

            ElevatedButton(onClick = {
                val settingsIntent = Intent()
                settingsIntent.action = HEALTH_CONNECT_SETTINGS_ACTION
                activity.startActivity(settingsIntent)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Enable access to your health data now")
            }
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}