package com.example.moviestack.ui.common.credits.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.databinding.CastDataBinding
import com.example.moviestack.ui.common.credits.CreditType

class CastAdapter(private val credits: Credits,private val type: CreditType.Type) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {



    inner class CastViewHolder(val castDataBinding: CastDataBinding) : RecyclerView.ViewHolder(castDataBinding.root) {

        fun bind(result: Credits,i: Int) {
            if(type==CreditType.Type.CREW){
                this.castDataBinding.crew = credits.crew[i]
                this.castDataBinding.cast = Credits.Cast(1,"","",1,1,"",1,"")
            }
            else{
                this.castDataBinding.crew = Credits.Crew("","",1,1,"","","")
                this.castDataBinding.cast = credits.cast[i]
            }

            this.castDataBinding.type = type
            this.castDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val castDataBinding: CastDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.cast,parent,false)
        return CastViewHolder(castDataBinding)
    }

    override fun getItemCount(): Int {
        return when(type){
            CreditType.Type.CAST -> credits.cast.size

            CreditType.Type.CREW -> credits.crew.size
        }

    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(credits,position)
    }

}