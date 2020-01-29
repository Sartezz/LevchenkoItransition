package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo
import com.example.openweather.databinding.WeatherDateItemBinding

class ForecastDataViewHolder(
    private val weatherDateItemBinding: WeatherDateItemBinding,
    private val clickListener: ForecastClickListener
) : RecyclerView.ViewHolder(weatherDateItemBinding.root) {

    fun bindItem(data: ForecastDayInfo) {
        weatherDateItemBinding.forecastData = data
        itemView.setOnClickListener {
            clickListener.onForecastClicked(adapterPosition)
        }
    }
}