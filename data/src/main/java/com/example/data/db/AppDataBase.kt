package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.dao.ForecastDataDao
import com.example.data.db.dao.WeatherInfoDao
import com.example.data.db.entity.forecastDb.Converter
import com.example.data.db.entity.forecastDb.ForecastDataDb
import com.example.data.db.entity.weatherInfoDb.WeatherInfoDb

@Database(
    entities = [WeatherInfoDb::class, ForecastDataDb::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
    abstract fun forecastDataDao(): ForecastDataDao
}