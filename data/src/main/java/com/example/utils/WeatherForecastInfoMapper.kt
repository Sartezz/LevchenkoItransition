package com.example.utils

import com.example.data.rest.entity.forecastWeather.WeatherForecastResponse
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast

fun WeatherForecastResponse.transformToWeatherForecast() = WeatherForecast(
    dt,
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