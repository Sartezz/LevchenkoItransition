package com.example.openweather.ui.mvvm

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, icon: String) {
    Glide.with(view.context)
        .load("http://openweathermap.org/img/wn/$icon@2x.png")
        .into(view)
}