package com.example.data.rest.entity.forecastWeather

import com.google.gson.annotations.SerializedName

data class CloudsResponse(
    @SerializedName("all")
    val cloudDensity: Int
)