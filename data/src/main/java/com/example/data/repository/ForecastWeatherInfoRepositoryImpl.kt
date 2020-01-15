package com.example.data.repository

import com.example.domain.entity.forecastWeatherInfo.ForecastWeatherInfo
import com.example.domain.repository.ForecastWeatherInfoRepository
import io.reactivex.Single

class ForecastWeatherInfoRepositoryImpl : ForecastWeatherInfoRepository {
    override fun getWeatherInfo(
        cityName: String?,
        units: String,
        key: String
    ): Single<ForecastWeatherInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}