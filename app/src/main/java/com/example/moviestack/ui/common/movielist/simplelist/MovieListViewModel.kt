package com.example.moviestack.ui.common.movielist.simplelist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.repo.person.PersonRepositoryI
import com.example.moviestack.ui.common.movielist.MovieListState
import com.example.moviestack.ui.common.movielist.adapter.MovieListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class MovieListViewModel
    : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    var mutableLiveData: MutableLiveData<MovieListState> = MutableLiveData()

    private var state =
        MovieListState()
        set(value) {
            field = value
            publishState(value)
        }


    fun getMovieCredits(id : String,personRepositoryI: PersonRepositoryI){
        state = state.copy(loading = true)
        compositeDisposable.add(
            personRepositoryI.getMovieCredits(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var joined = it.data as ArrayList<Result>
                    Log.i("dsjvhc",joined.toString())
                    state = state.copy(
                        movieListAdapter = MovieListAdapter(joined)
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

                }))
    }

    fun getTvCredits(id : String,personRepositoryI: PersonRepositoryI){
        state = state.copy(loading = true)
        compositeDisposable.add(
            personRepositoryI.getTvCredits(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var joined = it.data as ArrayList<Result>
                    state = state.copy(
                        movieListAdapter = MovieListAdapter(joined)
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

                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: MovieListState) {
        mutableLiveData.postValue(state)
    }
}