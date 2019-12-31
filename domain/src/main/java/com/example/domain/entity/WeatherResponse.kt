package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val weather: List<Weather>,
    val base: String,
    @SerializedName("main")
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    @SerializedName("clouds")
    val clouds: Clouds,
    val dt: Long,
    @SerializedName("sys")
    val sys: Sys,
    val id: Int,
    val name: String,
    val cod: Int
)