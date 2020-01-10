package com.example.moviestack.ui.persondetail.info.bindingadapter

import androidx.databinding.BindingAdapter
import com.ms.square.android.expandabletextview.ExpandableTextView


object PersonInfoBindingAdapter {
    @JvmStatic
    @BindingAdapter("setExpandableText")
    fun setExpandableText (layout : ExpandableTextView, text : String?){
        if (text != null) {
            layout?.setText(text)
        }
    }

    //app:setExpandableText="@{personInfo.biography}"

}