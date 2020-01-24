package com.example.openweather.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter.ForecastViewModelAdapter

@BindingAdapter("data")
fun setRecyclerViewProperties(recyclerView: RecyclerView, items: List<ForecastData>?) {
    if (recyclerView.adapter is ForecastViewModelAdapter) {
        items?.let { (recyclerView.adapter as ForecastViewModelAdapter).setForecastList(it) }
    }
}