package com.example.moviestack.ui.dashboard.search.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.moviestack.ui.common.movielist.MovieListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListActivity

object SearchBindingAdapter {

    @JvmStatic
    @BindingAdapter("setSearchOnclick")
    fun setSearchOnclick (layout : CardView, type : MovieListType.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListActivity::class.java)
            intent.putExtra("type", type)
            layout.context.startActivity(intent)
        }
    }

}