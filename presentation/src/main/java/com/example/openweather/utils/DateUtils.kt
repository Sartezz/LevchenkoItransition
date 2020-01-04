package com.example.openweather.utils

import java.text.DateFormat

object DateUtils {

    @JvmStatic
    fun parseBigDate(date: Long): String {
        return DateFormat.getDateInstance().format(date * 1000)
    }
    @JvmStatic
    fun parseSmallDate(date: Long): String {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
    }
}