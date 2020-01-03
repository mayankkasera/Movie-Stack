package com.example.moviestack.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.databinding.TrendingDataBinding

class SmallItemAdapter(private val list: List<SmallItemList.Result>) :

    RecyclerView.Adapter<SmallItemAdapter.TrendingMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMovieViewHolder {

        var s :String;

        val layoutInflater = LayoutInflater.from(parent.context)

        val trending: TrendingDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.small_item,parent,false)

        return TrendingMovieViewHolder(trending)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TrendingMovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TrendingMovieViewHolder(val trending: TrendingDataBinding) : RecyclerView.ViewHolder(trending.root) {

        fun bind(result: SmallItemList.Result) {
            this.trending.trendingModel = result
            this.trending.executePendingBindings()
        }

    }
}