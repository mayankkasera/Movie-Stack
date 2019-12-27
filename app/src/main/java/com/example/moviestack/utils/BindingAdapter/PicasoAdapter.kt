package com.example.moviestack.utils.BindingAdapter

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object PicasoAdapter{
    @JvmStatic
    @BindingAdapter("setImageResource")
    fun setImageResource (imageView : AppCompatImageView,url : String){
        Picasso.get().load(url).into(imageView)
    }

}