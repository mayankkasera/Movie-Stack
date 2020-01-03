package com.example.moviestack.ui.dashboard.home.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.ui.moviedetail.MovieDetailActivity
import com.squareup.picasso.Picasso

object SmallItemOnclick {
    @JvmStatic
    @BindingAdapter("setOnclick")
    fun setOnclick (layout : ConstraintLayout, result : SmallItemList.Result?){

        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieDetailActivity::class.java)
            intent.putExtra("SmallItem", result)
            layout.context.startActivity(intent)
        }


    }
}