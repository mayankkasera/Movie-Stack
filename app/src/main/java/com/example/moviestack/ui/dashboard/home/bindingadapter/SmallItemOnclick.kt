package com.example.moviestack.ui.dashboard.home.bindingadapter

import android.content.Intent
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.paginglist.MovieListPaggingActivity
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.MovieDetailActivity
import java.io.Serializable

object SmallItemOnclick {
    @JvmStatic
    @BindingAdapter("setSmallItemOnclick","smallItemOnclickType")
    fun setSmallItemOnclick (layout : ConstraintLayout, result : Result?,type: DetailData.Type?){

        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieDetailActivity::class.java)

            var data = DetailData(
                "${result?.id}",
                if(result?.name.equals("")) result?.originalTitle!! else  result?.name!!,
                type!!
                )

            Log.i("dsbcjd","sdxzchbzjcs :${result}")
            intent.putExtra("datasdcds", data)
            intent.putExtra("id", "${result?.id}")
            intent.putExtra("title", if(result?.title.equals(""))result?.originalTitle else  result?.title)
            layout.context.startActivity(intent)
        }

    }

    @JvmStatic
    @BindingAdapter("setMoreMovieOnclick")
    fun setMoreMovieOnclick (layout : AppCompatTextView, type : ListType.Type?){
        layout.setOnClickListener{
            var detailDataType :  DetailData.Type? = DetailData.Type.MOVIE
            val intent = Intent(layout.context, MovieListPaggingActivity::class.java)
            intent.putExtra("type", type as Serializable)
            intent.putExtra("detailType",detailDataType as Parcelable)
            layout.context.startActivity(intent)
        }
    }

    @JvmStatic
    @BindingAdapter("setMoreTvOnclick")
    fun setMoreTvOnclick (layout : AppCompatTextView, type : ListType.Type?){
        layout.setOnClickListener{
            var detailDataType :  DetailData.Type? = DetailData.Type.TV_SHOW
            val intent = Intent(layout.context, MovieListPaggingActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("detailType",detailDataType as Parcelable)
            layout.context.startActivity(intent)
        }
    }



}