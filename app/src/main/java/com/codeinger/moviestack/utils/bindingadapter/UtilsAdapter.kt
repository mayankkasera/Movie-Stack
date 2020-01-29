package com.codeinger.moviestack.utils.bindingadapter


import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.ui.moviedetail.DetailData
import com.codeinger.moviestack.ui.moviedetail.MovieDetailActivity
import com.codeinger.moviestack.api.NetworkConstants
import com.codeinger.moviestack.utils.PicassoImageLoadingService
import com.codeinger.moviestack.utils.adapter.MainSliderAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ss.com.bannerslider.Slider


object UtilsAdapter{
    @JvmStatic
    @BindingAdapter("setImageResource")
    fun setImageResource (imageView : AppCompatImageView,url : String?){
        if(url!=null&&!url.equals(""))
         Picasso.get().load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setImageResource")
    fun setImageResource (imageView : CircleImageView,url : String?){
        Picasso.get().load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("addSlider")
    fun addSlider (slider : Slider , result : List<Result>?){

        Log.i("dsbcjd","sdxzchbzjcs :${result}")
        if (result != null) {
            var list = ArrayList<String>()
            for(r in result!!)
                list.add(NetworkConstants.baseImageUrl500+r.backdropPath)

            Slider.init(PicassoImageLoadingService());
            slider.setAdapter(MainSliderAdapter(list))
            slider.setInterval(10000)
            slider.setOnSlideClickListener{
                val intent = Intent(slider.context, MovieDetailActivity::class.java)

                var data = DetailData(
                    "${result?.get(it).id}",
                    if(result?.get(it).name.equals("")) result?.get(it).originalTitle!! else  result?.get(it).name!!, DetailData.Type.MOVIE!!
                )


                intent.putExtra("datasdcds", data)
                intent.putExtra("id", "${result?.get(it)?.id}")
                intent.putExtra("title", if(result?.get(it).title.equals(""))result?.get(it).originalTitle else  result?.get(it).title)
                slider.context.startActivity(intent)
            }
        }



    }



}