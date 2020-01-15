package com.example.data.rest.entity.forecastWeather

data class WeatherResponse(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)