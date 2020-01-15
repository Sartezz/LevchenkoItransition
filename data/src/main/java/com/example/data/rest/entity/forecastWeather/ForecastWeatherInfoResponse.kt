package com.example.data.rest.entity.forecastWeather

data class ForecastWeatherInfoResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastResponseList>,
    val city: CityResponse
)