package com.example.data.rest.entity

import com.example.domain.entity.WeatherInfo

fun WeatherInfoResponse.transformToWeatherInfo() = WeatherInfo(
    name,
    dt,
    weather[0].main,
    weather[0].icon,
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