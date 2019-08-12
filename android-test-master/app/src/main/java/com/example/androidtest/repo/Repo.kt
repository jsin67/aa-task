package com.example.androidtest.repo

interface Repo {

    fun getWeatherData(listener: DataDownloadListener?, placeName: String)

}