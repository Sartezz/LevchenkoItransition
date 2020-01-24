package com.example.openweather.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openweather.AdapterInterface

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: T?) {
    if (recyclerView.adapter is AdapterInterface<*>) {
        items?.let { (recyclerView.adapter as AdapterInterface<T>).setData(items) }
    }
}