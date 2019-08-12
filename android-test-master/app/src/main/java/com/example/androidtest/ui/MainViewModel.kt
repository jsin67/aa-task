package com.example.androidtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.models.WeatherInformationUIModel
import com.example.androidtest.models.WeatherResponse
import com.example.androidtest.repo.DataDownloadListener
import com.example.androidtest.repo.Repo
import com.example.androidtest.utils.convertDateFormat
import com.example.androidtest.utils.convertKtoC
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    var weatherLiveData: MutableLiveData<List<WeatherInformationUIModel>> = MutableLiveData()

    fun getWeatherData(placeName: String) {
        repo.getWeatherData(listener, placeName)
    }

    var listener : DataDownloadListener? = object : DataDownloadListener {
        override fun onFailure(message: String) {
            weatherLiveData.value = emptyList()
        }

        override fun onSuccess(list: List<WeatherResponse>) {
            processDataIntoInfo(list)
        }

    }

    fun processDataIntoInfo(list: List<WeatherResponse>){
        val weatherInformationUIModel : ArrayList<WeatherInformationUIModel>  = arrayListOf()
        if(!list.isNullOrEmpty()) {
            list.forEach {
                var description = ""
                it.weather.forEach { obj -> description += obj.description }
                weatherInformationUIModel.add(WeatherInformationUIModel(convertDateFormat(it.dt_txt), convertKtoC(it.main.temp_min), convertKtoC(it.main.temp_max), description ))
            }

        }
        weatherLiveData.value = weatherInformationUIModel
    }


    override fun onCleared() {
        super.onCleared()
        listener = null
    }
}