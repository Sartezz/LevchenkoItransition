package com.example.data.rest.entity

import com.google.gson.annotations.SerializedName

data class WeatherInfoResponse(
    @SerializedName("coord")
    val coord: CoordResponse,
    @SerializedName("weather")
    val weather: List<WeatherResponse>,
    val base: String,
    @SerializedName("main")
    val main: MainResponse,
    val visibility: Int,
    val wind: WindResponse,
    @SerializedName("clouds")
    val clouds: CloudsResponse,
    val dt: Long,
    @SerializedName("sys")
    val sys: SysResponse,
    val id: Int,
    val name: String,
    val cod: Int
)