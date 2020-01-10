package com.example.moviestack.api.repo.movieInfo.genre

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.movieInfo.similarpaging.SimilarDataSource
import io.reactivex.disposables.CompositeDisposable

class GenreDataSourceFactory(var data : String,
                             var compositeDisposable: CompositeDisposable,
                             var discoverRepositoryI: DiscoverRepositoryI
) : DataSource.Factory<Int, Result>() {


    val moviesLiveDataSource =  MutableLiveData<GenreDataSource>()

    override fun create(): DataSource<Int, Result> {
        val genreDataSource =
            GenreDataSource(
                data,
                compositeDisposable,
                discoverRepositoryI
            )
        moviesLiveDataSource.postValue(genreDataSource)
        return genreDataSource
    }
}