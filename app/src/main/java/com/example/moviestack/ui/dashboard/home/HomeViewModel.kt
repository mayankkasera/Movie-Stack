package com.example.moviestack.ui.dashboard.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.trendingmovieslist.SmallItemRepositoryI
import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class HomeViewModel(
    var trendingMoviesRepositoryI: SmallItemRepositoryI,
    var trendingTvShowRepositoryI: SmallItemRepositoryI,
    var nowPlayingRepositoryI: SmallItemRepositoryI,
    var upcomingRepositoryI: SmallItemRepositoryI,
    var popularRepositoryI: SmallItemRepositoryI,
    var topRatedRepositoryI: SmallItemRepositoryI
) : ViewModel() {

    var mutableLiveData: MutableLiveData<HomeState> = MutableLiveData()

    private var compositeDisposable = CompositeDisposable()

    private var state = HomeState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getTrendingMovies() {
        state = state.copy(loading = true)

        compositeDisposable.add(
            Observable.merge(
                Arrays.asList( trendingMoviesRepositoryI.getTrendingMovies(),
                    trendingTvShowRepositoryI.getTrendingMovies(),
                    nowPlayingRepositoryI.getTrendingMovies(),
                    upcomingRepositoryI.getTrendingMovies(),
                    popularRepositoryI.getTrendingMovies(),
                    topRatedRepositoryI.getTrendingMovies()
                )
            ).subscribe({


                when (it.type) {


                    SmallItemList.Type.TRENDING_MOVIES -> state =
                        state.copy(trendingMovieAdapter = SmallItemAdapter(it.results))
                    SmallItemList.Type.TRENDING_TV_SHOW -> state =
                        state.copy(trendingTvShowAdapter = SmallItemAdapter(it.results))
                    SmallItemList.Type.NOW_PLAYING -> state =
                        state.copy(nowPlayingAdapter = SmallItemAdapter(it.results))
                    SmallItemList.Type.UPCOMING -> state =
                        state.copy(upComingAdapter = SmallItemAdapter(it.results))
                    SmallItemList.Type.POPULAR -> state =
                        state.copy(popularAdapter = SmallItemAdapter(it.results))
                    SmallItemList.Type.TOP_RATED -> state =
                        state.copy(topRatedAdapter = SmallItemAdapter(it.results))

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

            })

        )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: HomeState) {
        mutableLiveData.postValue(state)
    }


}