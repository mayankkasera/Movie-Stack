package com.example.moviestack.ui.common.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.MovieList
import com.example.moviestack.databinding.SimilarDataBinding

class MovieListAdapter (private val list: List<MovieList.Result>): RecyclerView.Adapter<MovieListAdapter.SimilarViewHolder>() {
    inner class SimilarViewHolder(val similarDataBinding : SimilarDataBinding) : RecyclerView.ViewHolder(similarDataBinding.root) {
        fun bind(result: MovieList.Result) {
            this.similarDataBinding.similar = result
            this.similarDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val similarDataBinding : SimilarDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.similar,parent,false)
        return SimilarViewHolder(similarDataBinding)
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bind(list[position])
    }
}