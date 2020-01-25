package com.example.moviestack.ui.dashboard.search.bindingadapter

import android.content.Intent
import android.os.Parcelable
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingActivity
import com.example.moviestack.ui.common.person.paging.PersonPagingActivity
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.search.SearchActivity

object SearchBindingAdapter {

    @JvmStatic
    @BindingAdapter("setSearchOnclick","setSearchOnclickType")
    fun setSearchOnclick (layout : CardView, type : ListType.Type?, typeDetailData : DetailData.Type){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListPaggingActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("detailType",typeDetailData as Parcelable)
            layout.context.startActivity(intent)
        }
    }

    @JvmStatic
    @BindingAdapter("setPersonOnclick")
    fun setPersonOnclick (layout : CardView, any : Any?){
        layout.setOnClickListener{
            var detailDataType :  DetailData.Type? = DetailData.Type.PERSON
            val intent = Intent(layout.context, PersonPagingActivity::class.java)
            intent.putExtra("type", ListType.Type.POPULAR_PERSON)
            intent.putExtra("detailType",detailDataType as Parcelable)
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