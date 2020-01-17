package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.openweather.R
import com.example.openweather.databinding.WeatherItemBinding

class ForecastViewModelAdapter :
    RecyclerView.Adapter<ForecastViewModelAdapter.ForecastViewHolder>() {
    private lateinit var forecastList: List<WeatherForecast>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.weather_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = forecastList.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.forecastBinding.weatherForecast = forecastList[position]
    }
    fun setForecastList(forecastList: List<WeatherForecast>) {
        this.forecastList = forecastList
    }

    inner class ForecastViewHolder(val forecastBinding: WeatherItemBinding) :
        RecyclerView.ViewHolder(forecastBinding.root)
}