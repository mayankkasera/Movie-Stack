package com.example.moviestack.ui.moviedetail.adapter

import android.util.Log
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.api.pojo.Images
import com.example.moviestack.api.pojo.Videos
import com.example.moviestack.utils.NetworkConstants
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder




class MainSliderAdapter(private val list: List<Videos.Result>) : SliderAdapter() {

    override fun getItemCount(): Int {
       return if(list.size>3) 3 else list.size
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        Log.i("sddhvgch",""+list.get(0).getImage())
        when (position) {
            0 -> viewHolder.bindImageSlide(list.get(0).getImage())
            1 -> viewHolder.bindImageSlide(list.get(1).getImage())
            2 -> viewHolder.bindImageSlide(list.get(2).getImage())
        }
    }
}