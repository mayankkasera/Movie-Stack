package com.example.moviestack.ui.moviedetail.info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.SmallItemList
import com.example.moviestack.databinding.GenresDataBinding
import com.example.moviestack.databinding.TrendingDataBinding
import com.example.moviestack.ui.dashboard.home.adapter.SmallItemAdapter
import com.example.moviestack.ui.moviedetail.DetailData

class GenreAdapter(private val list: List<MovieInfo.Genre>,private val type: DetailData.Type)  : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(val trending: GenresDataBinding) : RecyclerView.ViewHolder(trending.root) {

        fun bind(result: MovieInfo.Genre?) {
            this.trending.genres = result
            this.trending.type = type
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