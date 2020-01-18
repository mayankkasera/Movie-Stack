package com.example.moviestack.ui.dashboard.home.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.MovieDetailActivity
import com.squareup.picasso.Picasso

object MovieListOnclick {
    @JvmStatic
    @BindingAdapter("setMovieListOnclick","movieListOnclickType")
    fun setMovieListOnclick (layout : ConstraintLayout, result : Result?,type: DetailData.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieDetailActivity::class.java)

            var data = DetailData(
                "${result?.id}",
                if(result?.name.equals("")) result?.originalTitle!! else  result?.name!!,
                type!!
            )

            intent.putExtra("datasdcds", data)
            intent.putExtra("id", "${result?.id}")
            intent.putExtra("title", if(result?.name.equals("")) result?.originalTitle else  result?.name)
            layout.context.startActivity(intent)
        }
    }
}