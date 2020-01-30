package com.codeinger.moviestack.api.repo.trending.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.codeinger.moviestack.api.repo.trending.TrendingRepositoryI
import com.codeinger.moviestack.pojo.MovieList
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.pojo.SmallItemList
import com.codeinger.moviestack.ui.common.movielist.MovieListState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TrendingItemDataSource(
    var compositeDisposable: CompositeDisposable,
    var trendingRepositoryI: TrendingRepositoryI,
    var type : SmallItemList.Type
) : PageKeyedDataSource<Int, Result>() {

    companion object{
        val FIRST_PAGE = 1
    }
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

        Log.i("jcnjid","loadInitial")

        var observable : Observable<MovieList>

        observable =  trendingRepositoryI.getMoviesList(
            "$FIRST_PAGE",
            type
        )
        state = state.copy(loading = true)
        compositeDisposable.add(

            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i("jcnjid","loadInitial ${FIRST_PAGE + 1}")
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

                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        Log.i("jcnjid","loadAfter")

        var observable : Observable<MovieList>

        observable =  trendingRepositoryI.getMoviesList(
            "${params.key}",
            type
        )


        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    if (it != null && it.totalPages>params.key) {
                        Log.i("jcnjid","loadAfter  ${params.key + 1}")
                        callback.onResult(it.results, params.key + 1)
                    }


                }, {
                    state = state.copy(
//                        loading = false,
//                        failure = true,
//                        message = it.localizedMessage
                    )
                }, {
                    state = state.copy(
                        loading = false,
                        success = true
                    )
                }, {

                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        Log.i("jcnjid","loadBefore")

        var observable : Observable<MovieList>

        observable =  trendingRepositoryI.getMoviesList(
            "${params.key}",
            type
        )


        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    if (it != null) {
                        var i: Int = if (params.key > 1) params.key - 1 else 0
                        Log.i("jcnjid","loadAfter  ${i}")
                        callback.onResult(it!!.results, i)
                    }
                }, {
                    state = state.copy(
//                        loading = false,
//                        failure = true,
//                        message = it.localizedMessage
                    )
                }, {
                    state = state.copy(
                        loading = false,
                        success = true
                    )
                }, {

                })
        )

    }

    private fun publishState(state: MovieListState) {
        mutableLiveData.postValue(state)
    }


}