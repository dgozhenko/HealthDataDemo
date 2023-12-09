package com.dhozhenkohealthdatademo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import com.dhozhenkohealthdatademo.domain.navigation.NavigationRoute
import com.dhozhenkohealthdatademo.domain.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val healthConnectManager: HealthConnectManager): ViewModel() {

    private  val _mainScreenState = MutableStateFlow(MainState(startNavigationRoute = null))
    val mainScreenState = _mainScreenState.asStateFlow()

    init {
        checkForPermissions()
    }
    private fun checkForPermissions() {
        viewModelScope.launch {
            val isPermissionsGranted = healthConnectManager.hasAllPermissions()
            _mainScreenState.update { state ->
                state.copy(startNavigationRoute = if (isPermissionsGranted) NavigationRoute.HealthDataScreenNavigationRoute else NavigationRoute.WelcomeScreenNavigationRoute)
            }
        }

    }

}