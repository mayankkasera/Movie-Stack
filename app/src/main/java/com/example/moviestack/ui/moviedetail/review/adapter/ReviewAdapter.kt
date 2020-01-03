package com.example.moviestack.ui.moviedetail.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.Review
import com.example.moviestack.databinding.ReviewDataBinding

class ReviewAdapter(private val list: List<Review.Result>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    inner class ReviewViewHolder(val reviewDataBinding: ReviewDataBinding) : RecyclerView.ViewHolder(reviewDataBinding.root) {

        fun bind(result: Review.Result) {
            this.reviewDataBinding.review = result
            this.reviewDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val reviewDataBinding: ReviewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.review,parent,false)
        return ReviewViewHolder(reviewDataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(list[position])
    }

}