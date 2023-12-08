package com.dhozhenkohealthdatademo.util

fun formatDoubleToString(value: Double): String {
    val formattedString = if (value % 1 == 0.0) {
        String.format("%.0f", value)
    } else {
        String.format("%.1f", value)
    }
    return formattedString
}