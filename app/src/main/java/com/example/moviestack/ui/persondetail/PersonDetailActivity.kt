package com.example.moviestack.ui.persondetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.animate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviestack.R
import com.example.moviestack.api.DataHelper
import com.example.moviestack.api.repo.person.PersonRepositoryI
import com.example.moviestack.databinding.MovieDetailActivityBinding
import com.example.moviestack.databinding.PersonDetailActivityBinding
import com.example.moviestack.ui.moviedetail.MovieDetailViewModel
import com.example.moviestack.ui.persondetail.adapter.PersionViewPagerAdapter
import com.example.moviestack.utils.createFactory
import com.example.qrcode.ui.adapter.MainViewPagerAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_person_detail.*
import kotlinx.android.synthetic.main.activity_person_detail.collapsing_toolbar
import kotlinx.android.synthetic.main.activity_person_detail.tabLayout
import kotlinx.android.synthetic.main.activity_person_detail.viewPager


class PersonDetailActivity : AppCompatActivity() {


    private lateinit var  id : String
    private lateinit var  title : String
    private lateinit var  image : String
    lateinit var mainBinding: PersonDetailActivityBinding
    private lateinit var personDetailViewModel: PersonDetailViewModel
    private lateinit var personRepositoryI: PersonRepositoryI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_person_detail)

        init()
        loadData()
        setObserver()
        setUpViewPager()
        setUpAppBar()

        imageView4.isSelected = false


    }



    private fun loadData() {
        personDetailViewModel.loadData(id)
    }

    fun init() {
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        image = intent.getStringExtra("image")

        mainBinding.title = title
        mainBinding.imageurl = image

        Log.i("kjcbdsjds","  : ${id}")
        personRepositoryI = DataHelper().personRepositoryI
        val factory = PersonDetailViewModel(personRepositoryI).createFactory()
        personDetailViewModel = ViewModelProvider(this, factory).get(PersonDetailViewModel::class.java)
    }

    private fun setObserver() {
        personDetailViewModel.mutableLiveData.observe(this, Observer {
           mainBinding.taggedImages = it.taggedImages
            mainBinding. externalIds = it.externalIds
            Log.i("kjcbdsjds","  : ${it.taggedImages}")
        })
    }

    private fun setUpAppBar() {
        (findViewById(R.id.app_bar_layout) as AppBarLayout).addOnOffsetChangedListener(
            object : AppBarLayout.OnOffsetChangedListener {
                override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                    val min_height: Int = ViewCompat.getMinimumHeight(collapsing_toolbar) * 2
                    val scale = (min_height + verticalOffset).toFloat() / min_height

                    layout.setScaleX(if (scale >= 0) scale else 0f)
                    layout.setScaleY(if (scale >= 0) scale else 0f)
                    layout.setPivotY(200f)

                    when (Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())) {
                        in 0.70F..1F -> {
                            title_1.apply {
                                if (visibility != View.VISIBLE)
                                    visibility = View.VISIBLE

                                alpha = (Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat()))
                                //animate().setDuration(1000).alpha(Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())/0.5f)
                            }
                        }

                        in 0F..0.70F -> {
                            title_1.apply {
                                if (visibility == View.VISIBLE)
                                    visibility = View.GONE
                                animate().setDuration(0).alpha(Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())/0.5f)
                            }
                        }

                    }

                }
            }
        )
    }

    private fun setUpViewPager() {
        Log.i("dsjcbsdjbc","PersionViewPagerAdapter 0000 ")
        viewPager.adapter = PersionViewPagerAdapter(supportFragmentManager,id)
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)
    }

}
