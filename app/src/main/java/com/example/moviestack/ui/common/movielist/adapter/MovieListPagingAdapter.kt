package com.example.moviestack.ui.common.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.databinding.SimilarDataBinding

class MovieListPagingAdapter  : PagedListAdapter<Result, MovieListPagingAdapter.MovieListPagingViewHolder>(MovieDiffCallback()){

    inner class MovieListPagingViewHolder(val similarDataBinding : SimilarDataBinding) : RecyclerView.ViewHolder(similarDataBinding.root) {
        fun bind(result: Result) {
            this.similarDataBinding.similar = result
            this.similarDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListPagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val similarDataBinding : SimilarDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.similar,parent,false)
        return MovieListPagingViewHolder(similarDataBinding)
    }

    override fun onBindViewHolder(holder: MovieListPagingViewHolder, position: Int) {
        val result : Result? = getItem(position)!!
        holder.bind(result!!)
    }


    class MovieDiffCallback : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}