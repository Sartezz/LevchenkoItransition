package com.example.data.repository

import com.example.data.rest.api.CurrentWeatherApi
import com.example.utils.transformToWeatherInfo
import com.example.domain.entity.WeatherInfo
import com.example.domain.repository.WeatherInfoRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherInfoRepositoryImpl @Inject constructor(var weatherApi: CurrentWeatherApi) :
    WeatherInfoRepository {

    override fun getWeatherInfo(
        cityName: String?,
        units: String,
        key: String
    ): Single<WeatherInfo> =
        weatherApi.getWeatherInfo(cityName, units, key)
            .map {
                it.transformToWeatherInfo()
            }
}