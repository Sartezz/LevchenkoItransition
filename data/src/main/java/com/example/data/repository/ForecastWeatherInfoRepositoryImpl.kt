package com.example.data.repository

import android.util.Log
import com.example.data.rest.api.OpenWeatherApi
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.domain.repository.ForecastWeatherInfoRepository
import com.example.utils.transformToWeatherForecast
import io.reactivex.Single
import javax.inject.Inject

class ForecastWeatherInfoRepositoryImpl @Inject constructor(private val weatherApi: OpenWeatherApi) :
    ForecastWeatherInfoRepository {
    override fun getWeatherInfo(
        cityName: String?,
        units: String,
        key: String
    ): Single<List<WeatherForecast>> {
        return weatherApi.getForecastInfo(cityName, units, key).map { apiResponse ->
            apiResponse.list.map{
                it.transformToWeatherForecast()
            }
        }
    }
}