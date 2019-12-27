package com.example.moviestack.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.Trending
import com.example.moviestack.databinding.TrendingDataBinding

class TrendingMovieAdapter(private val listOfFlags: List<Trending.Result>) :

    RecyclerView.Adapter<TrendingMovieAdapter.TrendingMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMovieViewHolder {

       var s :String;

        val layoutInflater = LayoutInflater.from(parent.context)

        val trending: TrendingDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.trending_movie,parent,false)

        return TrendingMovieViewHolder(trending)

    }

    override fun getItemCount(): Int {
        return listOfFlags.size
    }

    override fun onBindViewHolder(holder: TrendingMovieViewHolder, position: Int) {
        holder.bind(listOfFlags[position])
    }

    inner class TrendingMovieViewHolder(val trending: TrendingDataBinding) : RecyclerView.ViewHolder(trending.root) {

        fun bind(result: Trending.Result) {
            this.trending.trendingModel = result
            this.trending.executePendingBindings()
        }

    }
}