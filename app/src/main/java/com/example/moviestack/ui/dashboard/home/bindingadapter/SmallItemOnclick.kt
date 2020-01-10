package com.example.moviestack.ui.dashboard.home.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.api.pojo.*
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.ui.common.movielist.MovieListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListActivity
import com.example.moviestack.ui.moviedetail.MovieDetailActivity
import com.squareup.picasso.Picasso

object SmallItemOnclick {
    @JvmStatic
    @BindingAdapter("setSmallItemOnclick")
    fun setSmallItemOnclick (layout : ConstraintLayout, result : Result?){

        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieDetailActivity::class.java)
            intent.putExtra("id", "${result?.id}")
            intent.putExtra("title", if(result?.title.equals(""))result?.originalTitle else  result?.title)
            layout.context.startActivity(intent)
        }

    }

    @JvmStatic
    @BindingAdapter("setMoreOnclick")
    fun setMoreOnclick (layout : AppCompatTextView, type : MovieListType.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListActivity::class.java)
            intent.putExtra("type", type)
            layout.context.startActivity(intent)
        }
    }



}