package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.WeatherInfoDao
import com.example.data.db.entity.WeatherInfoDb

import com.example.utils.DATABASE

@Database(entities = [WeatherInfoDb::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDataBase {
            return Room
                .databaseBuilder(context, AppDataBase::class.java, DATABASE)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun weatherInfoDao(): WeatherInfoDao
}