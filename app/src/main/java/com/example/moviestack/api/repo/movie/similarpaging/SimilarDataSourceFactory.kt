package com.example.moviestack.api.repo.movie.similarpaging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.pojo.Result
import io.reactivex.disposables.CompositeDisposable

class SimilarDataSourceFactory(var id : String,
                               var compositeDisposable: CompositeDisposable,
                               var movieItemRepositoryI: MovieItemRepositoryI
): DataSource.Factory<Int, Result>()  {

    val moviesLiveDataSource =  MutableLiveData<SimilarDataSource>()

    override fun create(): DataSource<Int, Result> {
        val similarDataSource =
            SimilarDataSource(
                id,
                compositeDisposable,
                movieItemRepositoryI
            )
        moviesLiveDataSource.postValue(similarDataSource)
        return similarDataSource
    }

}