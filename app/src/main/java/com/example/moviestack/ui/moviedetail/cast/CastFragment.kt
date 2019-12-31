package com.example.moviestack.ui.moviedetail.cast


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
import com.example.moviestack.api.repo.movieInforepo.MovieRepository
import com.example.moviestack.api.repo.movieInforepo.MovieRepositoryI
import com.example.moviestack.databinding.CastFragmentBinding
import com.example.moviestack.ui.moviedetail.info.InfoViewModel
import com.example.moviestack.utils.createFactory

/**
 * A simple [Fragment] subclass.
 */
class CastFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var castViewModel: CastViewModel
    private lateinit var binding: CastFragmentBinding
    private val movieRepositoryI: MovieRepositoryI = MovieRepository(NetworkHelper().gerMovieRequests())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cast, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
        castViewModel.getCast()
    }


    private fun init() {
        mView = binding.getRoot()
        val factory = CastViewModel(movieRepositoryI).createFactory()
        castViewModel = ViewModelProvider(this, factory).get(CastViewModel::class.java)
    }

    private fun setObserver() {
        castViewModel.mutableLiveData.observe(this, Observer { binding.castState = it
            Log.i("dkjhfckjds","hdfsgd "+it)
        })
    }


}
