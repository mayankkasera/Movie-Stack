package com.example.moviestack.di.intercepter

import com.example.moviestack.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newHttpUrl = originalRequest.url().newBuilder().addQueryParameter("api_key",BuildConfig.TMDB_API_KEY).build()
        val newRequest = originalRequest.newBuilder().url(newHttpUrl).build()
        return chain.proceed(newRequest)
    }
}