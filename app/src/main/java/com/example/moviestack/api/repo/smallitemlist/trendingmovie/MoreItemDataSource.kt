package com.example.moviestack.api.repo.smallitemlist.trendingmovie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.smallitemlist.SmallItemRepositoryI
import com.example.moviestack.ui.common.movielist.MovieListState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoreItemDataSource(
    var compositeDisposable: CompositeDisposable,
    var smallItemRepositoryI: SmallItemRepositoryI,
    var type : SmallItemList.Type
) : PageKeyedDataSource<Int, Result>() {

    val FIRST_PAGE = 1
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

        var observable : Observable<MovieList>

        observable =   when(type){
            SmallItemList.Type.TRENDING_MOVIES -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TRENDING_MOVIES
            )

            SmallItemList.Type.TRENDING_TV_SHOW -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TRENDING_TV_SHOW
            )

            SmallItemList.Type.NOW_PLAYING -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.NOW_PLAYING
            )

            SmallItemList.Type.UPCOMING -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.UPCOMING
            )

            SmallItemList.Type.POPULAR -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.POPULAR
            )

            SmallItemList.Type.TOP_RATED -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TOP_RATED
            )

            else -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TRENDING_MOVIES
            )


        }

        compositeDisposable.add(

            observable
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

                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        var observable : Observable<MovieList>

        observable =   when(type){
            SmallItemList.Type.TRENDING_MOVIES -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TRENDING_MOVIES
            )

            else -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TRENDING_MOVIES
            )


        }

        compositeDisposable.add(
                observable
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

                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        var observable : Observable<MovieList>

        observable =   when(type){
            SmallItemList.Type.TRENDING_MOVIES -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TRENDING_MOVIES
            )

            else -> smallItemRepositoryI.getMoviesList(
                "$FIRST_PAGE",
                SmallItemList.Type.TRENDING_MOVIES
            )


        }

        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        var i: Int = if (params.key > 1) params.key - 1 else 0
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

                })
        )

    }

    private fun publishState(state: MovieListState) {
        mutableLiveData.postValue(state)
    }


}