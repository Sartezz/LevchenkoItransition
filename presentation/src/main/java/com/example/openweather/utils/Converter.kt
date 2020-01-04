package com.example.openweather.utils

import android.widget.TextView
import androidx.databinding.InverseMethod

@Suppress("UNUSED", "UNUSED_PARAMETER")
object Converter {

    @InverseMethod("toDouble")
    @JvmStatic
    fun toString(
        view: TextView,
        value: Double
    ): String {
        val text = view.text.toString()
        val parsed = text.toDoubleOrNull() ?: 0.0
        if (parsed == value) {
            return text
        }
        return value.toString()
    }

    @JvmStatic
    fun toDouble(
        view: TextView,
        value: String
    ): Double = value.toDoubleOrNull() ?: 0.0

    @JvmStatic
    fun toInt(
        view: TextView,
        value: String
    ): Int = value.toIntOrNull() ?: 0

    @InverseMethod("toInt")
    @JvmStatic
    fun toString(
        view: TextView,
        value: Int
    ): String {
        val text = view.text.toString()
        val parsed = text.toIntOrNull() ?: 0
        if (parsed == value) {
            return text
        }
        return value.toString()
    }

    @JvmStatic
    fun toLong(
        view: TextView,
        value: String
    ): Long = value.toLongOrNull() ?: 0L

    @InverseMethod("toLong")
    @JvmStatic
    fun toString(
        view: TextView,
        value: Long
    ): String {
        val text = view.text.toString()
        val parsed = text.toLongOrNull() ?: 0L

        if (parsed == value) {
            return text
        }
        return value.toString()
    }


    @JvmStatic
    fun toFloat(
        view: TextView,
        value: String
    ): Float = value.toFloatOrNull() ?: 0f

    @InverseMethod("toFloat")
    @JvmStatic
    fun toString(
        view: TextView,
        value: Float
    ): String {
        val text = view.text.toString()
        val parsed = text.toFloatOrNull() ?: 0f
        if (parsed == value) {
            return text
        }
        return value.toString()
    }
}