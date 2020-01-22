package com.example.data.repository

import com.example.data.db.dao.ForecastDataDao
import com.example.data.rest.api.OpenWeatherApi
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.domain.repository.ForecastWeatherInfoRepository
import com.example.utils.transformToForecastDataDb
import com.example.utils.transformToWeatherForecast
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ForecastWeatherInfoRepositoryImpl @Inject constructor(
    private val weatherApi: OpenWeatherApi,
    private val forecastDataDao: ForecastDataDao
) :
    ForecastWeatherInfoRepository {
    override fun getWeatherInfo(
        cityName: String?,
        units: String,
        key: String
    ): Single<List<WeatherForecast>> {
        return weatherApi.getForecastInfo(cityName, units, key).map { apiResponse ->
            apiResponse.weatherForecastResponseItem.map {
                it.transformToWeatherForecast()
            }
        }.doOnSuccess {
            processForecastInfo(it)
        }
            .onErrorResumeNext(forecastDataDao.getForecastList().map { forecastDbList ->
                forecastDbList.map {
                    it.transformToWeatherForecast()
                }
            }.toSingle())
    }

    private fun processForecastInfo(info: List<WeatherForecast>) {
        Completable.fromAction { forecastDataDao.deleteForecast() }
            .andThen(forecastDataDao.addForecastList(info.map {
                it.transformToForecastDataDb()
            }))
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}