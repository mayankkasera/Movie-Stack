package com.codeinger.moviestack.ui.boookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeinger.moviestack.R
import com.codeinger.moviestack.ui.boookmark.adapter.BookmarkPagerAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*

class BookamrkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookamrk)


        viewPager.adapter = BookmarkPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)

    }
}
