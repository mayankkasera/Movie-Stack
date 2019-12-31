package com.example.moviestack.ui.moviedetail.info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.databinding.GenresDataBinding
import com.example.moviestack.databinding.TrendingDataBinding
import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter

class GenreAdapter(private val list: List<MovieInfo.Genre>)  : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(val trending: GenresDataBinding) : RecyclerView.ViewHolder(trending.root) {

        fun bind(result: MovieInfo.Genre) {
            this.trending.genres = result
            this.trending.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val trending: GenresDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.genre,parent,false)
        return GenreViewHolder(trending)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(list[position])
    }
}