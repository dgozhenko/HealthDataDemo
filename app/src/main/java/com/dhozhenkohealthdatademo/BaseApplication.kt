package com.dhozhenkohealthdatademo

import android.app.Application
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    val healthConnectManager by lazy {
        HealthConnectManager(this)
    }
}