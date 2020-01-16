package com.example.data.rest.entity.forecastWeather

data class WeatherForecastResponse(
    val dt: Long,
    val main: MainResponse,
    val weather: List<WeatherResponse>,
    val clouds: CloudsResponse,
    val wind: WindResponse,
    val sys: SysResponse,
    val dt_txt: String
)