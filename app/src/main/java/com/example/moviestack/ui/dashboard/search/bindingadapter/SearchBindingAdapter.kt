package com.example.moviestack.ui.dashboard.search.bindingadapter

import android.content.Intent
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingActivity
import com.example.moviestack.ui.common.person.paging.PersonPagingActivity
import com.example.moviestack.ui.search.SearchActivity

object SearchBindingAdapter {

    @JvmStatic
    @BindingAdapter("setSearchOnclick")
    fun setSearchOnclick (layout : CardView, type : ListType.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListPaggingActivity::class.java)
            intent.putExtra("type", type)
            layout.context.startActivity(intent)
        }
    }

    @JvmStatic
    @BindingAdapter("setPersonOnclick")
    fun setPersonOnclick (layout : CardView, any : Any?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, PersonPagingActivity::class.java)
            layout.context.startActivity(intent)
        }
    }

    @JvmStatic
    @BindingAdapter("setGoToActivityOnclick")
    fun setGoToActivityOnclick (layout : LinearLayout, any : Any?){
        layout.setOnClickListener{
            var intent = Intent(layout.context, SearchActivity::class.java)
            layout.context.startActivity(intent)
        }
    }

}