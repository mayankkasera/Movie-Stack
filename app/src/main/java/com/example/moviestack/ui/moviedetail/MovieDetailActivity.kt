package com.example.moviestack.ui.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviestack.R
import com.example.moviestack.api.DataHelper
import com.example.moviestack.pojo.SmallItemList
import com.example.moviestack.databinding.MovieDetailActivityBinding
import com.example.moviestack.utils.createFactory
import com.example.qrcode.ui.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var id : String
    private lateinit var title : String
    lateinit var mainBinding: MovieDetailActivityBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        init()
        loadData()
        setObserver()
        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager, "${id}")
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)


    }

    private fun loadData() {
        movieDetailViewModel.getMedia(id)
    }

    fun init() {
        title = intent.getStringExtra("title")
        id = intent.getStringExtra("id")
        val factory = MovieDetailViewModel(DataHelper().movieItemRepositoryI).createFactory()
        movieDetailViewModel =
            ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)
    }

    private fun setObserver() {
        movieDetailViewModel.mutableLiveData.observe(this, Observer {
            mainBinding.videos = it.videos
            mainBinding.title = title
        })
    }

}
