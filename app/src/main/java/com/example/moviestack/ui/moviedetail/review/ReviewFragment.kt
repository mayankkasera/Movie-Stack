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
import com.example.moviestack.api.repo.movieInfo.MovieRepository
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.databinding.ReviewtFragmentBinding
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : Fragment() {

    private lateinit var id: String
    private lateinit var mView: View
    private lateinit var reviewViewModel: ReviewViewModel
    private lateinit var binding: ReviewtFragmentBinding
    private lateinit var movieRepositoryI: MovieRepositoryI

    companion object {
        private const val ID = "Id"
        fun newInstance(id : String): ReviewFragment {
            val bundle = Bundle()
            bundle.putSerializable(ID, id)
            val fragment = ReviewFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

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
        id = arguments?.getSerializable(ReviewFragment.ID) as String
        movieRepositoryI = MovieRepository(NetworkHelper().gerMovieRequests(),id)
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
