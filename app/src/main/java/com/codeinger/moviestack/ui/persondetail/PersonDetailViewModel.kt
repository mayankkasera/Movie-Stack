package com.codeinger.moviestack.ui.persondetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codeinger.moviestack.pojo.ExternalIds
import com.codeinger.moviestack.pojo.TaggedImages
import com.codeinger.moviestack.api.repo.person.PersonRepositoryI
import com.codeinger.moviestack.api.repo.person.PersonResponce
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class PersonDetailViewModel(val personRepositoryI: PersonRepositoryI) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<PersonDetailState> = MutableLiveData()
    private var state = PersonDetailState()
        set(value) {
            field = value
            publishState(value)
        }


    fun loadData(id: String) {
        state = state.copy(loading = true)

        compositeDisposable.add(
            Observable.merge(Arrays.asList(
                personRepositoryI.getExternalIds(id),
                personRepositoryI.getTaggedImages(id)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it.type) {
                        PersonResponce.Type.EXTERNAL_IDS -> {
                            val externalIds = it.data as ExternalIds
                            state = state.copy(externalIds = externalIds)
                        }
                        PersonResponce.Type.TAGGED_IMAGES -> {
                            val taggedImages : TaggedImages = it.data as TaggedImages
                            state = state.copy(taggedImages = taggedImages)
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

    private fun publishState(state: PersonDetailState) {
        mutableLiveData.postValue(state)
    }

}