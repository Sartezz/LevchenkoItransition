package com.example.utils

import com.example.data.db.entity.WeatherInfoDb
import com.example.data.rest.entity.currentWeather.WeatherInfoResponse
import com.example.domain.entity.weatherInfo.WeatherInfo

fun WeatherInfoResponse.transformToWeatherInfo() = WeatherInfo(
    name,
    dt,
    weather.firstOrNull()?.main ?: "",
    weather.firstOrNull()?.icon ?: "",
    main.temp,
    main.tempMin,
    main.tempMax,
    wind.speed,
    wind.deg,
    sys.sunrise,
    sys.sunset,
    main.humidity,
    main.feelsLike
)

fun WeatherInfoDb.transformToWeatherInfo() = WeatherInfo(
    name,
    dt,
    main,
    icon,
    temp,
    tempMin,
    tempMax,
    windSpeed,
    windDeg,
    sunrise,
    sunset,
    humidity,
    feelsLike
)

fun WeatherInfo.transformToWeatherInfoDb() = WeatherInfoDb(
    null,
    name,
    dt,
    main,
    icon,
    temp,
    tempMin,
    tempMax,
    windSpeed,
    windDeg,
    sunrise,
    sunset,
    humidity,
    feelsLike
)