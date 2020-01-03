package com.example.moviestack.utils.bindingadapter


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

object PicasoAdapter{
    @JvmStatic
    @BindingAdapter("setImageResource")
    fun setImageResource (imageView : AppCompatImageView,url : String?){
        Picasso.get().load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setImageResource")
    fun setImageResource (imageView : CircleImageView,url : String?){
        Picasso.get().load(url).into(imageView)
    }

}