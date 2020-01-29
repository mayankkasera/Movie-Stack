package com.codeinger.moviestack.ui.moviedetail.info.bindingadapter

import android.content.Intent
import android.os.Parcelable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.codeinger.moviestack.ui.common.ListType
import com.codeinger.moviestack.ui.common.movielist.paginglist.MovieListPaggingActivity
import com.codeinger.moviestack.ui.moviedetail.DetailData

object GenreBindingAdapter {
    @JvmStatic
    @BindingAdapter("setGenreOnclick","genreOnclickType")
    fun setGenreOnclick (layout : ConstraintLayout, id : Int?,type: DetailData.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, MovieListPaggingActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("type", ListType.Type.GENRE)
            intent.putExtra("detailType",type as Parcelable)
            layout.context.startActivity(intent)
        }
    }
}