package com.example.domain.entity

data class WeatherInfo(
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