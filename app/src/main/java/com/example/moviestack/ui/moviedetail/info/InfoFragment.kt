package com.example.moviestack.ui.moviedetail.info

import android.content.Context
import android.net.Uri
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
import com.example.moviestack.databinding.HomeFragmentBinding
import com.example.moviestack.databinding.InfoFragmentBinding
import com.example.moviestack.ui.dashboard.home.HomeViewModel
import com.example.moviestack.utils.createFactory


class InfoFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var infoViewModel: InfoViewModel
    private lateinit var binding: InfoFragmentBinding
    private val movieRepositoryI: MovieRepositoryI = MovieRepository(NetworkHelper().gerMovieRequests())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        init()
        loadData()
        setObserver()
        return  mView
    }

    private fun loadData() {
        infoViewModel.getMovieInfo()
    }

    private fun init() {
        mView = binding.getRoot()
        val factory = InfoViewModel(movieRepositoryI).createFactory()
        infoViewModel = ViewModelProvider(this, factory).get(InfoViewModel::class.java)
    }

    private fun setObserver() {
        infoViewModel.mutableLiveData.observe(this, Observer { binding.infoState = it
           Log.i("dkjhfckjds","hdfsgd "+it)
        })
    }


}
