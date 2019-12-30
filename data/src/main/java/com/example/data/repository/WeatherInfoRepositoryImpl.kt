package com.example.data.repository

import com.example.data.rest.api.CurrentWeatherApi
import com.example.domain.entity.WeatherResponse
import com.example.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherInfoRepositoryImpl @Inject constructor(var weatherApi: CurrentWeatherApi) :
    WeatherRepository {

    override fun getWeatherInfo(cityName: String?, key: String): Single<WeatherResponse> =
        weatherApi.getWeatherInfo(cityName, key)
}