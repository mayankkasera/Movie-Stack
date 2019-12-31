package com.example.moviestack.ui.moviedetail

import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder




class MainSliderAdapter : SliderAdapter() {
    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        when (position) {
            0 -> viewHolder.bindImageSlide("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg")
            1 -> viewHolder.bindImageSlide("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg")
            2 -> viewHolder.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png")
        }
    }
}