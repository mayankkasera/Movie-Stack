package com.example.moviestack.ui.moviedetail.review


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.moviestack.R
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.api.pojo.Review
import com.example.moviestack.api.repo.movieInforepo.MovieRepository
import com.example.moviestack.api.repo.movieInforepo.MovieRepositoryI
import com.example.moviestack.databinding.CastFragmentBinding
import com.example.moviestack.databinding.ReviewtFragmentBinding
import com.example.moviestack.ui.moviedetail.cast.CastViewModel
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var reviewViewModel: ReviewViewModel
    private lateinit var binding: ReviewtFragmentBinding
    private val movieRepositoryI: MovieRepositoryI = MovieRepository(NetworkHelper().gerMovieRequests())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
        reviewViewModel.getReview()
    }


    private fun init() {
        mView = binding.getRoot()
        val factory = ReviewViewModel(movieRepositoryI).createFactory()
        reviewViewModel = ViewModelProvider(this, factory).get(ReviewViewModel::class.java)
    }

    private fun setObserver() {
        reviewViewModel.mutableLiveData.observe(this, Observer { binding.reviewState = it
            Log.i("dkjhfckjds","hdfsgd "+it)
        })
    }



}
