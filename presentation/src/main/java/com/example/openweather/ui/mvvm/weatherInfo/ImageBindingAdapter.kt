package com.example.openweather.ui.mvvm.weatherInfo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, icon: String?) {

    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    icon?.let {
        Glide.with(view.context)
            .asBitmap()
            .load("http://openweathermap.org/img/wn/$it@2x.png")
            .placeholder(circularProgressDrawable)
            .into(view)
    }
}