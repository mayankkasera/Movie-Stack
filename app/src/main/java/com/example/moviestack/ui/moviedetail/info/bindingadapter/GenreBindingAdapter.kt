package com.example.moviestack.ui.moviedetail.info.bindingadapter

import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.ui.common.movielist.MovieListActivity

object GenreBindingAdapter {
    @JvmStatic
    @BindingAdapter("setGenreOnclick")
    fun setGenreOnclick (layout : ConstraintLayout, id : Int?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListActivity::class.java)
            intent.putExtra("id", id)
            layout.context.startActivity(intent)
        }
    }
}