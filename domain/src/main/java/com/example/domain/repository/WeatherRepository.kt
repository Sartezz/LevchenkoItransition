package com.example.domain.repository

import com.example.domain.entity.WeatherResponse
import io.reactivex.Single

interface WeatherRepository {
    fun getWeatherInfo(cityName: String?, key: String): Single<WeatherResponse>
}