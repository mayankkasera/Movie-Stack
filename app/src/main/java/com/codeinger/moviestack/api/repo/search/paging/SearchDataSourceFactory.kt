package com.codeinger.moviestack.api.repo.search.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.pojo.SmallItemList
import com.codeinger.moviestack.api.repo.search.SearchRepositoryI
import io.reactivex.disposables.CompositeDisposable

class SearchDataSourceFactory(
    var query :String,
    var compositeDisposable: CompositeDisposable,
    var searchRepositoryI: SearchRepositoryI,
    var type : SmallItemList.Type
): DataSource.Factory<Int, Result>()  {

    val moviesLiveDataSource =  MutableLiveData<SearchDataSource>()

    override fun create(): DataSource<Int, Result> {
        val similarDataSource =
            SearchDataSource(
                query,
                compositeDisposable,
                searchRepositoryI,
                type
            )
        moviesLiveDataSource.postValue(similarDataSource)
        return similarDataSource
    }
}