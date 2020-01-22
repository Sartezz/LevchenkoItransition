package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.entity.forecastDb.ForecastDataDb
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface ForecastDataDao {
    @Insert
    fun addForecastList(forecastList: ForecastDataDb): Completable

    @Insert
    fun addForecastList(forecastList: List<ForecastDataDb>): Completable

    @Query("SELECT * FROM forecasts")
    fun getForecastList(): Maybe<List<ForecastDataDb>>

    @Query("DELETE FROM forecasts")
    fun deleteForecast()
}