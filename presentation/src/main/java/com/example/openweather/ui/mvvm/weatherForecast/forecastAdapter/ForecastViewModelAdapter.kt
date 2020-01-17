package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.openweather.R
import com.example.openweather.databinding.WeatherItemBinding

class ForecastViewModelAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    lateinit var forecastWeatherDiffUtilCallback: ForecastWeatherDiffUtilCallback
    lateinit var diffUtilResult: DiffUtil.DiffResult
    private lateinit var forecastList: MutableList<WeatherForecast>
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


    fun getForecastList(): List<WeatherForecast> {
        return forecastList
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.forecastBinding.weatherForecast = forecastList[position]
    }

    fun setForecastList(forecastList: List<WeatherForecast>) {
        private val forecastWeatherDiffUtilCallback: ForecastWeatherDiffUtilCallback =
            ForecastWeatherDiffUtilCallback()
        this.forecastList.clear()
        this.forecastList.addAll(forecastList)
    }
}

lateinit var forecastWeatherDiffUtilCallback: ForecastWeatherDiffUtilCallback
lateinit var diffUtilResult: DiffUtil.DiffResult

forecastWeatherDiffUtilCallback =
ForecastWeatherDiffUtilCallback(adapter.getForecastList(), forecastList)
diffUtilResult = DiffUtil.calculateDiff(forecastWeatherDiffUtilCallback)
adapter.setForecastList(forecastList)
diffUtilResult.dispatchUpdatesTo(adapter)