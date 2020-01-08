package com.example.openweather.utils

import android.text.format.DateUtils
import java.text.DateFormat

object DateUtils {

    @JvmStatic
    fun formatToFullDate(date: Long): String {
        return DateFormat.getDateInstance().format(date * DateUtils.SECOND_IN_MILLIS)
    }

    @JvmStatic
    fun formatToTime(date: Long): String {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
    }
}