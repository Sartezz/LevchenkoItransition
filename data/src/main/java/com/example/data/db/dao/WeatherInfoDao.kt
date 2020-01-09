package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.entity.WeatherInfoDb
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface WeatherInfoDao {
    @Insert
    fun addWeatherInfo(weatherInfo: WeatherInfoDb): Completable

    @Delete
    fun deleteWeatherInfo(weatherInfo: WeatherInfoDb): Completable

    @Query("SELECT * FROM weatherInfo")
    fun getWeatherInfo(): Flowable<List<WeatherInfoDb>>
}