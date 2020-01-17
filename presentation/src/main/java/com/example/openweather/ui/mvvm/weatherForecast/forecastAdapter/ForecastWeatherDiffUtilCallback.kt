package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast

class ForecastWeatherDiffUtilCallback(
    private val oldList: List<WeatherForecast>,
    private val newList: List<WeatherForecast>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldWeatherForecast: WeatherForecast = oldList[oldItemPosition]
        val newWeatherForecast: WeatherForecast = oldList[oldItemPosition]
        return oldWeatherForecast.dt == newWeatherForecast.dt
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldWeatherForecast: WeatherForecast = oldList[oldItemPosition]
        val newWeatherForecast: WeatherForecast = oldList[oldItemPosition]
        return oldWeatherForecast == newWeatherForecast
    }
}