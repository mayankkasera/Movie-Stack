package com.codeinger.moviestack.api.repo.tvshow.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.codeinger.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.pojo.SmallItemList
import io.reactivex.disposables.CompositeDisposable

class TvShowDataSourceFactory(
    var compositeDisposable: CompositeDisposable,
    var tvShowRepositoryI: TvShowRepositoryI,
    var type : SmallItemList.Type
): DataSource.Factory<Int, Result>() {

    val moviesLiveDataSource =  MutableLiveData<TvShowDataSource>()

    override fun create(): DataSource<Int, Result> {
        val trendingMoviesDataSource =
            TvShowDataSource(
                compositeDisposable,
                tvShowRepositoryI,
                type
            )
        moviesLiveDataSource.postValue(trendingMoviesDataSource)
        return trendingMoviesDataSource
    }
}