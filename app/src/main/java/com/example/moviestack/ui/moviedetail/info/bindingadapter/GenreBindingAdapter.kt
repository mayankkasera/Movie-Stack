package com.example.moviestack.ui.moviedetail.info.bindingadapter

import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingActivity

object GenreBindingAdapter {
    @JvmStatic
    @BindingAdapter("setGenreOnclick")
    fun setGenreOnclick (layout : ConstraintLayout, id : Int?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListPaggingActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("type",
                ListType.Type.GENRE)
            layout.context.startActivity(intent)
        }
    }
}