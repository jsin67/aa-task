package com.example.androidtest.rest

import com.example.androidtest.models.ApiResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("/data/2.5/forecast")
    fun getWeatherData(@Query("q")name: String, @Query("appid")apiKey: String = "0b7e077bddf1960c096dd19f17665fcf"):
            Single<Response<ApiResponse>>
}