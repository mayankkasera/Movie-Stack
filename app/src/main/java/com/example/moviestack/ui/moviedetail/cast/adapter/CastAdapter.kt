package com.example.moviestack.ui.moviedetail.cast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.Credits
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.databinding.CastDataBinding

class CastAdapter(private val list: List<Credits.Cast>) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    inner class CastViewHolder(val castDataBinding: CastDataBinding) : RecyclerView.ViewHolder(castDataBinding.root) {

        fun bind(result: Credits.Cast) {
            this.castDataBinding.cast = result
            this.castDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val castDataBinding: CastDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.cast,parent,false)
        return CastViewHolder(castDataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(list[position])
    }

}