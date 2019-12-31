package com.example.data.repository

import com.example.data.rest.api.CurrentWeatherApi
import com.example.domain.entity.WeatherResponse
import com.example.domain.repository.WeatherInfoRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherInfoInfoRepositoryImpl @Inject constructor(var weatherApi: CurrentWeatherApi) :
    WeatherInfoRepository {

    override fun getWeatherInfo(
        cityName: String?,
        units: String,
        key: String
    ): Single<WeatherResponse> =
        weatherApi.getWeatherInfo(cityName, units, key)
}