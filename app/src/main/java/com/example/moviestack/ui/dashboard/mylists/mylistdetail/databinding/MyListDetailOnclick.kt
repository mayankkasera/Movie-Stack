package com.example.moviestack.ui.dashboard.mylists.mylistdetail.databinding

import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.pojo.MovieList
import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.simplelist.MovieListActivity
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.MovieDetailActivity

object MyListDetailOnclick {
    @JvmStatic
    @BindingAdapter("setMyListDetailOnclick","setMyListDetailType")
    fun setMyListDetailOnclick (layout : ConstraintLayout, id : Int?, type: ListType.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListActivity::class.java)
            var data = ListType(
                "$id",
                type!!
            )
            intent.putExtra("list_type",data)
            layout.context.startActivity(intent)
        }
    }
}