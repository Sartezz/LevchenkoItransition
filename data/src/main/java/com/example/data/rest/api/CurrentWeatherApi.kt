package com.example.data.rest.api

import com.example.data.rest.entity.currentWeather.WeatherInfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {
    @GET("/data/2.5/weather")
    fun getWeatherInfo(
        @Query("q") q: String?,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<WeatherInfoResponse>
}