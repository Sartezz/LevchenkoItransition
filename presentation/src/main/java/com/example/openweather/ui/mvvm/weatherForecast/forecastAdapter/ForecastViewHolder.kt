package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.forecastWeatherInfo.ForecastWeatherInfo
import com.example.openweather.R
import kotlinx.android.synthetic.main.weather_item.view.*
import java.text.DateFormat

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ForecastWeatherInfo, click: (ForecastWeatherInfo) -> Unit, id: Int) {

        itemView.day_of_week.text =
            DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
                .format(item.weather[id].dt)

        Glide.with(itemView.context)
            .asBitmap()
            .load("http://openweathermap.org/img/wn/${item.weather[id].icon}@2x.png")
            .into(itemView.weather_icon)

        itemView.weather_description.text = item.weather[id].main
        itemView.min_max_temperature.text = itemView.context.getString(
            R.string.min_max_temperature,
            item.weather[id].tempMax,
            item.weather[id].tempMin
        )
    }
}