package com.example.data.repository

import com.example.data.rest.api.OpenWeatherApi
import com.example.domain.entity.forecastWeatherInfo.ForecastWeatherInfo
import com.example.domain.repository.ForecastWeatherInfoRepository
import com.example.utils.transformToForecastWeatherInfo
import io.reactivex.Single
import javax.inject.Inject

class ForecastWeatherInfoRepositoryImpl @Inject constructor(private val weatherApi: OpenWeatherApi) :
    ForecastWeatherInfoRepository {
    override fun getWeatherInfo(
        cityName: String?,
        units: String,
        key: String
    ): Single<ForecastWeatherInfo> {
        return weatherApi.getForecastInfo(cityName, units, key).map {
            it.transformToForecastWeatherInfo()
        }
    }
}