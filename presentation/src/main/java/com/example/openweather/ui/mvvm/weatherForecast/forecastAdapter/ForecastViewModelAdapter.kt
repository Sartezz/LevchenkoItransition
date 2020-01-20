package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.openweather.R

class ForecastViewModelAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    private val forecastList: MutableList<WeatherForecast> = ArrayList()
    override fun getItemCount(): Int = forecastList.size

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
        holder.forecastBinding.weatherForecast = forecastList[position]
    }

    fun setForecastList(newForecastList: List<WeatherForecast>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(ForecastWeatherDiffUtilCallback(newForecastList, forecastList))
        diffUtilResult.dispatchUpdatesTo(this)

        this.forecastList.clear()
        this.forecastList.addAll(newForecastList)

    }
}