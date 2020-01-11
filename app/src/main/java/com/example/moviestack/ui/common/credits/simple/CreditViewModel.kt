package com.example.moviestack.ui.common.credits.simple

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.ui.common.credits.CreditSatate
import com.example.moviestack.ui.common.credits.CreditType
import com.example.moviestack.ui.common.credits.adapter.CastAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CreditViewModel(val movieRepositoryI: MovieRepositoryI) : ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<CreditSatate> = MutableLiveData()
    private var state =
        CreditSatate()
        set(value) {
            field = value
            publishState(value)
        }

    fun getCast(id : String){

        state = state.copy(loading = true)
        compositeDisposable.add(movieRepositoryI.getCredits(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val credits : Credits = it.data as Credits
                Log.i("dchjdbjsd",""+credits.cast.toString())
                state = state.copy(castAdapter = CastAdapter(credits,
                    CreditType.Type.CAST
                ))
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

            }))
    }

    fun getCrew(id : String){

        state = state.copy(loading = true)
        compositeDisposable.add(movieRepositoryI.getCredits(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val credits : Credits = it.data as Credits
                Log.i("dchjdbjsd",""+credits.cast.toString())
                state = state.copy(castAdapter = CastAdapter(credits,
                    CreditType.Type.CREW
                ))
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

            }))
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: CreditSatate) {
        mutableLiveData.postValue(state)
    }
}