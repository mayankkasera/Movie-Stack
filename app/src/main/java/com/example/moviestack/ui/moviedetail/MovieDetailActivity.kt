package com.example.moviestack.ui.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.moviestack.R
import com.example.moviestack.api.NetworkHelper
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.movieInforepo.MovieRepository
import com.example.moviestack.databinding.InfoFragmentBinding
import com.example.moviestack.databinding.MovieDetailActivityBinding
import com.example.moviestack.ui.moviedetail.info.InfoState
import com.example.moviestack.ui.moviedetail.info.InfoViewModel
import com.example.moviestack.utils.createFactory
import com.example.qrcode.ui.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import ss.com.bannerslider.Slider


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var result : SmallItemList.Result
    lateinit var mainBinding : MovieDetailActivityBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var movieRepositoryI: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        init()
        loadData()
        setObserver()
        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager,"${result?.id}")
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)


    }
    private fun loadData() {
        movieDetailViewModel.getMedia()
    }

    fun init(){
        result = intent.getParcelableExtra("SmallItem")

        movieRepositoryI = MovieRepository(NetworkHelper().gerMovieRequests(),"${result?.id}")
        val factory = MovieDetailViewModel(movieRepositoryI).createFactory()
        movieDetailViewModel = ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)
    }

    private fun setObserver() {
        movieDetailViewModel.mutableLiveData.observe(this, Observer {
            mainBinding.videos = it.videos
            mainBinding.smallItem = result
        })
    }

}
