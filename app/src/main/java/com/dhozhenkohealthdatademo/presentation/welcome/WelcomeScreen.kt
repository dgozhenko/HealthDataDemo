package com.dhozhenkohealthdatademo.presentation.welcome

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.Lifecycle.Event.ON_ANY
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.navigation.NavController
import com.dhozhenkohealthdatademo.HEALTH_CONNECT_SETTINGS_ACTION
import com.dhozhenkohealthdatademo.R
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import com.dhozhenkohealthdatademo.domain.navigation.NavigationRoute
import com.dhozhenkohealthdatademo.ui.theme.MainColor
import com.dhozhenkohealthdatademo.util.OnLifecycleEvent
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    navController: NavController, healthConnectManager: HealthConnectManager
) {

    val activity = LocalContext.current
    val scope = rememberCoroutineScope()
    var isShowMoreVisible by remember {
        mutableStateOf(false)
    }

    OnLifecycleEvent { _, event ->
        when (event) {
            ON_CREATE -> Unit
            ON_START -> Unit
            ON_RESUME -> {
                scope.launch {
                    val isPermissionGranted = healthConnectManager.hasAllPermissions()
                    if (isPermissionGranted) {
                        navController.navigate(NavigationRoute.HealthDataScreenNavigationRoute.name)
                    }
                }
            }

            ON_PAUSE -> Unit
            ON_STOP -> Unit
            ON_DESTROY -> Unit
            ON_ANY -> Unit
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { scaffoldPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
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
                    fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MainColor
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MainColor
                ), modifier = Modifier

            ) {
                Text(
                    text = stringResource(R.string.welcome_detail),
                    style = TextStyle(fontSize = 16.sp, color = Color.White),
                    modifier = Modifier.padding(12.dp)
                )
                TextButton(onClick = { isShowMoreVisible = !isShowMoreVisible }) {
                    Text(text = if (isShowMoreVisible) "Show less" else "Show more", style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White))
                }

                AnimatedVisibility(visible = isShowMoreVisible) {
                    Column {
                        Text(
                            text = stringResource(R.string.welcome_features_list),
                            style = TextStyle(fontSize = 16.sp, color = Color.White),
                            modifier = Modifier.fillMaxWidth().padding(12.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(12.dp))

            ElevatedButton(onClick = {
                val settingsIntent = Intent()
                settingsIntent.action = HEALTH_CONNECT_SETTINGS_ACTION
                activity.startActivity(settingsIntent)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Enable access to your health data")
            }
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}