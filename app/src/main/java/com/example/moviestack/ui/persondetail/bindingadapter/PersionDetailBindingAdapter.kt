package com.example.moviestack.ui.persondetail.bindingadapter

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.moviestack.R
import com.example.moviestack.api.pojo.TaggedImages
import com.example.moviestack.api.pojo.Videos
import com.example.moviestack.ui.moviedetail.adapter.MainSliderAdapter
import com.example.moviestack.utils.NetworkConstants
import com.example.moviestack.utils.PicassoImageLoadingService
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ss.com.bannerslider.Slider

object PersionDetailBindingAdapter {
    @JvmStatic
    @BindingAdapter("setPersionDetailSlider")
    fun setPersionDetailSlider (slider : Slider, result : TaggedImages?){
        Slider.init(PicassoImageLoadingService());
        if (result != null) {

            val arrayList = ArrayList<String>()
            if(result.results.size>0)
            arrayList.add(NetworkConstants.baseImageOriginalUrl+result.results.get(0).media.backdropPath)

            if(result.results.size>1)
                arrayList.add(NetworkConstants.baseImageOriginalUrl+result.results.get(1).media.backdropPath)

            if(result.results.size>2)
                arrayList.add(NetworkConstants.baseImageOriginalUrl+result.results.get(3).media.backdropPath)

            Log.i("dsgcvh",arrayList.toString())
            Log.i("dsgcvh",result.toString())

            slider.setAdapter(MainSliderAdapter(arrayList))
        }
        else{
            Log.i("sddhvgch","sdcvhsd")
        }

    }

    @JvmStatic
    @BindingAdapter("setImageState")
    fun setImageState (imageView : AppCompatImageView, state : Boolean?){

        Log.i("sdhcghs","$state")

        if(state!!)
          imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.white));
        else
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.light_gray));

    }

}
