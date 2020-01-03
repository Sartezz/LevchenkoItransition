package com.example.domain.repository

import com.example.domain.entity.WeatherInfo
import io.reactivex.Single

interface WeatherInfoRepository {
    fun getWeatherInfo(cityName: String?, units: String, key: String): Single<WeatherInfo>
}