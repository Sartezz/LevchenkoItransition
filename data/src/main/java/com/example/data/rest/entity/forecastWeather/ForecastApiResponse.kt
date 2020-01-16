package com.example.data.rest.entity.forecastWeather

data class ForecastApiResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherForecastResponse>,
    val city: CityResponse
)