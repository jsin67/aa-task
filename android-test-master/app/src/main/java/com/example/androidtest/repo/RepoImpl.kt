package com.example.androidtest.repo

import android.annotation.SuppressLint
import com.example.androidtest.rest.RestApi

class RepoImpl(private val restApi: RestApi) : Repo {

    /**
     * Calls api to get data.
     * @param data: set data in this reference
     * @param placeName: api end point name
     */
    @SuppressLint("CheckResult")
    override fun getWeatherData(listener: DataDownloadListener?, placeName: String) {

        restApi.getWeatherData(placeName)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe({ templates ->
                val list = templates.body()?.list ?: listOf()
                listener?.onSuccess(list)
            }, {
               listener?.onFailure(it.localizedMessage)
            })
    }
}