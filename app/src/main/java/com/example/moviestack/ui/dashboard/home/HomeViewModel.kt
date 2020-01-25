package com.example.moviestack.ui.dashboard.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.trending.TrendingRepositoryI
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.pojo.SmallItemList
import com.example.moviestack.ui.dashboard.home.adapter.PersonVerticalAdapter
import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter
import com.example.moviestack.ui.moviedetail.DetailData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class HomeViewModel(var movieItemRepositoryI: MovieItemRepositoryI,
                    var trendingRepositoryI: TrendingRepositoryI,
                    var tvShowRepositoryI: TvShowRepositoryI) : ViewModel()  {

    var mutableLiveData: MutableLiveData<HomeState> = MutableLiveData()
    private var compositeDisposable = CompositeDisposable()
    private var state = HomeState()
        set(value) {
            field = value
            publishState(value)
        }


    fun loadData() {
        state = state.copy(loading = true)

        compositeDisposable.add(
            Observable.merge(
                Arrays.asList(
                    trendingRepositoryI.getSmallItemsList(SmallItemList.Type.TRENDING_MOVIES),
                    trendingRepositoryI.getSmallItemsList(SmallItemList.Type.TRENDING_TV_SHOW),
                    trendingRepositoryI.getSmallItemsList(SmallItemList.Type.TRENDING_PERSON),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.NOW_PLAYING),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.UPCOMING),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.POPULAR_MOVIES),
                    movieItemRepositoryI.getSmallItemsList(SmallItemList.Type.TOP_RATED_MOVIES),
                    tvShowRepositoryI.getSmallItemsList(SmallItemList.Type.POPULAR_TV_SHOW),
                    tvShowRepositoryI.getSmallItemsList(SmallItemList.Type.TOP_RATED_TV_SHOW)
                )
            ).subscribe({

                when (it.type) {

                    SmallItemList.Type.TRENDING_MOVIES -> state =
                        state.copy(trendingMovieAdapter = SmallItemAdapter(it.results,
                            DetailData.Type.MOVIE)
                        )
                    SmallItemList.Type.TRENDING_TV_SHOW -> state =
                        state.copy(trendingTvShowAdapter = SmallItemAdapter(it.results,
                            DetailData.Type.TV_SHOW)
                        )
                    SmallItemList.Type.TRENDING_PERSON -> state =
                        state.copy(
                            personVerticalAdapter = PersonVerticalAdapter(
                                it.results
                            )
                        )
                    SmallItemList.Type.NOW_PLAYING -> state =
                        state.copy(nowPlayingAdapter = SmallItemAdapter(it.results,
                            DetailData.Type.MOVIE)
                        )
                    SmallItemList.Type.UPCOMING ->{
                        state = state.copy(upComingList = it.results)
                    }
                    SmallItemList.Type.POPULAR_MOVIES -> state =
                        state.copy(popularMovieAdapter = SmallItemAdapter(it.results,
                            DetailData.Type.MOVIE)
                        )
                    SmallItemList.Type.TOP_RATED_MOVIES -> state =
                        state.copy(topRateMoviedAdapter = SmallItemAdapter(it.results,
                            DetailData.Type.MOVIE)
                        )
                    SmallItemList.Type.POPULAR_TV_SHOW -> state =
                        state.copy(popularTvShowAdapter = SmallItemAdapter(it.results,
                            DetailData.Type.TV_SHOW)
                        )
                    SmallItemList.Type.TOP_RATED_TV_SHOW -> state =
                        state.copy(topRatedTvShowAdapter = SmallItemAdapter(it.results,
                            DetailData.Type.TV_SHOW)
                        )

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