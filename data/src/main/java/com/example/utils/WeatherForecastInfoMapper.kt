package com.example.utils

import android.text.format.DateUtils
import com.example.data.db.entity.forecastDb.ForecastDataDb
import com.example.data.rest.entity.forecastWeather.WeatherForecastResponse
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast

fun WeatherForecastResponse.transformToWeatherForecast() = WeatherForecast(
    dt * DateUtils.SECOND_IN_MILLIS,
    weather.firstOrNull()?.main ?: "",
    weather.firstOrNull()?.icon ?: "",
    main.temp,
    main.tempMin,
    main.tempMax,
    wind.speed,
    wind.deg,
    main.humidity,
    main.feelsLike
)

fun WeatherForecast.transformToForecastDataDb() =
    ForecastDataDb(dt, main, icon, temp, tempMin, tempMax, windSpeed, windDeg, humidity, feelsLike)

fun ForecastDataDb.transformToWeatherForecast() =
    WeatherForecast(dt, main, icon, temp, tempMin, tempMax, windSpeed, windDeg, humidity, feelsLike)