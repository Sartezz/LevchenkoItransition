package com.example.domain.repository

import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import io.reactivex.Single

interface ForecastWeatherInfoRepository {
    fun getWeatherInfo(cityName: String?, units: String, key: String): Single<List<WeatherForecast>>
}