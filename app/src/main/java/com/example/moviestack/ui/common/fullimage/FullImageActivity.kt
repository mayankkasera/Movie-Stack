package com.example.moviestack.ui.common.fullimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler
import com.example.moviestack.R
import com.example.moviestack.pojo.ImagesDeatail
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.fullimage.adapter.FullImageViewPager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_image.*


class FullImageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)

        val list : List<ImagesDeatail>? = intent.getParcelableArrayListExtra("image")
        viewPager.adapter = FullImageViewPager(supportFragmentManager,list!!)
        viewPager.setCurrentItem(intent.getIntExtra("position",0),true)

    }
}
