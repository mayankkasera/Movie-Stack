package com.example.moviestack.ui.moviedetail.review

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.pojo.Review
import com.example.moviestack.ui.moviedetail.review.adapter.ReviewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReviewViewModel() : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    var mutableLiveData: MutableLiveData<ReviewState> = MutableLiveData()

    private var state = ReviewState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getReview(id: String,movieItemRepositoryI: MovieItemRepositoryI) {
        state = state.copy(loading = true)
        compositeDisposable.add(
            movieItemRepositoryI.getReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val review: Review = it.data as Review
                    state = state.copy(reviewAdapter = ReviewAdapter(review.results))
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

    fun getReview(id: String,tvShowRepositoryI: TvShowRepositoryI) {
        state = state.copy(loading = true)
        compositeDisposable.add(
            tvShowRepositoryI.getReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val review: Review = it.data as Review
                    state = state.copy(reviewAdapter = ReviewAdapter(review.results))
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

    private fun publishState(state: ReviewState) {
        mutableLiveData.postValue(state)
    }

}