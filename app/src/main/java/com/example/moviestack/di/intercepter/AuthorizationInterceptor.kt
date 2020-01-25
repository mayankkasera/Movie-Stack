package com.example.moviestack.di.intercepter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.moviestack.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthorizationInterceptor(val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw NoConnectivityException()
        }
        val originalRequest = chain.request()
        val newHttpUrl = originalRequest.url().newBuilder().addQueryParameter("api_key",BuildConfig.TMDB_API_KEY).build()
        val newRequest = originalRequest.newBuilder().url(newHttpUrl).build()
        return chain.proceed(newRequest)
    }


    @Suppress("DEPRECATION")
    private val isConnected: Boolean
        get() {
            var result = false
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm?.run {
                    cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                        result = when {
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                            else -> false
                        }
                    }
                }
            } else {
                cm?.run {
                    cm.activeNetworkInfo?.run {
                        if (type == ConnectivityManager.TYPE_WIFI) {
                            result = true
                        } else if (type == ConnectivityManager.TYPE_MOBILE) {
                            result = true
                        }
                    }
                }
            }
            return result
        }

    class NoConnectivityException : IOException() {
        override val message: String
            get() = "No network available, please check your WiFi or Data connection"
    }


}