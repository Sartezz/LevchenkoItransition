package com.example.data.repository

import com.example.data.db.dao.WeatherInfoDao
import com.example.data.rest.api.CurrentWeatherApi
import com.example.domain.entity.WeatherInfo
import com.example.domain.repository.WeatherInfoRepository
import com.example.utils.transformToWeatherInfo
import io.reactivex.Single
import javax.inject.Inject

class WeatherInfoRepositoryImpl @Inject constructor(
    private val weatherApi: CurrentWeatherApi
) :
    WeatherInfoRepository {

    override fun getWeatherInfo(
        cityName: String?,
        units: String,
        key: String
    ): Single<WeatherInfo> {
        return weatherApi.getWeatherInfo(cityName, units, key)
            .map {
                it.transformToWeatherInfo()
            }

    }

}