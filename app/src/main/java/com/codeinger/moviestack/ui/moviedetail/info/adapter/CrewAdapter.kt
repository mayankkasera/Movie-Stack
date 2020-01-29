package com.codeinger.moviestack.ui.moviedetail.info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.moviestack.R
import com.codeinger.moviestack.pojo.Credits
import com.codeinger.moviestack.databinding.CrewDataBinding

class CrewAdapter(private val list: List<Credits.Crew>) : RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    inner class CrewViewHolder(val crewDataBinding: CrewDataBinding) : RecyclerView.ViewHolder(crewDataBinding.root) {

        fun bind(result: Credits.Crew) {
            this.crewDataBinding.crew = result
            this.crewDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val crewDataBinding: CrewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.crew,parent,false)
        return CrewViewHolder(crewDataBinding)
    }

    override fun getItemCount(): Int {
        if(list.size>8)
            return 8
        else
            return list.size
    }



    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.bind(list[position])
    }


}