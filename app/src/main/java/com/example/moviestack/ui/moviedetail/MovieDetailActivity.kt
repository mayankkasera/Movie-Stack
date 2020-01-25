package com.example.moviestack.ui.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviestack.R
import com.example.moviestack.api.DataHelper
import com.example.moviestack.databinding.MovieDetailActivityBinding
import com.example.moviestack.utils.createFactory
import com.example.qrcode.ui.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var id : String
    private lateinit var title : String
    private lateinit var data : DetailData
    lateinit var mainBinding: MovieDetailActivityBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        init()
        loadData()
        setObserver()
        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager, "${id}",data.type)
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)


    }

    private fun loadData() {
        when(data.type){
           DetailData.Type.MOVIE -> movieDetailViewModel.getMovieMedia(id,DataHelper().movieItemRepositoryI)
            DetailData.Type.TV_SHOW -> movieDetailViewModel.getTvShowMedia(id,DataHelper().tvShowRepositoryI)
        }

    }

    fun init() {

        Log.i("dsbcjd","sdcs :${intent.getParcelableExtra<DetailData>("datasdcds")}")

        data = intent.getParcelableExtra<DetailData>("datasdcds")
        title = intent.getStringExtra("title")
        id = intent.getStringExtra("id")
        val factory = MovieDetailViewModel().createFactory()
        movieDetailViewModel = ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)
    }

    private fun setObserver() {
        movieDetailViewModel.mutableLiveData.observe(this, Observer {
            mainBinding.videos = it.videos
            mainBinding.title = title
        })
    }

}
