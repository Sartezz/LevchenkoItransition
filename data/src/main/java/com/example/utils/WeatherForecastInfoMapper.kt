package com.example.utils

import com.example.data.rest.entity.forecastWeather.ForecastResponseList
import com.example.data.rest.entity.forecastWeather.ForecastWeatherInfoResponse
import com.example.domain.entity.forecastWeatherInfo.ForecastList
import com.example.domain.entity.forecastWeatherInfo.ForecastWeatherInfo

fun ForecastResponseList.transformToForecastList() = ForecastList(
    dt,
    weather[0].main,
    weather[0].icon,
    main.temp,
    main.temp_min,
    main.temp_max,
    wind.speed,
    wind.deg,
    main.humidity,
    main.feels_like
)

fun ForecastWeatherInfoResponse.transformToForecastWeatherInfo() = ForecastWeatherInfo(
    city.name, cnt, city.sunrise, city.sunset, list.map { it.transformToForecastList() })