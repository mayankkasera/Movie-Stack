package com.example.moviestack.api.repo.movieInfo.similarpaging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import io.reactivex.disposables.CompositeDisposable

class SimilarDataSourceFactory(var id : String,
                               var compositeDisposable: CompositeDisposable,
                               var movieRepositoryI: MovieRepositoryI
): DataSource.Factory<Int, Result>()  {

    val moviesLiveDataSource =  MutableLiveData<SimilarDataSource>()

    override fun create(): DataSource<Int, Result> {
        val similarDataSource =
            SimilarDataSource(
                id,
                compositeDisposable,
                movieRepositoryI
            )
        moviesLiveDataSource.postValue(similarDataSource)
        return similarDataSource
    }

}