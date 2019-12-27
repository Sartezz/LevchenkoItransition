package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val Weather: List<Weather>,
    val base: String,
    @SerializedName("main")
    val Main: Main,
    val visibility: Int,
    val wind: Wind,
    @SerializedName("clouds")
    val Clouds: Clouds,
    val dt: Int,
    @SerializedName("sys")
    val Sys: Sys,
    val id: Int,
    val name: String,
    val cod: Int
)