package com.example.moviestack.roomdb.movieInfo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.pojo.Result
import com.example.moviestack.api.repo.person.PersonRepositoryI
import com.example.moviestack.pojo.MyListDetail
import com.example.moviestack.roomdb.bookmark.BookmarkHelperI
import com.example.moviestack.roomdb.mylistdetail.MyListDetailHelperI
import com.example.moviestack.ui.common.movielist.MovieListState
import com.example.moviestack.ui.common.movielist.adapter.MovieListAdapter
import com.example.moviestack.ui.moviedetail.DetailData

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


    fun getMovieCredits(id : String,personRepositoryI: PersonRepositoryI,detailDataType : DetailData.Type){
        Log.i("dshcgjdsbc","sdhkjcn 1")
        state = state.copy(loading = true)
        compositeDisposable.add(
            personRepositoryI.getMovieCredits(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var joined = it.data as ArrayList<Result>
                    Log.i("dshcgjdsbc",joined.toString())
                    state = state.copy(
                        movieListAdapter = MovieListAdapter(joined,detailDataType)
                    )
                }, {
                    Log.i("dshcgjdsbc","2 "+it.toString())
                    state = state.copy(
                        loading = false,
                        failure = true,
                        message = it.localizedMessage
                    )
                }, {
                    Log.i("dshcgjdsbc","3 ")
                    state = state.copy(
                        loading = false,
                        success = true
                    )
                }, {

                }))
    }

    fun getTvCredits(id : String,personRepositoryI: PersonRepositoryI,detailDataType : DetailData.Type){
        state = state.copy(loading = true)
        compositeDisposable.add(
            personRepositoryI.getTvCredits(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var joined = it.data as ArrayList<Result>
                    state = state.copy(
                        movieListAdapter = MovieListAdapter(joined,detailDataType)
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

    fun getResult(bookmarkHelperI: BookmarkHelperI,type : DetailData.Type){
        state = state.copy(
            movieListAdapter = MovieListAdapter(bookmarkHelperI.getAllBookmarkMovieInfo(type),type)
        )
    }

    fun getMyList(myListDetailHelper: MyListDetailHelperI,type : MyListDetail.Type,myListId : Int,detailDataType : DetailData.Type){
        state = state.copy(
            movieListAdapter = MovieListAdapter(myListDetailHelper.getAllMyListDetail(myListId,type),detailDataType)
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