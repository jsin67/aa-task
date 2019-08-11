package com.example.androidtest.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Receives broadcast whenever there is a change in connection
 */
class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, arg1: Intent) {

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.onNetworkConnectionChanged(verifyAvailableNetwork(context))
        }
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}