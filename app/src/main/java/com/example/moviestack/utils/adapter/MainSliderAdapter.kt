package com.example.moviestack.utils.adapter

import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder




class MainSliderAdapter(private val list: List<String>) : SliderAdapter() {

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        viewHolder.bindImageSlide(list.get(position))
    }
}