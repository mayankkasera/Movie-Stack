package com.example.moviestack.ui.persondetail.info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.PersonImages
import com.example.moviestack.api.pojo.Videos
import com.example.moviestack.databinding.ImagesDataBinding

class PersonImageAdapter(private val list: List<PersonImages.Profile>) : RecyclerView.Adapter<PersonImageAdapter.VideosViewHolder>() {

    inner class VideosViewHolder(val imagesDataBinding: ImagesDataBinding) : RecyclerView.ViewHolder(imagesDataBinding.root) {

        fun bind(result: PersonImages.Profile) {
            this.imagesDataBinding.images = result
            this.imagesDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val imagesDataBinding: ImagesDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.images,parent,false)
        return VideosViewHolder(imagesDataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bind(list[position])
    }
}