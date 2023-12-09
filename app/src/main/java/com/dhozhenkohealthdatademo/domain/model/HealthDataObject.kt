package com.dhozhenkohealthdatademo.domain.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.dhozhenkohealthdatademo.domain.enum.HealthDataType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthDataObject(
    val dataValue: Double,
    val date: String,
    val stringValue: String
) : Parcelable {
    companion object NavigationType : NavType<HealthDataObject>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): HealthDataObject? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): HealthDataObject {
            return Gson().fromJson(value, HealthDataObject::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: HealthDataObject) {
            bundle.putParcelable(key, value)
        }
    }
}

@Parcelize
data class HealthData(
    val type: HealthDataType,
    val data: List<HealthDataObject>
) : Parcelable {
    companion object NavigationType : NavType<HealthData>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): HealthData? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): HealthData {
            return Gson().fromJson(value, HealthData::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: HealthData) {
            bundle.putParcelable(key, value)
        }
    }
}

