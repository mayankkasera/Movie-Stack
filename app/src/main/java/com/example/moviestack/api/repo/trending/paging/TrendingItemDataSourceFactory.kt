package com.example.moviestack.api.repo.trending.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.trending.TrendingRepositoryI
import io.reactivex.disposables.CompositeDisposable

class TrendingItemDataSourceFactory(
    var compositeDisposable: CompositeDisposable,
    var trendingRepositoryI: TrendingRepositoryI,
    var type : SmallItemList.Type
): DataSource.Factory<Int, Result>()  {


    val moviesLiveDataSource =  MutableLiveData<TrendingItemDataSource>()
    override fun create(): DataSource<Int, Result> {
        val trendingItemDataSource =
            TrendingItemDataSource(
                compositeDisposable,
                trendingRepositoryI,
                type
            )
        moviesLiveDataSource.postValue(trendingItemDataSource)
        return trendingItemDataSource
    }

}