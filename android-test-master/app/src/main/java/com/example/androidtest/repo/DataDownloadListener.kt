package com.example.androidtest.repo

import com.example.androidtest.models.WeatherResponse

interface DataDownloadListener {
    fun onSuccess(list: List<WeatherResponse>)

    fun onFailure (message: String)
}