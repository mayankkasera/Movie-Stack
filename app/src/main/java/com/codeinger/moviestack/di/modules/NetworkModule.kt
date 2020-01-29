package com.codeinger.moviestack.di.modules

import android.content.Context
import com.codeinger.moviestack.api.repo.discover.DiscoverRequests
import com.codeinger.moviestack.api.repo.movie.MovieItemRequests
import com.codeinger.moviestack.api.repo.person.PersonRequests
import com.codeinger.moviestack.api.repo.search.SearchRequests
import com.codeinger.moviestack.api.repo.trending.TrendingRequest
import com.codeinger.moviestack.di.intercepter.AuthorizationInterceptor
import com.codeinger.tvstack.api.repo.tvshow.TvShowRequests
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getRetrofit(@Named("name") baseUrl :String,okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttp(@Named("appContext") context: Context) : OkHttpClient{
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(AuthorizationInterceptor(context))
            .build()
    }



    @Provides
    @Singleton
    fun provideDiscoverRequest(retrofit: Retrofit) : DiscoverRequests {
        return retrofit.create(DiscoverRequests::class.java)
    }

    @Provides
    @Singleton
    fun providePersonRequest(retrofit: Retrofit) : PersonRequests {
        return retrofit.create(PersonRequests::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieItemRequest(retrofit: Retrofit) : MovieItemRequests {
        return retrofit.create(MovieItemRequests::class.java)
    }


    @Provides
    @Singleton
    fun provideSearchRequest(retrofit: Retrofit) : SearchRequests {
        return retrofit.create(SearchRequests::class.java)
    }

    @Provides
    @Singleton
    fun provideTvShowRequest(retrofit: Retrofit) : TvShowRequests {
        return retrofit.create(TvShowRequests::class.java)
    }

    @Provides
    @Singleton
    fun provideTrendingRequest(retrofit: Retrofit) : TrendingRequest {
        return retrofit.create(TrendingRequest::class.java)
    }


}