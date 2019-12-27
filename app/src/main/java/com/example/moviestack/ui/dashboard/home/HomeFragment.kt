package com.example.moviestack.ui.dashboard.home

import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestack.R
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.api.pojo.Trending
import com.example.moviestack.api.repo.trendingmovieslist.TrendingMoviesRepository
import com.example.moviestack.api.repo.trendingmovieslist.TrendingMoviesRepositoryI
import com.example.moviestack.databinding.HomeFragmentBinding
import com.example.moviestack.ui.dashboard.home.adapter.TrendingMovieAdapter
import com.example.moviestack.utils.createFactory
import com.example.moviestack.utils.gone
import com.example.moviestack.utils.visible
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var mView: View
    private val trendingMoviesRepositoryI: TrendingMoviesRepositoryI = TrendingMoviesRepository(NetworkHelper().gerRetrofit())
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        init()
        loadData()
        setObserver()
        return mView
    }

    private fun loadData() {
        viewModel.getTrendingMovies()
    }

    private fun init() {
        mView = binding.getRoot()
        val factory = HomeViewModel(trendingMoviesRepositoryI).createFactory()
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    private fun setObserver() {
        viewModel.mutableLiveData.observe(this, Observer {
            binding.homeState = it
        })
    }




}
