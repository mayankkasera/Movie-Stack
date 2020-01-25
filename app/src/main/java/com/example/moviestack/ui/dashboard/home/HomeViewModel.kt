package com.example.moviestack.ui.dashboard.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.pojo.SmallItemList
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.trending.TrendingRepositoryI
import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter
import com.example.moviestack.ui.moviedetail.DetailData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class HomeViewModel(var movieItemRepositoryI: MovieItemRepositoryI, var trendingRepositoryI: TrendingRepositoryI) : ViewModel() {

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
                Arrays.asList(
                    trendingRepositoryI.getSmallItemsList(SmallItemList.Type.TRENDING_MOVIES),
                    trendingRepositoryI.getSmallItemsList(SmallItemList.Type.TRENDING_TV_SHOW),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.NOW_PLAYING),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.UPCOMING),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.POPULAR_MOVIES),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.TOP_RATED_MOVIES)
                )
            ).subscribe({

                when (it.type) {

                    SmallItemList.Type.TRENDING_MOVIES -> state =
                        state.copy(trendingMovieAdapter = SmallItemAdapter(it.results,DetailData.Type.MOVIE))
                    SmallItemList.Type.TRENDING_TV_SHOW -> state =
                        state.copy(trendingTvShowAdapter = SmallItemAdapter(it.results,DetailData.Type.TV_SHOW))
                    SmallItemList.Type.NOW_PLAYING -> state =
                        state.copy(nowPlayingAdapter = SmallItemAdapter(it.results,DetailData.Type.MOVIE))
                    SmallItemList.Type.UPCOMING -> state =
                        state.copy(upComingAdapter = SmallItemAdapter(it.results,DetailData.Type.MOVIE))
                    SmallItemList.Type.POPULAR_MOVIES -> state =
                        state.copy(popularAdapter = SmallItemAdapter(it.results,DetailData.Type.MOVIE))
                    SmallItemList.Type.TOP_RATED_MOVIES -> state =
                        state.copy(topRatedAdapter = SmallItemAdapter(it.results,DetailData.Type.MOVIE))

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