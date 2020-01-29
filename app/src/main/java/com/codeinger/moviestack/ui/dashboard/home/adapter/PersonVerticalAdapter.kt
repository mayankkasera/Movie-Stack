package com.codeinger.moviestack.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.moviestack.R
import com.codeinger.moviestack.databinding.PersonVerticle
import com.codeinger.moviestack.pojo.Result

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