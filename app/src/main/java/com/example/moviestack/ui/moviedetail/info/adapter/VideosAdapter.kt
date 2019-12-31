package com.example.moviestack.ui.moviedetail.info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.MovieInfo
import com.example.moviestack.api.pojo.Videos
import com.example.moviestack.databinding.VideoDataBinding

class VideosAdapter(private val list: List<Videos.Result>) : RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    inner class VideosViewHolder(val videoDataBinding: VideoDataBinding) : RecyclerView.ViewHolder(videoDataBinding.root) {

        fun bind(result: Videos.Result) {
            this.videoDataBinding.video = result
            this.videoDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val videoDataBinding: VideoDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.video,parent,false)
        return VideosViewHolder(videoDataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bind(list[position])
    }
}