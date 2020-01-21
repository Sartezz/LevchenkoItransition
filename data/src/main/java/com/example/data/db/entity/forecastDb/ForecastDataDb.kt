package com.example.data.db.entity.forecastDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.utils.FORECAST_DATABASE

@Entity(tableName = FORECAST_DATABASE)
data class ForecastDataDb(@PrimaryKey val forecastItem: ForecastData)