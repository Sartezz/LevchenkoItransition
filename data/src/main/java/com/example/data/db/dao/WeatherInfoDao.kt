package com.example.data.db.dao

import androidx.room.*
import com.example.data.db.entity.WeatherInfoDb
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface WeatherInfoDao {
    @Insert
    fun addWeatherInfo(weatherInfo: WeatherInfoDb): Completable

    @Query("SELECT * FROM weatherInfo")
    fun getWeatherInfo(): Maybe<WeatherInfoDb>

    @Query("DELETE FROM weatherInfo where id NOT IN (SELECT id from weatherInfo ORDER BY id DESC LIMIT 1)")
    fun deleteWeatherInfo()
}