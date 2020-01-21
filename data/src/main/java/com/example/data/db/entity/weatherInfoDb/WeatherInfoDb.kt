package com.example.data.db.entity.weatherInfoDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.WEATHER_INFO_DATABASE

@Entity(tableName = WEATHER_INFO_DATABASE)
data class WeatherInfoDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val dt: Long,
    val main: String,
    val icon: String,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val windSpeed: Double,
    val windDeg: Int,
    val sunrise: Long,
    val sunset: Long,
    val humidity: Int,
    val feelsLike: Double
)