package com.example.openweather.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefreshingValue")
fun isRefreshingState(view: SwipeRefreshLayout, isLoading: Boolean) {
    view.isRefreshing = isLoading
}

@BindingAdapter("visibilityValue")
fun setVisibility(view: View, isVisible: Boolean) {
    if (isVisible) view.visibility = View.VISIBLE
}