package com.example.moviestack.api.repo.smallitemlist.trendingmovie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.smallitemlist.SmallItemRepositoryI
import io.reactivex.disposables.CompositeDisposable

class MoreItemDataSourceFactory(var compositeDisposable: CompositeDisposable,
                                var smallItemRepositoryI: SmallItemRepositoryI,
                                var type : SmallItemList.Type
): DataSource.Factory<Int, Result>() {

    val moviesLiveDataSource =  MutableLiveData<MoreItemDataSource>()

    override fun create(): DataSource<Int, Result> {
        val trendingMoviesDataSource =
            MoreItemDataSource(
                compositeDisposable,
                smallItemRepositoryI,
                type
            )
        moviesLiveDataSource.postValue(trendingMoviesDataSource)
        return trendingMoviesDataSource
    }
}