package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.ForecastDataDao
import com.example.data.db.dao.WeatherInfoDao
import com.example.data.db.entity.forecastDb.ForecastDataDb
import com.example.data.db.entity.weatherInfoDb.WeatherInfoDb

@Database(
    entities = [WeatherInfoDb::class, ForecastDataDb::class],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
    abstract fun forecastDataDao(): ForecastDataDao
}