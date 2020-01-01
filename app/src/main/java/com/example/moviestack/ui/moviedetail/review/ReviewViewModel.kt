package com.example.moviestack.ui.moviedetail.review

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.api.pojo.Review
import com.example.moviestack.api.repo.movieInforepo.MovieRepositoryI
import com.example.moviestack.ui.moviedetail.cast.CastSatate
import com.example.moviestack.ui.moviedetail.cast.adapter.CastAdapter
import com.example.moviestack.ui.moviedetail.review.adapter.ReviewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReviewViewModel(val movieRepositoryI: MovieRepositoryI) : ViewModel()  {
    private var compositeDisposable = CompositeDisposable()
    var mutableLiveData: MutableLiveData<ReviewState> = MutableLiveData()
    private var state = ReviewState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getReview(){
        movieRepositoryI.getReviews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var review : Review = it.data as Review
                state = state.copy(reviewAdapter = ReviewAdapter(review.results))
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

    private fun publishState(state: ReviewState) {
        mutableLiveData.postValue(state)
    }

}