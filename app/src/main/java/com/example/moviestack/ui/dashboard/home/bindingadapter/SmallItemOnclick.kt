package com.example.moviestack.ui.dashboard.home.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingActivity
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.MovieDetailActivity

object SmallItemOnclick {
    @JvmStatic
    @BindingAdapter("setSmallItemOnclick")
    fun setSmallItemOnclick (layout : ConstraintLayout, result : Result?){

        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieDetailActivity::class.java)

//            var data = DetailData(
//                "${result?.id}",
//                if(result?.name.equals("")) result?.originalTitle!! else  result?.name!!,
//
//                )

            intent.putExtra("id", "${result?.id}")
            intent.putExtra("title", if(result?.title.equals(""))result?.originalTitle else  result?.title)
            layout.context.startActivity(intent)
        }

    }

    @JvmStatic
    @BindingAdapter("setMoreOnclick")
    fun setMoreOnclick (layout : AppCompatTextView, type : ListType.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListPaggingActivity::class.java)
            intent.putExtra("type", type)
            layout.context.startActivity(intent)
        }
    }



}