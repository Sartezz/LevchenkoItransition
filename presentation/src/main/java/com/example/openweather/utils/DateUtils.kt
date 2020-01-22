package com.example.openweather.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun formatToFullDate(date: Long): String {
        return DateFormat.getDateInstance().format(date)
    }

    @JvmStatic
    fun formatToTime(date: Long): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
    }

    @JvmStatic
    fun myFormatToTime(date: Long): String {
        return SimpleDateFormat(
            "dd MMMM",
            Locale.getDefault()
        ).format(date)
    }
}