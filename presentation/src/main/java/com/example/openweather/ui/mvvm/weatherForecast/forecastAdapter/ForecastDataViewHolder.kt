package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo
import com.example.openweather.databinding.WeatherDateItemBinding

class ForecastDataViewHolder(
    private val weatherDateItemBinding: WeatherDateItemBinding,
    private val clickListener: ForecastClickListener
) :
    RecyclerView.ViewHolder(weatherDateItemBinding.root) {
    fun bindItem(data: ForecastDayInfo, forecastList: MutableList<ForecastData>) {
        weatherDateItemBinding.forecastData = forecastList[adapterPosition] as ForecastDayInfo
        itemView.setOnClickListener {
            clickListener.onForecastClicked(data, adapterPosition)
        }
    }
}