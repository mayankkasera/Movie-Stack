package com.codeinger.moviestack.api.repo.trending.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.pojo.SmallItemList
import com.codeinger.moviestack.api.repo.trending.TrendingRepositoryI
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