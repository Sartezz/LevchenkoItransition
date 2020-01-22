package com.example.data.db.entity.forecastDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.FORECAST_DATABASE

@Entity(tableName = FORECAST_DATABASE)
data class ForecastDataDb(
    @PrimaryKey
    val dt: Long,
    val main: String,
    val icon: String,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val windSpeed: Double,
    val windDeg: Int,
    val humidity: Int,
    val feelsLike: Double
)