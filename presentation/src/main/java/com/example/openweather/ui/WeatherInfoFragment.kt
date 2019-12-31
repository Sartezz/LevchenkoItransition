package com.example.openweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.BuildConfig
import com.example.openweather.R
import com.example.openweather.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_blank.*
import java.text.DateFormat
import javax.inject.Inject


class WeatherInfoFragment : Fragment() {

    @Inject
    lateinit var weatherInfoInfoRepositoryImpl: WeatherInfoRepository
    @Inject
    lateinit var weatherInfoViewModelFactory: WeatherInfoViewModelFactory

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_blank, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key: String? = arguments?.getString("KEY")
        val viewModel = ViewModelProvider(this, weatherInfoViewModelFactory)
            .get(WeatherInfoViewModel::class.java)

        disposable = viewModel.getWeatherInfo(key, "metric", BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Glide.with(this)
                        .asBitmap()
                        .load(getString(R.string.weather_icon, it.weather.first().icon))
                        .into(weather_icon)
                    city_name.text = it.name
                    weather_type.text = it.weather.first().main
                    temperature.text = it.main.temp.toString() + "°C"
                    temp_max.text = it.main.temp_max.toString() + "°C"
                    temp_min.text = it.main.temp_min.toString() + "°C"
                    feels_like_text.text = it.main.feels_like.toString()
                    current_time.text = DateFormat.getDateInstance().format(it.dt)
                    humidity.text = it.main.humidity.toString()
                    sunrise.text =
                        DateFormat.getTimeInstance(DateFormat.SHORT).format(it.sys.sunrise)
                    sunrise.text =
                        DateFormat.getTimeInstance(DateFormat.SHORT).format(it.sys.sunset)
                    wind.text = getString(R.string.wind, it.wind.deg, it.wind.speed)
                },
                {})
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    companion object {
        fun newInstance(key: String): WeatherInfoFragment {
            val bundle = Bundle()
            bundle.putString("KEY", key)
            val fragment = WeatherInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}