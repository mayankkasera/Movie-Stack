package com.example.moviestack.ui.common.credits.bindingadapter

import android.content.Intent
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.ui.moviedetail.MovieDetailActivity
import com.example.moviestack.ui.persondetail.PersonDetailActivity

object CreditItemOnlick {

    @JvmStatic
    @BindingAdapter("setCreditItemOnlick","setCreditTitle","setCreditImage")
    fun setCreditItemOnlick (layout : ConstraintLayout, result : String?,title : String?,image : String?){

        layout.setOnClickListener{
            Log.i("sdjbcsj","${result} ${title} ${image}")
            val intent = Intent(layout.context, PersonDetailActivity::class.java)
            intent.putExtra("id", "${result}")
            intent.putExtra("title", "${title}")
            intent.putExtra("image", "${image}")
            layout.context.startActivity(intent)
        }

    }

}