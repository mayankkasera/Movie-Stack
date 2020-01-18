package com.example.moviestack.ui.moviedetail.info.bindingadapter

import android.content.Intent
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.roomdb.bookmark.BookmarkHelperI
import com.example.moviestack.roomdb.movieInfo.MovieInfoHelperI
import com.example.moviestack.ui.common.person.simple.PersonSimpleActivity
import com.example.moviestack.ui.moviedetail.DetailData

object InfoBindingAdapter {
    @JvmStatic
    @BindingAdapter("showAllCrewOnclick","AllCrewOnclickType")
    fun showAllCrewOnclick (layout : AppCompatTextView, id : Long?,type : DetailData.Type?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, PersonSimpleActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("type",type as Parcelable)
            layout.context.startActivity(intent)
        }
    }




    @JvmStatic
    @BindingAdapter("bookmarkOnclick","setBookmarkHelperI")
    fun bookmarkOnclick (layout : AppCompatImageView, movieInfo : MovieInfo?, bookmarkHelperI: BookmarkHelperI?){
        if (movieInfo != null && bookmarkHelperI != null) {

            if(bookmarkHelperI.hasBookmark("${movieInfo.id}"))
                layout.isSelected = true
            else
                layout.isSelected = false

            layout.setOnClickListener{
                if(layout.isSelected){
                    layout.isSelected = false
                    bookmarkHelperI.deleteBookmark(movieInfo.id!!)
                }
                else{
                    layout.isSelected = true
                    bookmarkHelperI.insertBookmark(movieInfo!!)
                }
            }
        }
    }
}