package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.entity.weatherInfoDb.WeatherInfoDb
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface WeatherInfoDao {
    @Insert
    fun addWeatherInfo(weatherInfo: WeatherInfoDb): Completable

    @Query("SELECT * FROM weatherInfo")
    fun getWeatherInfo(): Maybe<WeatherInfoDb>

    @Query("DELETE FROM weatherInfo")
    fun deleteWeatherInfo()
}