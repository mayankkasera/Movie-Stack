package com.example.moviestack.ui.moviedetail.adapter

import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder




class MainSliderAdapter(private val list: List<String>) : SliderAdapter() {

    override fun getItemCount(): Int {
       return if(list.size>3) 3 else list.size
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        when (position) {
            0 -> viewHolder.bindImageSlide(list.get(0))
            1 -> viewHolder.bindImageSlide(list.get(1))
            2 -> viewHolder.bindImageSlide(list.get(2))
        }
    }
}