package com.example.moviestack.ui.persondetail.info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.*
import com.example.moviestack.api.repo.movieInfo.MovieResponse
import com.example.moviestack.api.repo.person.PersonRepositoryI
import com.example.moviestack.api.repo.person.PersonResponce
import com.example.moviestack.ui.moviedetail.info.adapter.CrewAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.GenreAdapter
import com.example.moviestack.ui.moviedetail.info.adapter.VideosAdapter
import com.example.moviestack.ui.persondetail.info.adapter.PersonImageAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class PersonInfoViewModel(val personRepositoryI: PersonRepositoryI) : ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<PersonInfoState> = MutableLiveData()
    private var state = PersonInfoState()
        set(value) {
            field = value
            publishState(value)
        }

    fun loadData(id:String){
        state = state.copy(loading = true)

        compositeDisposable.add(
            Observable.merge(
                Arrays.asList(
                    personRepositoryI.getPersonInfo(id),
                    personRepositoryI.getImages(id)
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it.type) {
                        PersonResponce.Type.PERSON_INFO -> {
                            var personInfo: PersonInfo = it.data as PersonInfo
                            state = state.copy(
                                personInfo = personInfo
                            )
                        }
                        PersonResponce.Type.PERSON_IMAGES -> {
                            var personImages: PersonImages = it.data as PersonImages
                            var v = PersonImageAdapter(personImages.profiles)
                            Log.i("bvhvhg","${personImages}")
                            state = state.copy(
                                personImageAdapter = PersonImageAdapter(personImages.profiles)
                            )
                        }


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

    private fun publishState(state: PersonInfoState) {
        mutableLiveData.postValue(state)
    }
}