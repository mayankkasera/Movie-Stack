package com.example.moviestack.ui.common.person.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.databinding.PersonPagingDataBinding

class PersonPagingAdapter : PagedListAdapter<Result, PersonPagingAdapter.PersonPagingViewHolder>(PersonPagingAdapter.MovieDiffCallback()){

    inner class PersonPagingViewHolder(val personPagingDataBinding : PersonPagingDataBinding) : RecyclerView.ViewHolder(personPagingDataBinding.root) {
        fun bind(result: Result) {
            this.personPagingDataBinding.person = result
            this.personPagingDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonPagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val personPagingDataBinding : PersonPagingDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.person_paging,parent,false)
        return PersonPagingViewHolder(personPagingDataBinding)
    }

    override fun onBindViewHolder(holder: PersonPagingViewHolder, position: Int) {
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