package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.openweather.R
import com.example.utils.TYPE_DATE
import com.example.utils.TYPE_INFO

class ForecastViewModelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val forecastList: MutableList<ForecastData> = ArrayList()

    override fun getItemCount(): Int = forecastList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_INFO -> ForecastViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.weather_item,
                    parent,
                    false
                )
            )
            TYPE_DATE -> ForecastDataViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.weather_date_item,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastViewHolder -> holder.forecastBinding.weatherForecast =
                forecastList[position] as WeatherForecast
            is ForecastDataViewHolder -> holder.weatherDateItemBinding.forecastData =
                forecastList[position] as ForecastDayInfo
            else -> throw IllegalArgumentException()
        }
    }

    fun setForecastList(newForecastList: List<ForecastData>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(ForecastWeatherDiffUtilCallback(forecastList, newForecastList))
        diffUtilResult.dispatchUpdatesTo(this)

        forecastList.clear()
        forecastList.addAll(newForecastList)
    }

    override fun getItemViewType(position: Int): Int {
        return when (forecastList[position]) {
            is ForecastDayInfo -> TYPE_DATE
            is WeatherForecast -> TYPE_INFO
            else -> throw IllegalArgumentException()
        }
    }
}