package com.example.data.rest.api

import com.example.data.rest.entity.currentWeather.WeatherInfoResponse
import com.example.data.rest.entity.forecastWeather.ForecastWeatherInfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("/data/2.5/weather")
    fun getWeatherInfo(
        @Query("q") q: String?,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<WeatherInfoResponse>

    @GET("/data/2.5/forecast")
    fun getForecastInfo(
        @Query("q") q: String?,
        @Query("units") units: String,
        @Query("appied") appid: String
    ) : Single<ForecastWeatherInfoResponse>
}