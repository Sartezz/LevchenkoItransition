package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast

class ForecastWeatherDiffUtilCallback(
    private val oldList: List<ForecastData>,
    private val newList: List<ForecastData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldWeatherForecast: ForecastData = oldList[oldItemPosition]
        val newWeatherForecast: ForecastData = newList[newItemPosition]

        return if (oldWeatherForecast is WeatherForecast && newWeatherForecast is WeatherForecast) {
            oldWeatherForecast.dt == newWeatherForecast.dt
        } else if (oldWeatherForecast is ForecastDayInfo && newWeatherForecast is ForecastDayInfo) {
            oldWeatherForecast.date == newWeatherForecast.date
        } else false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldWeatherForecast: ForecastData = oldList[oldItemPosition]
        val newWeatherForecast: ForecastData = newList[newItemPosition]
        return oldWeatherForecast == newWeatherForecast
    }
}