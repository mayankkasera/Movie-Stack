package com.example.moviestack.ui.common.person.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.databinding.CastDataBinding
import com.example.moviestack.ui.common.ListType

class PersonAdapter(private val credits: Credits, private val type: ListType.Type) : RecyclerView.Adapter<PersonAdapter.CastViewHolder>() {



    inner class CastViewHolder(val castDataBinding: CastDataBinding) : RecyclerView.ViewHolder(castDataBinding.root) {

        fun bind(result: Credits,i: Int) {
            if(type==ListType.Type.CREW){
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
        val castDataBinding: CastDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.person,parent,false)
        return CastViewHolder(castDataBinding)
    }

    override fun getItemCount(): Int {
        return when(type){
            ListType.Type.CAST -> credits.cast.size

            ListType.Type.CREW -> credits.crew.size

            else->0
        }

    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(credits,position)
    }

}