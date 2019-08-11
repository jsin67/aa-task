package com.example.androidtest.repo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.androidtest.models.WeatherInformationUIModel
import com.example.androidtest.rest.RestApi
import com.example.androidtest.utils.convertKtoC
import com.example.androidtest.utils.convertDateFormat

class RepoImpl(private val restApi: RestApi) : Repo {

    /**
     * Calls api to get data.
     * @param data: set data in this reference
     * @param placeName: api end point name
     */
    @SuppressLint("CheckResult")
    override fun getWeatherData(data: MutableLiveData<List<WeatherInformationUIModel>>, placeName: String) {
        val weatherInformationUIModel : ArrayList<WeatherInformationUIModel>  = arrayListOf()

        restApi.getWeatherData(placeName)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe({ templates ->
                val list = templates.body()?.list ?: listOf()
                if(!list.isNullOrEmpty()) {
                    list.forEach {
                        weatherInformationUIModel.add(WeatherInformationUIModel(convertDateFormat(it.dt_txt), convertKtoC(it.main.temp_min), convertKtoC(it.main.temp_max), it.weather[0].description))
                    }

                }
                data.value = weatherInformationUIModel
            }, {
                data.value = emptyList()
            })
    }
}