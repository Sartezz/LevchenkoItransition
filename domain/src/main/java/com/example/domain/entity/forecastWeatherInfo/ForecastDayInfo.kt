package com.example.domain.entity.forecastWeatherInfo

data class ForecastDayInfo(val date: Long, var isExpanded:Boolean, val list: MutableList<WeatherForecast>) : ForecastData