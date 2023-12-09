package com.dhozhenkohealthdatademo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dhozhenkohealthdatademo.domain.navigation.NavigationRoute
import com.dhozhenkohealthdatademo.presentation.welcome.WelcomeScreen
import com.dhozhenkohealthdatademo.presentation.healthdata.HealthDataScreen
import com.dhozhenkohealthdatademo.ui.theme.HealthDataDemoTheme
import dagger.hilt.android.AndroidEntryPoint

const val HEALTH_CONNECT_SETTINGS_ACTION = "androidx.health.ACTION_HEALTH_CONNECT_SETTINGS"

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val healthConnectManager = (application as BaseApplication).healthConnectManager

        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                viewModel.mainScreenState.value.startNavigationRoute == null
            }
        }

        setContent {
            HealthDataDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val state by viewModel.mainScreenState.collectAsState()
                    NavHost(navController = navController, startDestination = state.startNavigationRoute?.name ?: "") {
                        composable(route = NavigationRoute.WelcomeScreenNavigationRoute.name) {
                            WelcomeScreen(
                                navController = navController, healthConnectManager = healthConnectManager
                            )
                        }
                        composable(route = NavigationRoute.HealthDataScreenNavigationRoute.name) {
                            HealthDataScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}