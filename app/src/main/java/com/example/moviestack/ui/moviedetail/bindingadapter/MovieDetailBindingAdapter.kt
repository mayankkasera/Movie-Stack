package com.example.moviestack.ui.moviedetail.bindingadapter


import android.util.Log
import androidx.databinding.BindingAdapter
import com.example.moviestack.api.pojo.Images
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.pojo.Videos
import com.example.moviestack.ui.moviedetail.adapter.MainSliderAdapter
import com.example.moviestack.utils.PicassoImageLoadingService
import com.google.android.material.appbar.CollapsingToolbarLayout
import ss.com.bannerslider.Slider

object MovieDetailBindingAdapter {
    @JvmStatic
    @BindingAdapter("slider")
    fun setSlider (slider : Slider, result : Videos?){
        Slider.init(PicassoImageLoadingService());
        if (result != null) {
            slider.setAdapter(MainSliderAdapter(result.results))
            Log.i("sddhvgch","sdcvhsd 1111")
        }
        else{
            Log.i("sddhvgch","sdcvhsd")
        }

    }

    @JvmStatic
    @BindingAdapter("collapsingToolbarTitle")
    fun collapsingToolbarTitle (collapsingToolbarLayout: CollapsingToolbarLayout, result : String?){
        collapsingToolbarLayout.title = result
    }
}