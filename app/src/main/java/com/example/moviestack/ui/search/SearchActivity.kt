package com.example.moviestack.ui.search

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestack.R
import com.example.moviestack.ui.search.adapter.SerchViewPagerAdapter
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

        edit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Your piece of code on keyboard search click
                viewPager.adapter = SerchViewPagerAdapter(supportFragmentManager,edit.text.toString())
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0)
                true
            } else false
        }

        search.setOnClickListener{
            viewPager.adapter = SerchViewPagerAdapter(supportFragmentManager,edit.text.toString())
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(search.getWindowToken(), 0)
        }


    }
}
