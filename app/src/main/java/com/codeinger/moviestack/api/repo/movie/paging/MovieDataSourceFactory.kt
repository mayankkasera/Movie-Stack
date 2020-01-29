package com.codeinger.moviestack.api.repo.movie.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.pojo.SmallItemList
import com.codeinger.moviestack.api.repo.movie.MovieItemRepositoryI
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(var compositeDisposable: CompositeDisposable,
                             var movieItemRepositoryI: MovieItemRepositoryI,
                             var type : SmallItemList.Type
): DataSource.Factory<Int, Result>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Result> {
        val trendingMoviesDataSource =
            MovieDataSource(
                compositeDisposable,
                movieItemRepositoryI,
                type
            )
        moviesLiveDataSource.postValue(trendingMoviesDataSource)
        return trendingMoviesDataSource
    }
}