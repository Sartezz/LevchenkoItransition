package com.example.openweather.utils

import android.text.format.DateUtils
import java.text.DateFormat

object DateUtils {

    @JvmStatic
    fun parseBigDate(date: Long): String {
        return DateFormat.getDateInstance().format(date * DateUtils.SECOND_IN_MILLIS)
    }

    @JvmStatic
    fun parseSmallDate(date: Long): String {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
    }
}