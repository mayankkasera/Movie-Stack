package com.example.moviestack.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviestack.R
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.trendingmovieslist.SmallItemRepository
import com.example.moviestack.api.repo.trendingmovieslist.SmallItemRepositoryI
import com.example.moviestack.databinding.HomeFragmentBinding
import com.example.moviestack.utils.createFactory

class HomeFragment : Fragment() {

    private lateinit var mView: View
    private val trendingMoviesRepositoryI: SmallItemRepositoryI = SmallItemRepository(NetworkHelper().gerRetrofit(),SmallItemList.Type.TRENDING_MOVIES)
    private val trendingTvRepositoryI: SmallItemRepositoryI = SmallItemRepository(NetworkHelper().gerRetrofit(),SmallItemList.Type.TRENDING_TV_SHOW)
    private val nowPlayingRepositoryI: SmallItemRepositoryI = SmallItemRepository(NetworkHelper().gerRetrofit(),SmallItemList.Type.NOW_PLAYING)
    private val upcomingRepositoryI: SmallItemRepositoryI = SmallItemRepository(NetworkHelper().gerRetrofit(),SmallItemList.Type.UPCOMING)
    private val popularRepositoryI: SmallItemRepositoryI = SmallItemRepository(NetworkHelper().gerRetrofit(),SmallItemList.Type.POPULAR)
    private val topRatedRepositoryI: SmallItemRepositoryI = SmallItemRepository(NetworkHelper().gerRetrofit(),SmallItemList.Type.TOP_RATED)
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
        val factory = HomeViewModel(trendingMoviesRepositoryI,trendingTvRepositoryI,nowPlayingRepositoryI,upcomingRepositoryI,popularRepositoryI,topRatedRepositoryI).createFactory()
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    private fun setObserver() {
        viewModel.mutableLiveData.observe(this, Observer { binding.homeState = it })
    }




}
