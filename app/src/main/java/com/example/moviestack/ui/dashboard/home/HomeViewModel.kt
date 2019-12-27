package com.example.moviestack.ui.dashboard.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.repo.trendingmovieslist.TrendingMoviesRepositoryI
import com.example.moviestack.ui.dashboard.home.adapter.TrendingMovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(var trendingMoviesRepositoryI : TrendingMoviesRepositoryI) : ViewModel() {

    var mutableLiveData : MutableLiveData<HomeState> = MutableLiveData()

    private  var compositeDisposable = CompositeDisposable()

    private var state = HomeState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getTrendingMovies(){
        state = state.copy(loading = true)

        compositeDisposable.add(trendingMoviesRepositoryI.getTrendingMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("sdjdcgv","askdjcna : "+it.toString() )
                state = state.copy(
                    loading = false,
                    success = true,
                    trendingMovieAdapter = TrendingMovieAdapter(it.results)
                )
            }
                ,{
                    state = state.copy(
                        loading = false,
                        failure = true,
                        message = it.localizedMessage
                    )

                }))

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: HomeState) {
        mutableLiveData.postValue(state)
    }






}