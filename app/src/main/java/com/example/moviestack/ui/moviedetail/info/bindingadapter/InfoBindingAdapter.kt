package com.example.moviestack.ui.moviedetail.info.bindingadapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.MyList
import com.example.moviestack.pojo.MyListDetail
import com.example.moviestack.roomdb.LocaleDataBase
import com.example.moviestack.roomdb.RoomDatabaseHelper
import com.example.moviestack.roomdb.bookmark.BookmarkHelper
import com.example.moviestack.roomdb.mylist.MyListHelper
import com.example.moviestack.roomdb.mylistdetail.MyListDetailHelper
import com.example.moviestack.ui.common.person.simple.PersonSimpleActivity
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.info.dialog.MyListDetailDialog
import com.example.moviestack.ui.moviedetail.info.dialog.MyListDetailDialogOnlick
import com.example.moviestack.ui.moviedetail.info.dialog.MyListDialog
import com.example.moviestack.ui.moviedetail.info.dialog.MyListDialogOnclick
import com.example.moviestack.ui.moviedetail.info.dialog.adapter.MyListAdapter
import com.example.qrcode.roomdb.utils.MovieInfoHelper

object InfoBindingAdapter {
    @JvmStatic
    @BindingAdapter("showAllCrewOnclick", "AllCrewOnclickType")
    fun showAllCrewOnclick(layout: AppCompatTextView, id: Long?, type: DetailData.Type?) {
        layout.setOnClickListener {
            val intent = Intent(layout.context, PersonSimpleActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("type", type as Parcelable)
            layout.context.startActivity(intent)
        }
    }


    @JvmStatic
    @BindingAdapter("bookmarkOnclick", "setlocaleDataBase", "bookmarkOnclickType")
    fun bookmarkOnclick(
        layout: AppCompatImageView,
        movieInfo: MovieInfo?,
        localeDataBase: LocaleDataBase?,
        type: DetailData.Type?
    ) {
        if (movieInfo != null && localeDataBase != null) {

            var bookmarkHelperI = BookmarkHelper(localeDataBase)

            if (bookmarkHelperI.hasBookmark("${movieInfo.id}"))
                layout.isSelected = true
            else
                layout.isSelected = false

            layout.setOnClickListener {
                if (layout.isSelected) {
                    layout.isSelected = false
                    bookmarkHelperI.deleteBookmark(movieInfo.id!!)
                } else {
                    layout.isSelected = true
                    bookmarkHelperI.insertBookmark(movieInfo!!, type!!)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("myListOnclick", "setlocaleDataBaseMyListOnclick","myListOnclickType")
    fun myListOnclick(
        layout: AppCompatImageView,
        movieInfo: MovieInfo?,
        localeDataBase: LocaleDataBase?,
        typeDetailData: DetailData.Type?
    ) {

        if (movieInfo != null && localeDataBase != null) {
            val myListHelperI = MyListHelper(localeDataBase!!)

            var type : MyList.Type;
            var myListDetailType : MyListDetail.Type

            if(typeDetailData == DetailData.Type.MOVIE) {
                type = MyList.Type.MOVIE
                myListDetailType = MyListDetail.Type.MOVIE
            }
            else{
                type = MyList.Type.TV_SHOW
                myListDetailType = MyListDetail.Type.TV_SHOW
            }


            layout.setOnClickListener {
              val myListDetailDialog =  MyListDetailDialog(context = layout.context,type = myListDetailType)
                myListDetailDialog.setAdapter(myListHelperI.getAllMyListMovie(type),movieInfo!!)
                myListDetailDialog
                    .setOnclic(object : MyListDetailDialogOnlick{
                        override fun onClick() {
                            val also = MyListDialog(layout.context).also {
                                it.setOnClickListener(object : MyListDialogOnclick {
                                    override fun onClick(s: String) {
                                        val myList = MyList(
                                            name = s,
                                            size = 1,
                                            type = type,
                                            image = movieInfo?.backdropPath!!
                                        )
                                        val l = myListHelperI.insertMyList(myList)
                                        val myListDetailHelper = MyListDetailHelper(RoomDatabaseHelper().localeDataBase)
                                        val movieInfoHelper = MovieInfoHelper(RoomDatabaseHelper().localeDataBase)
                                        if(!movieInfoHelper.hasMovie("${movieInfo.id}"))
                                            movieInfoHelper.insertResult(movieInfo)
                                        myListDetailHelper.insertMyListDetail(MyListDetail(movieInfoId = movieInfo.id!!,myListId = l.toInt(),type = myListDetailType))
                                    }

                                    @SuppressLint("ShowToast")
                                    override fun onCancel() {
                                        Toast.makeText(layout.context, "Cancel...", Toast.LENGTH_SHORT)
                                    }
                                })
                                it.show()
                            }

                        }
                    })
            }




        }


    }
}