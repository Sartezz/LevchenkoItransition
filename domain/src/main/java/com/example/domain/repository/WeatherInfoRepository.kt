package com.example.domain.repository

import com.example.domain.entity.WeatherResponse
import io.reactivex.Single

interface WeatherInfoRepository {
    fun getWeatherInfo(cityName: String?, units: String, key: String): Single<WeatherResponse>
}