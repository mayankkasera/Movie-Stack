package com.codeinger.moviestack.ui.common.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.moviestack.R
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.databinding.MovieListDataBinding
import com.codeinger.moviestack.ui.moviedetail.DetailData

class MovieListAdapter (private val list: List<Result>,private val type : DetailData.Type): RecyclerView.Adapter<MovieListAdapter.SimilarViewHolder>() {

    inner class SimilarViewHolder(val similarDataBinding : MovieListDataBinding) : RecyclerView.ViewHolder(similarDataBinding.root) {
        fun bind(result: Result) {
            this.similarDataBinding.similar = result
            this.similarDataBinding.type = type
            this.similarDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val similarDataBinding : MovieListDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.similar,parent,false)
        return SimilarViewHolder(similarDataBinding)
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bind(list[position])
    }
}