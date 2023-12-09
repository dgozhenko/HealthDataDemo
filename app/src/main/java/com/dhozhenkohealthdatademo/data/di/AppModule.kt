package com.dhozhenkohealthdatademo.data.di

import android.content.Context
import com.dhozhenkohealthdatademo.data.manager.HealthConnectManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideHealthManager(@ApplicationContext context: Context): HealthConnectManager {
        return HealthConnectManager(context)
    }
}