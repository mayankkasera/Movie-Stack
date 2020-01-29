package com.codeinger.moviestack.ui.moviedetail.info.bindingadapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter


object VideoBindingAdapter {
    @JvmStatic
    @BindingAdapter("setVideoOnclick")
    fun setVideoOnclick(layout: ConstraintLayout, id : String?) {
        layout.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=$id")
            )
            try {
                layout.context.startActivity(appIntent)
            } catch (ex: ActivityNotFoundException) {
                layout.context.startActivity(webIntent)
            }
        }

    }
}