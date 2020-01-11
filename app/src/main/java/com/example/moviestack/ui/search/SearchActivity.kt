package com.example.moviestack.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestack.R
import com.example.moviestack.ui.persondetail.adapter.PersionViewPagerAdapter
import com.example.moviestack.ui.search.adapter.SerchViewPagerAdapter
import kotlinx.android.synthetic.main.activity_person_detail.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewPager.adapter = SerchViewPagerAdapter(supportFragmentManager,"")
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)


    }
}
