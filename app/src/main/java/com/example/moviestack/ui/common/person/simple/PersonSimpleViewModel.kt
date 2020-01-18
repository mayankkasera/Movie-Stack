package com.example.moviestack.ui.common.person.simple

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.pojo.Credits
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.person.PersonSatate
import com.example.moviestack.ui.common.person.adapter.PersonAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PersonSimpleViewModel() : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<PersonSatate> = MutableLiveData()
    private var state =
        PersonSatate()
        set(value) {
            field = value
            publishState(value)
        }

    fun getMovieCast(id : String,movieItemRepositoryI: MovieItemRepositoryI){

        state = state.copy(loading = true)
        compositeDisposable.add(movieItemRepositoryI.getCredits(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val credits : Credits = it.data as Credits
                Log.i("dchjdbjsd",""+credits.cast.toString())
                state = state.copy(personAdapter = PersonAdapter(credits,
                    ListType.Type.CAST
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

    fun getMovieCrew(id : String,movieItemRepositoryI: MovieItemRepositoryI){

        state = state.copy(loading = true)
        compositeDisposable.add(movieItemRepositoryI.getCredits(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val credits : Credits = it.data as Credits
                Log.i("dchjdbjsd",""+credits.crew.toString())
                state = state.copy(personAdapter = PersonAdapter(credits,
                    ListType.Type.CREW
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

    fun getTvCast(id : String,tvShowRepositoryI: TvShowRepositoryI){

        state = state.copy(loading = true)
        compositeDisposable.add(tvShowRepositoryI.getCredits(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val credits : Credits = it.data as Credits
                Log.i("dchjdbjsd",""+credits.cast.toString())
                state = state.copy(personAdapter = PersonAdapter(credits,
                    ListType.Type.CAST
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

    fun getTvCrew(id : String,tvShowRepositoryI: TvShowRepositoryI){

        state = state.copy(loading = true)
        compositeDisposable.add(tvShowRepositoryI.getCredits(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val credits : Credits = it.data as Credits
                Log.i("dchjdbjsd",""+credits.cast.toString())
                state = state.copy(personAdapter = PersonAdapter(credits,
                    ListType.Type.CREW
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

    private fun publishState(state: PersonSatate) {
        mutableLiveData.postValue(state)
    }
}