package com.example.domain.entity.forecastWeatherInfo

data class ForecastWeatherInfo(
    val name: String,
    val cnt: Int,
    val sunrise: Long,
    val sunset: Long,
    val weather: List<ForecastList>
)