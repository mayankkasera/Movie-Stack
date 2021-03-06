package com.codeinger.moviestack.api.repo.person.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.codeinger.moviestack.api.repo.person.PersonRepositoryI
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.ui.common.movielist.MovieListState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PersonDataSource(var compositeDisposable: CompositeDisposable,
                       var personRepositoryI: PersonRepositoryI) : PageKeyedDataSource<Int, Result>() {


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
        compositeDisposable.add( personRepositoryI.getPerson("$FIRST_PAGE")
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
        compositeDisposable.add( personRepositoryI.getPerson("${params.key}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (it.totalPages >= params.key) {
                    callback.onResult(it.results, params.key + 1)
                }
            }, {
                state = state.copy(
//                    loading = false,
//                    failure = true,
//                    message = it.localizedMessage
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
        compositeDisposable.add( personRepositoryI.getPerson("${params.key}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (it != null) {
                    val i: Int = if (params.key > 1) params.key - 1 else 0
                    callback.onResult(it!!.results, i)
                }
            }, {
                state = state.copy(
//                    loading = false,
//                    failure = true,
//                    message = it.localizedMessage
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