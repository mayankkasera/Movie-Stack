package com.example.moviestack.api.repo.tvshow.similarpaging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.pojo.Result
import io.reactivex.disposables.CompositeDisposable

class TvShowSimilarDataSourceFactory(var id : String,
                                     var compositeDisposable: CompositeDisposable,
                                     var tvShowRepositoryI: TvShowRepositoryI
): DataSource.Factory<Int, Result>()   {

    val moviesLiveDataSource =  MutableLiveData<TvShowSimilarDataSource>()

    override fun create(): DataSource<Int, Result> {
        val similarDataSource =
            TvShowSimilarDataSource(
                id,
                compositeDisposable,
                tvShowRepositoryI
            )
        moviesLiveDataSource.postValue(similarDataSource)
        return similarDataSource
    }
}