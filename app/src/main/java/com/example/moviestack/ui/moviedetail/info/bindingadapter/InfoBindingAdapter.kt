package com.example.moviestack.ui.moviedetail.info.bindingadapter

import android.content.Intent
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.example.moviestack.ui.common.credits.CreditActivity

object InfoBindingAdapter {
    @JvmStatic
    @BindingAdapter("showAllCrewOnclick")
    fun showAllCrewOnclick (layout : AppCompatTextView, id : Int?){
        layout.setOnClickListener{
            val intent = Intent(layout.context, CreditActivity::class.java)
            intent.putExtra("id", id)
            layout.context.startActivity(intent)
        }
    }
}