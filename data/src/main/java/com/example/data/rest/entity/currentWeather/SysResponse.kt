package com.example.data.rest.entity.currentWeather

data class SysResponse(
    val type: Int,
    val id: Int,
    val message: String,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)