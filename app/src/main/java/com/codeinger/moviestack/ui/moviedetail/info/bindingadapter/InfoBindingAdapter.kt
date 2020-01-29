package com.codeinger.moviestack.ui.moviedetail.info.bindingadapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.codeinger.moviestack.pojo.ImagesDeatail
import com.codeinger.moviestack.pojo.MovieInfo
import com.codeinger.moviestack.pojo.MyList
import com.codeinger.moviestack.pojo.MyListDetail
import com.codeinger.moviestack.roomdb.LocaleDataBase
import com.codeinger.moviestack.roomdb.RoomDatabaseHelper
import com.codeinger.moviestack.roomdb.bookmark.BookmarkHelper
import com.codeinger.moviestack.roomdb.mylist.MyListHelper
import com.codeinger.moviestack.roomdb.mylistdetail.MyListDetailHelper
import com.codeinger.moviestack.ui.common.fullimage.FullImageActivity
import com.codeinger.moviestack.ui.common.person.simple.PersonSimpleActivity
import com.codeinger.moviestack.ui.moviedetail.DetailData
import com.codeinger.moviestack.ui.moviedetail.info.dialog.MyListDetailDialog
import com.codeinger.moviestack.ui.moviedetail.info.dialog.MyListDetailDialogOnlick
import com.codeinger.moviestack.ui.moviedetail.info.dialog.MyListDialog
import com.codeinger.moviestack.ui.moviedetail.info.dialog.MyListDialogOnclick
import com.codeinger.qrcode.roomdb.utils.MovieInfoHelper
import java.util.ArrayList

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
    @BindingAdapter("showImage")
    fun showImage(layout: CardView, list : List<ImagesDeatail>?) {
        layout.setOnClickListener {
            val intent = Intent(layout.context, FullImageActivity::class.java)
            intent.putParcelableArrayListExtra("image", list as ArrayList<ImagesDeatail>)
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