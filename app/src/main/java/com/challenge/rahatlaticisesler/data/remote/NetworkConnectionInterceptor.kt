package com.challenge.rahatlaticisesler.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.challenge.rahatlaticisesler.R
import com.challenge.rahatlaticisesler.utils.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * A class that responsible for checking network connection
 */
class NetworkConnectionInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkConnectionAvailable()) {
            throw NoConnectionException(context.getString(R.string.noInternetConnection))
        }
        return chain.proceed(chain.request())
    }

    /**
     *  Use this method to check if connection available with wifi or cellular
     */
    private fun isNetworkConnectionAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val info = connectivityManager.activeNetworkInfo ?: return false
            return info.isConnected
        }
    }
}