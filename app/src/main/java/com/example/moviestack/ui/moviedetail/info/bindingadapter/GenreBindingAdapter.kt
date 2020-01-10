package com.example.moviestack.ui.moviedetail.info.bindingadapter

import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.ui.common.movielist.MovieListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListActivity

object GenreBindingAdapter {
    @JvmStatic
    @BindingAdapter("setGenreOnclick")
    fun setGenreOnclick (layout : ConstraintLayout, id : Int?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("type",MovieListType.Type.GENRE)
            layout.context.startActivity(intent)
        }
    }
}