package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.openweather.R
import com.example.openweather.databinding.WeatherItemBinding

class ForecastViewModelAdapter :
    ListAdapter<WeatherForecast, ForecastViewHolder>
        (ForecastWeatherDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.weather_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.forecastBinding.weatherForecast = getItem(position)
    }
}