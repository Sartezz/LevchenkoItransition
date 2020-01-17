package com.example.data.rest.entity.forecastWeather

import com.google.gson.annotations.SerializedName

data class ForecastApiResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    @SerializedName("list")
    val weatherForecastResponseItem: List<WeatherForecastResponse>,
    val city: CityResponse
)