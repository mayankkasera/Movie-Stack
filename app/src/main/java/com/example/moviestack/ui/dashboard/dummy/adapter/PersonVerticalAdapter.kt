package com.example.moviestack.ui.dashboard.dummy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.databinding.CastDataBinding
import com.example.moviestack.databinding.PersonVerticle
import com.example.moviestack.pojo.Credits
import com.example.moviestack.pojo.Person
import com.example.moviestack.pojo.Result
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.adapter.MovieListAdapter
import com.example.moviestack.ui.moviedetail.DetailData

class PersonVerticalAdapter(private val list: List<Result>): RecyclerView.Adapter<PersonVerticalAdapter.PersonVerticalViewHolder>() {

    inner class PersonVerticalViewHolder(val castDataBinding: PersonVerticle) : RecyclerView.ViewHolder(castDataBinding.root) {

        fun bind(person: Result, i: Int) {
            this.castDataBinding.person = person
            this.castDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVerticalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val similarDataBinding : PersonVerticle = DataBindingUtil.inflate(layoutInflater, R.layout.person_vertical,parent,false)
        return PersonVerticalViewHolder(similarDataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PersonVerticalViewHolder, position: Int) {
        holder.bind(list[position],position)
    }


}