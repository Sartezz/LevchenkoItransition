package com.example.openweather.ui.MVVM

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, icon: String, fragment: Fragment) {
    Glide.with(fragment)
        .load("http://openweathermap.org/img/wn/%1$icon@2x.png")
        .into(view)
}