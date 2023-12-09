package com.dhozhenkohealthdatademo.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDoubleToString(value: Double): String {
    val formattedString = if (value % 1 == 0.0) {
        String.format("%.0f", value)
    } else {
        String.format("%.1f", value)
    }
    return formattedString
}

fun formatLocalDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
    return date.format(formatter)
}