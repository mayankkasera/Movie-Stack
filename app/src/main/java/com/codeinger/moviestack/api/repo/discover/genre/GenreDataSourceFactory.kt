package com.codeinger.moviestack.api.repo.discover.genre

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.api.repo.discover.DiscoverRepositoryI
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