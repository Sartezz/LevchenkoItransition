package com.example.openweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.repository.WeatherRepository
import com.example.openweather.BuildConfig
import com.example.openweather.R
import com.example.openweather.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_blank.*
import javax.inject.Inject


class BlankFragment : Fragment() {

    @Inject
    lateinit var weatherInfoRepositoryImpl: WeatherRepository
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
        getWeatherInfo(key)

        fragment_view.setOnClickListener {
            getWeatherInfo(key)
        }
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    companion object {
        fun newInstance(key: String): BlankFragment {
            val bundle = Bundle()
            bundle.putString("KEY", key)
            val fragment = BlankFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun getWeatherInfo(key: String?) {
        disposable = weatherInfoRepositoryImpl.getWeatherInfo(key, BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    city_name.text = it.name
                    weather_type.text = it.weather.first().main
                    temperature.text = (it.main.temp - 273.15).toString()
                    temp_max.text = (it.main.temp_max - 273.15).toString()
                    temp_min.text = (it.main.temp_min - 273.15).toString()
                },
                { }
            )
    }
}
