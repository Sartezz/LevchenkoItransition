package com.example.data.rest.entity.forecastWeather

data class ForecastResponse(
    val dt: Int,
    val main: MainResponse,
    val weather: List<WeatherResponse>,
    val clouds: CloudsResponse,
    val wind: WindResponse,
    val sys: SysResponse,
    val dt_txt: String
)