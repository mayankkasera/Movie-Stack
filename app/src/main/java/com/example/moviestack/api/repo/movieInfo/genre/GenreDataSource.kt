package com.example.moviestack.api.repo.movieInfo.genre

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.smallitemlist.trendingmovie.MoreItemDataSource
import com.example.moviestack.ui.common.movielist.MovieListState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GenreDataSource(var data : String,
                      var compositeDisposable: CompositeDisposable,
                      var discoverRepositoryI: DiscoverRepositoryI
): PageKeyedDataSource<Int, Result>()  {

    val mutableLiveData: MutableLiveData<MovieListState> = MutableLiveData()
    private var state = MovieListState()
        set(value) {
            field = value
            publishState(value)
        }

        val FIRST_PAGE = 1


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        compositeDisposable.add(discoverRepositoryI?.getGenreMovieList(data,"${FIRST_PAGE}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieList: MovieList = it.data as MovieList
                callback.onResult(movieList.results, null, FIRST_PAGE + 1)
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
        compositeDisposable.add( discoverRepositoryI?.getGenreMovieList(data,"${params.key}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieList: MovieList = it.data as MovieList
                if (movieList.totalPages >= params.key) {
                    callback.onResult(movieList.results, params.key + 1)
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
        compositeDisposable.add( discoverRepositoryI?.getGenreMovieList(data,"${params.key}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieList: MovieList = it.data as MovieList
                if (it != null) {
                    val i: Int = if (params.key > 1) params.key - 1 else 0
                    callback.onResult(movieList!!.results, i)
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