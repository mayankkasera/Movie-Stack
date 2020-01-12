package com.example.moviestack.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviestack.R
import com.example.moviestack.ui.persondetail.adapter.PersionViewPagerAdapter
import com.example.moviestack.ui.search.adapter.SerchViewPagerAdapter
import kotlinx.android.synthetic.main.activity_person_detail.*
import kotlinx.android.synthetic.main.activity_person_detail.tabLayout
import kotlinx.android.synthetic.main.activity_person_detail.viewPager
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewPager.offscreenPageLimit = 2
        viewPager.adapter = SerchViewPagerAdapter(supportFragmentManager,edit.text.toString())
        tabLayout.setupWithViewPager(viewPager)

        search.setOnClickListener{
            viewPager.adapter = SerchViewPagerAdapter(supportFragmentManager,edit.text.toString())
            tabLayout.setupWithViewPager(viewPager)
        }


    }
}
