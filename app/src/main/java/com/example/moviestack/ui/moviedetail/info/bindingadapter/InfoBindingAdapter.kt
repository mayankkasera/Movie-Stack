package com.example.moviestack.ui.moviedetail.info.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.roomdb.bookmark.BookmarkHelperI
import com.example.moviestack.roomdb.movieInfo.MovieInfoHelperI
import com.example.moviestack.ui.common.person.simple.PersonSimpleActivity

object InfoBindingAdapter {
    @JvmStatic
    @BindingAdapter("showAllCrewOnclick")
    fun showAllCrewOnclick (layout : AppCompatTextView, id : Long?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, PersonSimpleActivity::class.java)
            intent.putExtra("id", id)
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