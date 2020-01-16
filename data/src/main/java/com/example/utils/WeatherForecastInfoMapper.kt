package com.example.utils

import com.example.data.rest.entity.forecastWeather.WeatherForecastResponse
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast

fun WeatherForecastResponse.transformToWeatherForecast() = WeatherForecast(
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