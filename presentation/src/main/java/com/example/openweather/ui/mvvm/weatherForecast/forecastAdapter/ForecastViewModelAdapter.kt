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
const val SAVED_EXPANDED_LIST = "savedExpandedList"
const val NO_SUCH_VIEWTYPE = "No such viewtype"

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
            else -> throw IllegalArgumentException(NO_SUCH_VIEWTYPE)
        }
    }

    override fun onForecastClicked(position: Int) {
        val data = forecastList[position]
        if (data is ForecastDayInfo) {
            data.isExpanded = !data.isExpanded
            val newForecastList: MutableList<ForecastData> = ArrayList()
            newForecastList.addAll(forecastList)
            if (data.isExpanded) {
                newForecastList.addAll(position + 1, data.list)
            } else {
                newForecastList.removeAll(data.list)
            }

            isExpandedList.clear()
            newForecastList.forEach {
                if (it is ForecastDayInfo) {
                    isExpandedList.add(it.isExpanded)
                }
            }
            expandItem(newForecastList)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastViewHolder -> holder.forecastBinding.weatherForecast =
                forecastList[position] as WeatherForecast
            is ForecastDataViewHolder -> {
                holder.bindItem(forecastList[position] as ForecastDayInfo)
            }
            else -> throw IllegalArgumentException(NO_SUCH_VIEWTYPE)
        }
    }

    private fun expandItem(data: MutableList<ForecastData>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(ForecastWeatherDiffUtilCallback(forecastList, data))
        diffUtilResult.dispatchUpdatesTo(this)
        forecastList.clear()
        forecastList.addAll(data)
    }

    override fun setData(data: MutableList<ForecastData>) {
        val newForecastList: MutableList<ForecastData> = ArrayList()

        if (isExpandedList.isEmpty()) {
            data.forEach {
                if (it is ForecastDayInfo) {
                    isExpandedList.add(it.isExpanded)
                }
            }
        }

        setIsListExpandedValues(data)
        data.forEach {
            if (it is ForecastDayInfo) {
                newForecastList.add(it)
                if (it.isExpanded) newForecastList.addAll(it.list)
            }
        }

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
            else -> throw IllegalArgumentException(NO_SUCH_VIEWTYPE)
        }
    }

    fun saveRecyclerData(outState: Bundle) {
        isExpandedList.clear()
        forecastList.forEach {
            if (it is ForecastDayInfo)
                isExpandedList.add(it.isExpanded)
        }
        outState.putBooleanArray(SAVED_EXPANDED_LIST, isExpandedList.toBooleanArray())
    }

    fun restorePreviousData(savedInstanceState: Bundle) {
        savedInstanceState.getBooleanArray(SAVED_EXPANDED_LIST)
            ?.let { isExpandedList.addAll(it.toList()) }
    }

    private fun setIsListExpandedValues(list: MutableList<ForecastData>) {
        for (index in 0 until list.size) {
            if (list[index] is ForecastDayInfo) {
                (list[index] as ForecastDayInfo).isExpanded = isExpandedList[index]
            }
        }
    }
}