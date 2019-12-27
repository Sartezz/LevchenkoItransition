package com.example.domain.entity

data class Sys(
    val type: Int,
    val id: Int,
    val message: String,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)