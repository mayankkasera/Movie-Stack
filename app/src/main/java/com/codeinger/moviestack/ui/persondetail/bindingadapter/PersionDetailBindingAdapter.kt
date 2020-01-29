package com.codeinger.moviestack.ui.persondetail.bindingadapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.codeinger.moviestack.R
import com.codeinger.moviestack.pojo.TaggedImages
import com.codeinger.moviestack.utils.adapter.MainSliderAdapter
import com.codeinger.moviestack.api.NetworkConstants
import com.codeinger.moviestack.utils.PicassoImageLoadingService
import ss.com.bannerslider.Slider


object PersionDetailBindingAdapter {
    @JvmStatic
    @BindingAdapter("setPersionDetailSlider")
    fun setPersionDetailSlider(slider: Slider, result: TaggedImages?) {
        Slider.init(PicassoImageLoadingService());
        if (result != null) {

            val arrayList = ArrayList<String>()
            if (result.results.size > 0)
                arrayList.add(NetworkConstants.baseImageOriginalUrl + result.results.get(0).media.backdropPath)

            if (result.results.size > 1)
                arrayList.add(NetworkConstants.baseImageOriginalUrl + result.results.get(1).media.backdropPath)

            if (result.results.size > 2)
                arrayList.add(NetworkConstants.baseImageOriginalUrl + result.results.get(3).media.backdropPath)

            Log.i("dsgcvh", arrayList.toString())
            Log.i("dsgcvh", result.toString())


            slider.setAdapter(
                MainSliderAdapter(
                    arrayList
                )
            )
            slider.setInterval(10000)
        } else {
            Log.i("sddhvgch", "sdcvhsd")
        }

    }

    @JvmStatic
    @BindingAdapter("setImageState")
    fun setImageState(imageView: AppCompatImageView, state: Boolean?) {
        if (state!!)
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.white));
        else
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.light_gray));

    }

    @JvmStatic
    @BindingAdapter("setImdbOnClick")
    fun setImdbOnClick(imageView: AppCompatImageView, id: String?) {
        if (id != null)
            imageView.setOnClickListener {
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.imdb.com/name/$id/?ref_=tt_ov_wr")
                )
                try {
                    imageView.context.startActivity(webIntent)
                } catch (ex: ActivityNotFoundException) {
                    imageView.context.startActivity(webIntent)
                }
            }

    }


    @JvmStatic
    @BindingAdapter("setFbOnClick")
    fun setFbOnClick(imageView: AppCompatImageView, id: String?) {
        if (id != null)
            imageView.setOnClickListener {
                imageView.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/$id")
                    )
                )
            }

    }

    @JvmStatic
    @BindingAdapter("setInstaOnClick")
    fun setInstaOnClick(imageView: AppCompatImageView, id: String?) {
        if (id != null)
            imageView.setOnClickListener {
                imageView.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/$id")
                    )
                )
            }

    }

    @JvmStatic
    @BindingAdapter("setTwitterOnClick")
    fun setTwitterOnClick(imageView: AppCompatImageView, id: String?) {

        if (id != null)
            imageView.setOnClickListener {
                imageView.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/$id")
                    )
                )
            }

    }

}
