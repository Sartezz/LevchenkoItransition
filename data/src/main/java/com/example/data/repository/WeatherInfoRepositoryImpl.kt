package com.example.data.repository

import com.example.data.db.dao.WeatherInfoDao
import com.example.data.rest.api.OpenWeatherApi
import com.example.domain.entity.weatherInfo.WeatherInfo
import com.example.domain.repository.WeatherInfoRepository
import com.example.utils.transformToWeatherInfo
import com.example.utils.transformToWeatherInfoDb
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherInfoRepositoryImpl @Inject constructor(
    private val weatherApi: OpenWeatherApi,
    private val weatherInfoDao: WeatherInfoDao
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
            .doOnSuccess {
                processWeatherInfo(it)
            }
            .onErrorResumeNext(weatherInfoDao.getWeatherInfo().map {
                it.transformToWeatherInfo()
            }.toSingle())
    }

    private fun processWeatherInfo(info: WeatherInfo) {
        Completable.fromAction { weatherInfoDao.deleteWeatherInfo() }
            .andThen(weatherInfoDao.addWeatherInfo(info.transformToWeatherInfoDb()))
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}