package com.example.moviestack.ui.persondetail.info.bindingadapter

import android.content.Intent
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.moviestack.pojo.ImagesDeatail
import com.example.moviestack.ui.common.fullimage.FullImageActivity
import com.ms.square.android.expandabletextview.ExpandableTextView
import java.util.ArrayList


object PersonInfoBindingAdapter {
    @JvmStatic
    @BindingAdapter("setExpandableText")
    fun setExpandableText (layout : ExpandableTextView, text : String?){
        if (text != null) {
            layout?.setText(text)
        }
    }


    @JvmStatic
    @BindingAdapter("goToFullImage","position")
    fun goToFullImage (layout : CardView, list : List<ImagesDeatail>?,position : Int?){
        if (list != null) {
            layout?.setOnClickListener{
                val intent = Intent(layout.context, FullImageActivity::class.java)
                intent.putExtra("position",position)
                intent.putParcelableArrayListExtra("image", list as ArrayList<ImagesDeatail>)
                layout.context.startActivity(intent)
            }
        }
    }


    //app:setExpandableText="@{personInfo.biography}"

}