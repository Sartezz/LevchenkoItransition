package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.WeatherInfoDao
import com.example.data.db.entity.WeatherInfoDb

@Database(entities = [WeatherInfoDb::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
}