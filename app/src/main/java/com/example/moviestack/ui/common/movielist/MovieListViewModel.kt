package com.example.moviestack.ui.common.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.ui.common.movielist.adapter.MovieListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(
    private val discoverRepositoryI: DiscoverRepositoryI,
    private val movieRepositoryI: MovieRepositoryI
    ) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    var mutableLiveData: MutableLiveData<MovieListState> = MutableLiveData()

    private var state = MovieListState()
        set(value) {
            field = value
            publishState(value)
        }


    fun getGenreMovieList(genre: String) {

        state = state.copy(loading = true)
        compositeDisposable.add(
            discoverRepositoryI.getGenreMovieList(genre)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val movieList: MovieList = it.data as MovieList
                    state = state.copy(
                        movieListAdapter = MovieListAdapter(
                            movieList.results
                        )
                    )
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

    fun getMovieList(id : String) {

        state = state.copy(loading = true)
        compositeDisposable.add(
            movieRepositoryI.getSimilars(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val movieList: MovieList = it.data as MovieList
                    state = state.copy(
                        movieListAdapter = MovieListAdapter(
                            movieList.results
                        )
                    )
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: MovieListState) {
        mutableLiveData.postValue(state)
    }
}