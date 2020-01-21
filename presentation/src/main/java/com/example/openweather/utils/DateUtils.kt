package com.example.openweather.utils

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat

object DateUtils {
    @JvmStatic
    fun formatToFullDate(date: Long): String {
        return DateFormat.getDateInstance().format(date * DateUtils.SECOND_IN_MILLIS)
    }

    @JvmStatic
    fun formatToTime(date: Long): String {
        return SimpleDateFormat("hh:mm").format(date * DateUtils.SECOND_IN_MILLIS)
    }

    @JvmStatic
    fun myFormatToTime(date: Long): String {
        return SimpleDateFormat(
            "dd MMMM",
            myDateFormatSymbols
        ).format(date * DateUtils.SECOND_IN_MILLIS)
    }

    private val myDateFormatSymbols = object : DateFormatSymbols() {
        override fun getMonths(): Array<String> {
            return arrayOf(
                "января",
                "февраля",
                "марта",
                "апреля",
                "мая",
                "июня",
                "июля",
                "августа",
                "сентября",
                "октября",
                "ноября",
                "декабря"
            )
        }
    }
}