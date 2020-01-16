package com.example.domain.entity.forecastWeatherInfo

data class WeatherForecast (
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