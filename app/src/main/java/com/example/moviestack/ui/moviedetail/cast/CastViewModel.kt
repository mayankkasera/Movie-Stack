package com.example.moviestack.ui.moviedetail.cast

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.api.repo.movieInforepo.MovieRepositoryI
import com.example.moviestack.ui.moviedetail.cast.adapter.CastAdapter
import com.example.moviestack.ui.moviedetail.info.InfoState
import com.example.moviestack.ui.moviedetail.info.adapter.CrewAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.GenreAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CastViewModel(val movieRepositoryI: MovieRepositoryI) : ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<CastSatate> = MutableLiveData()
    private var state = CastSatate()
        set(value) {
            field = value
            publishState(value)
        }

    fun getCast(){
        movieRepositoryI.getCredits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var credits : Credits = it.data as Credits
                state = state.copy(castAdapter = CastAdapter(credits.cast))
            },{
                state = state.copy(
                    loading = false,
                    failure = true,
                    message = it.localizedMessage
                )
            },{
                state = state.copy(
                    loading = false,
                    success = true
                )
            },{

            })
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: CastSatate) {
        mutableLiveData.postValue(state)
    }
}