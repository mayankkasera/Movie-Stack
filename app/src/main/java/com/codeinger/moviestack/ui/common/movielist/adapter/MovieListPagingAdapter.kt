package com.codeinger.moviestack.ui.common.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.moviestack.R
import com.codeinger.moviestack.databinding.MovieListDataBinding
import com.codeinger.moviestack.pojo.Result
import com.codeinger.moviestack.ui.moviedetail.DetailData

class MovieListPagingAdapter(var type : DetailData.Type)  : PagedListAdapter<Result, MovieListPagingAdapter.MovieListPagingViewHolder>(MovieDiffCallback()){



    inner class MovieListPagingViewHolder(val similarDataBinding :
                                          MovieListDataBinding) : RecyclerView.ViewHolder(similarDataBinding.root) {
        fun bind(result: Result) {
            this.similarDataBinding.similar = result
            this.similarDataBinding.type = type
            this.similarDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListPagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val similarDataBinding : MovieListDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.similar,parent,false)
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