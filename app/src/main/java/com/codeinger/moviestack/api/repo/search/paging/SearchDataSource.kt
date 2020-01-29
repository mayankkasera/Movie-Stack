package com.codeinger.moviestack.api.repo.search.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.codeinger.moviestack.api.repo.search.SearchRepositoryI
import io.reactivex.disposables.CompositeDisposable
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.pojo.SmallItemList
import com.codeinger.moviestack.ui.common.movielist.MovieListState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchDataSource(
    var query :String,
    var compositeDisposable: CompositeDisposable,
    var searchRepositoryI: SearchRepositoryI,
    var type : SmallItemList.Type) : PageKeyedDataSource<Int, Result>(){

    private val FIRST_PAGE = 1
    val mutableLiveData: MutableLiveData<MovieListState> = MutableLiveData()
    private var state = MovieListState()
        set(value) {
            field = value
            publishState(value)
        }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        state = state.copy(loading = true)
        compositeDisposable.add( searchRepositoryI.getMovie("$FIRST_PAGE",query,type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it.results, null, FIRST_PAGE + 1)
            }, {
                state = state.copy(
                    loading = false,
                    failure = true,
                    message = it.localizedMessage
                )
            }, {
                state = state.copy(
                    loading = false,
                    success = true
                )
            }, {

            }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        compositeDisposable.add( searchRepositoryI.getMovie("${params.key}",query,type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (it.totalPages >= params.key) {
                    callback.onResult(it.results, params.key + 1)
                }
            }, {
                state = state.copy(
                    loading = false,
                    failure = true,
                    message = it.localizedMessage
                )
            }, {
                state = state.copy(
                    loading = false,
                    success = true
                )
            }, {
            }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        compositeDisposable.add( searchRepositoryI.getMovie("${params.key}",query,type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (it != null) {
                    val i: Int = if (params.key > 1) params.key - 1 else 0
                    callback.onResult(it!!.results, i)
                }
            }, {
                state = state.copy(
                    loading = false,
                    failure = true,
                    message = it.localizedMessage
                )
            }, {
                state = state.copy(
                    loading = false,
                    success = true
                )
            }, {

            }))
    }

    private fun publishState(state: MovieListState) {
        mutableLiveData.postValue(state)
    }
}