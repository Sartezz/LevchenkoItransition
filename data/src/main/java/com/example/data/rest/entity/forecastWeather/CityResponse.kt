package com.example.data.rest.entity.forecastWeather

data class CityResponse(
    val id: Int,
    val name: String,
    val coord: CoordResponse,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)