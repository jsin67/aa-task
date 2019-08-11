package com.example.androidtest.utils

import android.content.Context
import android.net.ConnectivityManager
import java.text.DecimalFormat
import java.text.SimpleDateFormat

/**
 * Checks internet connection
 */
fun verifyAvailableNetwork(activity: Context): Boolean {
    val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

/**
 * Converts to string date format
 * @param time: time value
 */
fun convertDateFormat(time: String): String {
    val sdfSource = SimpleDateFormat("MM-dd-yyyy hh:mm:ss")

    //parse the string into Date object
    val date = sdfSource.parse(time)

    //create SimpleDateFormat object with desired date format
    val sdfDestination = SimpleDateFormat("E hh:mm")

    //parse the date into another format
    return sdfDestination.format(date)
}

fun convertKtoC(temp: Double): String{
    val df = DecimalFormat("#.##C")
    return df.format(temp.minus( 273.15))
}