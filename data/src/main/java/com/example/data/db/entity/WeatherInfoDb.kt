package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.utils.DATABASE

@Entity(tableName = DATABASE)
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