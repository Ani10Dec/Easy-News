package com.easy.easynews.utils

import android.content.Context
import android.net.ConnectivityManager

class AppHelper {

    companion object {
        fun isOnline(mContext: Context): Boolean {
            val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }
    }
}



