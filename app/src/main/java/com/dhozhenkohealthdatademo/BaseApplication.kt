package com.dhozhenkohealthdatademo

import android.app.Application
import com.dhozhenkohealthdatademo.data.HealthConnectManager

class BaseApplication : Application() {
    val healthConnectManager by lazy {
        HealthConnectManager(this)
    }
}