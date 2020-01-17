package com.example.data.rest.entity.forecastWeather

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    val dt: Long,
    val main: MainResponse,
    val weather: List<WeatherResponse>,
    val clouds: CloudsResponse,
    val wind: WindResponse,
    val sys: SysResponse,
    @SerializedName("dt_txt")
    val dtTxt: String
)