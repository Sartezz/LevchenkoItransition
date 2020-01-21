package com.example.data.db.entity.forecastDb

import androidx.room.TypeConverter
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.google.gson.Gson

class Converter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toForecastData(value: String): ForecastData {
            return Gson().fromJson(value, ForecastData::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromForecastData(value: ForecastData): String {
            return Gson().toJson(value)
        }
    }
}