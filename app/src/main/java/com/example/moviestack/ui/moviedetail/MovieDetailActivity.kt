package com.example.moviestack.ui.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.moviestack.R
import com.example.qrcode.ui.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import ss.com.bannerslider.Slider


class MovieDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        slider()

        viewPager.setOnPageChangeListener(object :  ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                when (position) {

                }
            }
        })
        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)


    }

    private fun slider() {
        Slider.init(PicassoImageLoadingService());
        slider.setAdapter(MainSliderAdapter())
    }
}
