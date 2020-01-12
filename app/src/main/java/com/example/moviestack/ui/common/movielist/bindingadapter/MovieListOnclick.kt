package com.example.moviestack.ui.dashboard.home.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.ui.moviedetail.MovieDetailActivity
import com.squareup.picasso.Picasso

object MovieListOnclick {
    @JvmStatic
    @BindingAdapter("setMovieListOnclick")
    fun setMovieListOnclick (layout : ConstraintLayout, result : Result?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieDetailActivity::class.java)
            intent.putExtra("id", "${result?.id}")
            intent.putExtra("title", if(result?.name.equals("")) result?.originalTitle else  result?.name)
            layout.context.startActivity(intent)
        }
    }
}