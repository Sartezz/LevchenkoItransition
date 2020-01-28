package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.openweather.AdapterInterface
import com.example.openweather.R

const val TYPE_DATE = 1
const val TYPE_INFO = 2

class ForecastViewModelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ForecastClickListener, AdapterInterface<MutableList<ForecastData>> {
    private val forecastList: MutableList<ForecastData> = ArrayList()
    private val isExpandedList: MutableList<Boolean> = ArrayList()

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
                ), this
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onForecastClicked(data: ForecastDayInfo, position: Int) {
        val newForecastList: MutableList<ForecastData> = ArrayList()
        newForecastList.addAll(forecastList)
        if (data.isExpanded) {
            newForecastList.removeAll(data.list)
        } else {
            newForecastList.addAll(position + 1, data.list)
        }
        data.isExpanded = !data.isExpanded
        setData(newForecastList)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastViewHolder -> holder.forecastBinding.weatherForecast =
                forecastList[position] as WeatherForecast
            is ForecastDataViewHolder -> {
                holder.bindItem(forecastList[position] as ForecastDayInfo)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun setData(data: MutableList<ForecastData>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(ForecastWeatherDiffUtilCallback(forecastList, data))
        diffUtilResult.dispatchUpdatesTo(this)
        forecastList.clear()
        if (isExpandedList.isEmpty()) {
            forecastList.addAll(data)
        } else {
            val newForecastList: MutableList<ForecastData> = ArrayList()
            setIsListExpandedValues(data)
            repopulateList(newForecastList, data)
            forecastList.addAll(newForecastList)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (forecastList[position]) {
            is ForecastDayInfo -> TYPE_DATE
            is WeatherForecast -> TYPE_INFO
            else -> throw IllegalArgumentException()
        }
    }

    fun saveRecyclerData(outState: Bundle, dataKeyString: String) {
        isExpandedList.clear()
        forecastList.forEach {
            if (it is ForecastDayInfo)
                isExpandedList.add(it.isExpanded)
        }
        outState.putBooleanArray(dataKeyString, isExpandedList.toBooleanArray())
    }

    fun restorePreviousData(savedInstanceState: Bundle, dataKeyString: String) {
        isExpandedList.clear()
        savedInstanceState.getBooleanArray(dataKeyString)
            ?.let { isExpandedList.addAll(it.toList()) }
    }

    private fun setIsListExpandedValues(list: MutableList<ForecastData>) {
        for (index in 0 until list.size) {
            if (list[index] is ForecastDayInfo) {
                (list[index] as ForecastDayInfo).isExpanded =
                    isExpandedList[index]
            }
        }
    }

    fun repopulateList(
        expandedForecastList: MutableList<ForecastData>,
        closedList: MutableList<ForecastData>
    ) {
        closedList.forEach {
            if (it is ForecastDayInfo) {
                expandedForecastList.add(it)
                if (it.isExpanded) expandedForecastList.addAll(it.list)
            }
        }
    }
}