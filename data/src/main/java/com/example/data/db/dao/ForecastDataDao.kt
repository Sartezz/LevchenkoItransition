package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import io.reactivex.Maybe

@Dao
interface ForecastDataDao {
    @Query("SELECT * FROM forecasts")
    fun getForecastList(): Maybe<ForecastData>
}